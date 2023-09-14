package com.example.bookStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookStoreProject.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	// You can define custom query methods here if needed.
}
