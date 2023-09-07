package com.example.bookStoreProject.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bookStoreProject.entity.Author;
import com.example.bookStoreProject.entity.Book;
import com.example.bookStoreProject.entity.Publisher;
import com.example.bookStoreProject.repository.AuthorRepository;
import com.example.bookStoreProject.repository.BookRepository;
import com.example.bookStoreProject.repository.PublisherRepository;
import com.example.bookStoreProject.services.BooksService;

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
    

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long bookId, Book book) {
        if (bookRepository.existsById(bookId)) {
            book.setBookID(bookId);
            return bookRepository.save(book);
        }
        return null;
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
    
    @Override
    public ResponseEntity<String> addBooks(Map<String, String>map) {
    	try {
			Book book = configureBook(map);
			System.out.println(book);
			if(Objects.isNull(book) == false) {
				bookRepository.save(book);
				return new ResponseEntity<String>("Book Added Successfully!!!", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Book Can not be added!!!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    	
    }
    
    public Book configureBook(Map<String, String> map) {
    	
    	Optional<Author> author  = authorRepository.findById(Long.parseLong(map.get("author")));
    	Optional<Publisher> publisher  = publisherRepository.findById(Long.parseLong(map.get("publisher")));
    	
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
    	
    	return book;
    }
}
