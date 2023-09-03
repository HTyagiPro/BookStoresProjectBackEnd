package com.example.bookStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookStoreProject.entity.OrderItems;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
    // Define custom queries or methods if needed
}

