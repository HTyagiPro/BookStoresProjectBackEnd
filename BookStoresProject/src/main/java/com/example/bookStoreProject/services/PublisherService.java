package com.example.bookStoreProject.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.bookStoreProject.entity.Publisher;

public interface PublisherService {
    List<Publisher> getAllPublishers();
    Publisher getPublisherById(Long publisherId);
    Publisher createPublisher(Publisher publisher);
    Publisher updatePublisher(Long publisherId, Publisher publisher);
    void deletePublisher(Long publisherId);
	ResponseEntity<String> addPublisher(Map<String, String> map);
	public Publisher getAddedPublisher();
}

