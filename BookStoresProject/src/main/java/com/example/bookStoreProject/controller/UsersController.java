package com.example.bookStoreProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.bookStoreProject.entity.Users;
import com.example.bookStoreProject.exception.ResourceNotFoundException;
import com.example.bookStoreProject.exception.ResourceNotModifiedException;
import com.example.bookStoreProject.services.UsersService;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody(required = true)Map<String, String>map) {    	
    	return usersService.logIn(map);
    }

    
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true)Map<String, String>map){
    	try {
    		
			return usersService.signUp(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Users user = usersService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = usersService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
        Users updatedUser = usersService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    /*
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> map){
    	try {
    		return usersService.logIn(map);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return new ResponseEntity<String>("Invalid Login", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    */
    
    
    
    
    
    @ExceptionHandler(ResourceNotModifiedException.class)
	public HttpStatus notModifiedExceptionHandler() {
		return HttpStatus.NOT_MODIFIED;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public HttpStatus notFoundExceptionHandler() {
		return HttpStatus.NOT_FOUND;
	}
}

