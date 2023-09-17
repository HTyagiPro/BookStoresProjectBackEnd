package com.example.bookStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bookStoreProject.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	// You can define custom query methods here if needed.
	
	@Query(nativeQuery = true, value = "Select avg(rating) from rating where book_id = :bookID GROUP BY book_id")
	public double getBookRatingByBookID(@Param("bookID")Long bookID);
	
	@Query(nativeQuery = true, value = "Select * from rating where book_id = :bookID AND customer_id =:customerID")
	public Rating getBookRatingByBookIDAndCustomerID(@Param("bookID")Long bookID, @Param("customerID")Long customerID);
	
	
}
