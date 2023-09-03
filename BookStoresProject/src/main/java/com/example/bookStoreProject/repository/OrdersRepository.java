package com.example.bookStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookStoreProject.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    // Define custom queries or methods if needed
}
