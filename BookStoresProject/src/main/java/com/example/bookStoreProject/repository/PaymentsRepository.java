package com.example.bookStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookStoreProject.entity.Payments;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {
    // Define custom queries or methods if needed
}

