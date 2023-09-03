package com.example.bookStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookStoreProject.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Define custom queries or methods if needed
}

