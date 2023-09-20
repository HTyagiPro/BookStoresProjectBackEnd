package com.bookStoreProject.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.bookStoreProject.entity.Users;

public interface UsersService {
    List<Users> getAllUsers();
    Users getUserById(Long userId);
    Users createUser(Users user);
    Users updateUser(Long userId, Users user);
    void deleteUser(Long userId);
    
    public ResponseEntity<String> logIn(Map<String, String> map);
	public ResponseEntity<String> signUp(Map<String, String> map);
    
}
