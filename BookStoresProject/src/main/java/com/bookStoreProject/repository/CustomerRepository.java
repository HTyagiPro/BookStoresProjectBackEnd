package com.bookStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookStoreProject.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Define custom queries or methods if needed
	@Query(nativeQuery = true,value = "select * from customer where email=:email")
	Customer getCustomerByEmail(@Param("email")String email);
	
	
	@Query(nativeQuery = true,value = "select * from customer ORDER BY customerID DESC Limit 1")
	Customer getAddedCustomer();
}
