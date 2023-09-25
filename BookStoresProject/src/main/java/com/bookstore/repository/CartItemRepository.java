package com.bookstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // You can define custom query methods here if needed.
	@Query(nativeQuery = true,value = "select * from cart_item where customerID=:customerID")
	List<CartItem> getCartByCustomerID(@Param("customerID")Long customerID);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "delete from cart_item where customerID=:customerID")
	void deleteCartByCustomerID(@Param("customerID")Long customerID);
	
	@Query(nativeQuery = true,value = "select * from cart_item where customerID=:customerID AND bookID=:bookID And quantity > 0")
	CartItem getCartByBookIdAndCustomerID(@Param("bookID")Long bookID,@Param("customerID")Long customerID);
	
}
