package com.bookstore.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.entity.Publisher;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.PublisherRepository;
import com.bookstore.services.BooksService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {
    private final BookRepository bookRepository;

    @Autowired
    public BooksServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @Autowired
    AuthorRepository authorRepository;
    
    @Autowired
    PublisherRepository publisherRepository;
    

    // Get a list of all books
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a book by its ID
    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    // Create a new book
    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // Update an existing book
    @Override
    public Book updateBook(Long bookId, Book book) {
        if (bookRepository.existsById(bookId)) {
            book.setBookID(bookId);
            return bookRepository.save(book);
        }
        return null;
    }

    // Delete a book by its ID
    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
    
    // Add a book using a map of book details
    @Override
    public ResponseEntity<String> addBooks(Map<String, String> map) throws Exception{
        try {
            Book book = configureBook(map);
            if (Objects.isNull(book) == false) {
                bookRepository.save(book);
                return new ResponseEntity<String>("Book Added Successfully!!!", HttpStatus.OK);
            }
            return new ResponseEntity<String>("Book Can not be added!!!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // Configure and create a book entity from a map of book details
    public Book configureBook(Map<String, String> map) throws NullPointerException {
        Optional<Author> author = authorRepository.findById(Long.parseLong(map.get("author")));
        Optional<Publisher> publisher = publisherRepository.findById(Long.parseLong(map.get("publisher")));
        
        try {
        Book book = new Book();
        book.setTitle(map.get("title"));
        book.setBookCondition(map.get("condition"));
        book.setISBNcode(Long.parseLong(map.get("isbncode")));
        book.setCategory(map.get("category"));
        book.setPrice(BigDecimal.valueOf(Double.valueOf(map.get("price"))));
        book.setPublishedYear(Integer.parseInt(map.get("publishedYear")));
        book.setAuthor(author.get());
        book.setPublisher(publisher.get());
        book.setPageCount(Integer.parseInt(map.get("pages")));
        book.setImages(map.get("images"));
        return book;
        }catch (Exception e) {
			// TODO: handle exception
        	//e.printStackTrace();

		}
        return new Book();
    }

    // Search for a book by various criteria
    @Override
    public ResponseEntity<Book> searchBook(Map<String, String> map) {
        // Not yet implemented
        return null;
    }

    // Search for books by various criteria
    @Override
    public ResponseEntity<List<Book>> searchBooks(Map<String, String> map) {
        List<Book> blist = null;
        try {
            if (map.get("searchBy").equals("title")) {
                blist = bookRepository.getBookByTitle(map.get("input"));
                if (Objects.isNull(blist)) {
                    return new ResponseEntity<List<Book>>(blist, HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<List<Book>>(blist, HttpStatus.OK);	
            } else if (map.get("searchBy").equals("category")) {
                blist = bookRepository.getBookByCategory(map.get("input"));
                if (Objects.isNull(blist)) {
                    return new ResponseEntity<List<Book>>(blist, HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<List<Book>>(blist, HttpStatus.OK);
            } else if (map.get("searchBy").equals("author")) {
                blist = bookRepository.getBookByAuthor(map.get("input"));
                if (Objects.isNull(blist)) {
                    return new ResponseEntity<List<Book>>(blist, HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<List<Book>>(blist, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<Book>>(blist, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}












//package com.bookStoreProject.servicesImpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.bookStoreProject.entity.Author;
//import com.bookStoreProject.entity.Book;
//import com.bookStoreProject.entity.Publisher;
//import com.bookStoreProject.repository.AuthorRepository;
//import com.bookStoreProject.repository.BookRepository;
//import com.bookStoreProject.repository.PublisherRepository;
//import com.bookStoreProject.services.BooksService;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Optional;
//
//@Service
//public class BooksServiceImpl implements BooksService {
//    private final BookRepository bookRepository;
//
//    @Autowired
//    public BooksServiceImpl(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
//    
//    @Autowired
//    AuthorRepository authorRepository;
//    
//    @Autowired
//    PublisherRepository publisherRepository;
//    
//
//    @Override
//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();
//    }
//
//    @Override
//    public Book getBookById(Long bookId) {
//        return bookRepository.findById(bookId).orElse(null);
//    }
//
//    @Override
//    public Book createBook(Book book) {
//        return bookRepository.save(book);
//    }
//
//    @Override
//    public Book updateBook(Long bookId, Book book) {
//        if (bookRepository.existsById(bookId)) {
//            book.setBookID(bookId);
//            return bookRepository.save(book);
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteBook(Long bookId) {
//        bookRepository.deleteById(bookId);
//    }
//    
//    @Override
//    public ResponseEntity<String> addBooks(Map<String, String>map) {
//    	try {
//			Book book = configureBook(map);
//			if(Objects.isNull(book) == false) {
//				bookRepository.save(book);
//				return new ResponseEntity<String>("Book Added Successfully!!!", HttpStatus.OK);
//			}
//			return new ResponseEntity<String>("Book Can not be added!!!", HttpStatus.BAD_REQUEST);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//    	return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
//    	
//    }
//    
//    public Book configureBook(Map<String, String> map) {
//    	
//    	Optional<Author> author  = authorRepository.findById(Long.parseLong(map.get("author")));
//    	Optional<Publisher> publisher  = publisherRepository.findById(Long.parseLong(map.get("publisher")));
//    	
//    	Book book = new Book();
//    	book.setTitle(map.get("title"));
//    	book.setBookCondition(map.get("condition"));
//    	book.setISBNcode(Long.parseLong(map.get("isbncode")));
//    	book.setCategory(map.get("category"));
//    	book.setPrice(BigDecimal.valueOf(Double.valueOf(map.get("price"))));
//    	book.setPublishedYear(Integer.parseInt(map.get("publishedYear")));
//    	book.setAuthor(author.get());
//    	book.setPublisher(publisher.get());
//    	book.setPageCount(Integer.parseInt(map.get("pages")));
//    	book.setImages(map.get("images"));    	
//    	return book;
//    }
//
//	@Override
//	public ResponseEntity<Book> searchBook(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ResponseEntity<List<Book>> searchBooks(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		
//		List<Book> blist = null;
//		try {
//			if(map.get("searchBy").equals("title")) {
//				blist = bookRepository.getBookByTitle(map.get("input"));
//				 if(Objects.isNull(blist)) {
//					return new ResponseEntity<List<Book>>(blist, HttpStatus.NOT_FOUND);
//				}
//				return new ResponseEntity<List<Book>>(blist, HttpStatus.OK);	
//			}else	if(map.get("searchBy").equals("category")) {
//				 blist = bookRepository.getBookByCategory(map.get("input"));
//				if(Objects.isNull(blist)) {
//					return new ResponseEntity<List<Book>>(blist, HttpStatus.NOT_FOUND);
//				}
//				return new ResponseEntity<List<Book>>(blist, HttpStatus.OK);
//				
//			}else if(map.get("searchBy").equals("author")) {
//				 blist = bookRepository.getBookByAuthor(map.get("input"));
//				if(Objects.isNull(blist)) {
//					return new ResponseEntity<List<Book>>(blist, HttpStatus.NOT_FOUND);
//				}
//				return new ResponseEntity<List<Book>>(blist, HttpStatus.OK);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return new ResponseEntity<List<Book>>(blist, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//}
