package com.example.bookStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bookStoreProject.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	// You can define custom query methods here if needed.
	
	@Query(nativeQuery = true, value = "Select * from rating where customer_id=:customerID AND book_id = :bookID")
	public Rating getBookRatingByCustomerID(@Param("customerID") Long customerID, @Param("bookID")Long bookID);
}
