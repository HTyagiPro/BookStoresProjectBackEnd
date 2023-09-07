package com.example.bookStoreProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.bookStoreProject.entity.Publisher;
import com.example.bookStoreProject.exception.ResourceNotFoundException;
import com.example.bookStoreProject.exception.ResourceNotModifiedException;
import com.example.bookStoreProject.services.PublisherService;

import java.util.List;
import java.util.Map;
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
    
    
    
    @ExceptionHandler(ResourceNotModifiedException.class)
	public HttpStatus notModifiedExceptionHandler() {
		return HttpStatus.NOT_MODIFIED;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public HttpStatus notFoundExceptionHandler() {
		return HttpStatus.NOT_FOUND;
	}
}
