package com.example.bookStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookStoreProject.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Define custom queries or methods if needed
}
