package com.example.bookStoreProject.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.bookStoreProject.entity.OrderItems;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from order_items where bookID=:bookID AND orderID=:orderID ")
	void returnOrderItem(@Param("bookID") long bookID,@Param("orderID") long orderID);
    // Define custom queries or methods if needed
	
	@Query(nativeQuery = true, value = "select * from order_items where bookID=:bookID AND orderID=:orderID ")
	OrderItems getOrderItemByBookIdAndOrderID(@Param("bookID") long bookID,@Param("orderID") long orderID);
    
}

