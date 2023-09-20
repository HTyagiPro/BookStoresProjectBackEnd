package com.bookStoreProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookStoreProject.entity.Author;
import com.bookStoreProject.exception.ResourceNotFoundException;
import com.bookStoreProject.exception.ResourceNotModifiedException;
import com.bookStoreProject.services.AuthorService;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.createAuthor(author);
        return ResponseEntity.ok(createdAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        Author updatedAuthor = authorService.updateAuthor(id, author);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
    
    
    // Add an author using a map of data
    @PostMapping("/addAuthor")
    public ResponseEntity<String> addAuthor(@RequestBody(required = true) Map<String, String> map){
    	try {
            // Attempt to add an author using the provided map data
			return authorService.addAuthor(map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        // If an exception occurs, return an internal server error response
    	return new ResponseEntity<String>("Somwthing Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // Get the added author
    @GetMapping("/getAddedAuthor")
    public Author getAddedAuthor() {
    	try {
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
        // Retrieve and return the recently added author
    	return authorService.getAddedAuthor();
    	}
    
 // Exception handler for ResourceNotModifiedException
    @ExceptionHandler(ResourceNotModifiedException.class)
    public HttpStatus notModifiedExceptionHandler() {
        return HttpStatus.NOT_MODIFIED;
    }

    // Exception handler for ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public HttpStatus notFoundExceptionHandler() {
        return HttpStatus.NOT_FOUND;
    }
}
