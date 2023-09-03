package com.example.bookStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookStoreProject.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    // Define custom queries or methods if needed
}
