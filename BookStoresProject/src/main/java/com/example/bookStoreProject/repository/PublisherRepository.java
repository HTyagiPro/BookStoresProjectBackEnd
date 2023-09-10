package com.example.bookStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.bookStoreProject.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    // Define custom queries or methods if needed
	@Query(nativeQuery = true,
			value = "SELECT * FROM Publisher " +
            "ORDER BY publisherID DESC " +
            "LIMIT 1")
	public Publisher getAddedPublisher();
}

