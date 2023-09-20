package com.bookStoreProject.servicesImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookStoreProject.entity.Publisher;
import com.bookStoreProject.repository.PublisherRepository;
import com.bookStoreProject.services.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getPublisherById(Long publisherId) {
        return publisherRepository.findById(publisherId).orElse(null);
    }

    @Override
    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher updatePublisher(Long publisherId, Publisher publisher) {
        if (publisherRepository.existsById(publisherId)) {
            publisher.setPublisherID(publisherId);
            return publisherRepository.save(publisher);
        }
        return null;
    }

    @Override
    public void deletePublisher(Long publisherId) {
        publisherRepository.deleteById(publisherId);
    }

	@Override
	public ResponseEntity<String> addPublisher(Map<String, String> map) {
		try {
			Publisher publisher = new Publisher();
			publisher.setPublisherName(map.get("publisherName"));
			publisherRepository.save(publisher);
			return new ResponseEntity<String>("Publisher Added Successfully!!!", HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public Publisher getAddedPublisher() {
		// TODO Auto-generated method stub
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return publisherRepository.getAddedPublisher();
	}
}
