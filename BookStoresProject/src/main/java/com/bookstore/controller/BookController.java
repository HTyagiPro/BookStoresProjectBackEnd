package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookstore.entity.Book;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.exception.ResourceNotModifiedException;
import com.bookstore.services.BooksService;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/books")
public class BookController {
    private final BooksService bookService;

    @Autowired
    public BookController(BooksService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.ok(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    
    // Add a book using a map of data
    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestBody(required = true) Map<String, String> map) {
        try {
            // Attempt to add a book using the provided map data
            return bookService.addBooks(map);
        } catch (Exception e) {
            // Handle any exceptions that may occur and log the error
            e.printStackTrace();
        }
        // If an exception occurs, return an internal server error response
        return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // Search for books using a map of search criteria
    @PostMapping("/searchBook")
    public ResponseEntity<List<Book>> searchBooks(@RequestBody(required = true) Map<String, String> map) {
        try {
            // Attempt to search for books using the provided search criteria
            return bookService.searchBooks(map);
        } catch (Exception e) {
            // Handle any exceptions that may occur and log the error
            e.printStackTrace();
        }
        // If an exception occurs, return an empty list and an internal server error response
        List<Book> bookList = null;
        return new ResponseEntity<List<Book>>(bookList, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
    @ExceptionHandler(ResourceNotModifiedException.class)
	public HttpStatus notModifiedExceptionHandler() {
		return HttpStatus.NOT_MODIFIED;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public HttpStatus notFoundExceptionHandler() {
		return HttpStatus.NOT_FOUND;
	}
}



































/*
@PostMapping("/addBook")
public ResponseEntity<String> addBook(@RequestBody(required = true) Map<String, String> map){
	try {
		return bookService.addBooks(map);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return new ResponseEntity<String>("Somwthing Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
}


@PostMapping("/searchBook")
public ResponseEntity<List<Book>> searchBooks(@RequestBody(required = true) Map<String, String> map){
	try {
		return bookService.searchBooks(map);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	List<Book> blist = null;
	return new ResponseEntity<List<Book>>(blist, HttpStatus.INTERNAL_SERVER_ERROR);
}
*/

