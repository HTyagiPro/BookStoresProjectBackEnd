package com.bookStoreProject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookStoreProject.entity.Publisher;
import com.bookStoreProject.exception.ResourceNotFoundException;
import com.bookStoreProject.exception.ResourceNotModifiedException;
import com.bookStoreProject.services.PublisherService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    // Get all publishers
    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    // Get a publisher by ID
    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Long id) {
        Publisher publisher = publisherService.getPublisherById(id);
        return ResponseEntity.ok(publisher);
    }

    // Create a new publisher
    @PostMapping
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        Publisher createdPublisher = publisherService.createPublisher(publisher);
        return ResponseEntity.ok(createdPublisher);
    }

    // Update an existing publisher by ID
    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher publisher) {
        Publisher updatedPublisher = publisherService.updatePublisher(id, publisher);
        return ResponseEntity.ok(updatedPublisher);
    }

    // Delete a publisher by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
    
    // Add a publisher using a map of data
    @PostMapping("/addPublisher")
    public ResponseEntity<String> addPublisher(@RequestBody(required = true) Map<String, String> map) {
        try {
            return publisherService.addPublisher(map);
        } catch (Exception e) {
            // Handle exceptions and log the error
            e.printStackTrace();
        }
        
        // If an exception occurs, return an internal server error response
        return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // Get the added publisher
    @GetMapping("/getAddedPublisher")
    public Publisher getAddedPublisher() {
        try {
            return publisherService.getAddedPublisher();
        } catch (Exception e) {
            // Handle exceptions and log the error
            e.printStackTrace();
        }
        
        // If an exception occurs, return an empty Publisher object
        return new Publisher();
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











/*
package com.bookStoreProject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookStoreProject.entity.Publisher;
import com.bookStoreProject.exception.ResourceNotFoundException;
import com.bookStoreProject.exception.ResourceNotModifiedException;
import com.bookStoreProject.services.PublisherService;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Long id) {
        Publisher publisher = publisherService.getPublisherById(id);
        return ResponseEntity.ok(publisher);
    }

    @PostMapping
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        Publisher createdPublisher = publisherService.createPublisher(publisher);
        return ResponseEntity.ok(createdPublisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher publisher) {
        Publisher updatedPublisher = publisherService.updatePublisher(id, publisher);
        return ResponseEntity.ok(updatedPublisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/addPublisher")
    public ResponseEntity<String> addPublisher(@RequestBody(required = true) Map<String, String> map){
    	try {
			return publisherService.addPublisher(map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return new ResponseEntity<String>("Somwthing Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @GetMapping("/getAddedPublisher")
    public Publisher getAddedPublisher() {
    	try {
			return publisherService.getAddedPublisher();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return new Publisher();
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

*/
