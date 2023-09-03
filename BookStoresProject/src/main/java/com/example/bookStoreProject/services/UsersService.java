package com.example.bookStoreProject.services;

import java.util.List;

import com.example.bookStoreProject.entity.Users;

public interface UsersService {
    List<Users> getAllUsers();
    Users getUserById(Long userId);
    Users createUser(Users user);
    Users updateUser(Long userId, Users user);
    void deleteUser(Long userId);
}
