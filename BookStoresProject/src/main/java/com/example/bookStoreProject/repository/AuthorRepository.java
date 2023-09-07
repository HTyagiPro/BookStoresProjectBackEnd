package com.example.bookStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookStoreProject.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Define custom queries or methods if needed
}