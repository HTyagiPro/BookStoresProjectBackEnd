package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Inventory;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Define custom queries or methods if needed
	@Query(nativeQuery = true,value = "select * from inventory where bookID=:bookID")
	Inventory getInventoryByBookID(@Param("bookID")long bookID);
}

