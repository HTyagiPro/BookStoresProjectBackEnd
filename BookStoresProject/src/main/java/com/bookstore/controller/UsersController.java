package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookstore.entity.Users;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.exception.ResourceNotModifiedException;
import com.bookstore.jwt.JwtFilter;
import com.bookstore.services.UsersService;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    
    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // Log in a user
    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody(required = true) Map<String, String> map) {    	
        return usersService.logIn(map);
    }

    // Sign up a user
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> map) {
        try {
            return usersService.signUp(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Get all users
    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Users user = usersService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = usersService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Update an existing user by ID
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
        Users updatedUser = usersService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Check if a user is an admin
    @GetMapping("/isAdmin")
    public ResponseEntity<String> isAdmin() {
        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<String>("1$1#", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("2#2#", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Exception handler for ResourceNotModifiedException
    @ExceptionHandler(ResourceNotModifiedException.class)
    public HttpStatus notModifiedExceptionHandler() {
        return HttpStatus.NOT_MODIFIED;
    }

    // Exception handler for ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public HttpStatus notFoundExceptionHandler() {
        return HttpStatus.NOT_FOUND;
    }
}










/*
package com.bookStoreProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookStoreProject.entity.Users;
import com.bookStoreProject.exception.ResourceNotFoundException;
import com.bookStoreProject.exception.ResourceNotModifiedException;
import com.bookStoreProject.jwt.JwtFilter;
import com.bookStoreProject.services.UsersService;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    
    @Autowired
    private JwtFilter jwtFilter;

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
    
    
    
    @GetMapping("/isAdmin")
    public ResponseEntity<String> isAdmin() {
    	try {
			if (jwtFilter.isAdmin()) {
				return new ResponseEntity<String>("1$1#",HttpStatus.OK);
			
			}else return new ResponseEntity<String>("2#2#",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    @ExceptionHandler(ResourceNotModifiedException.class)
	public HttpStatus notModifiedExceptionHandler() {
		return HttpStatus.NOT_MODIFIED;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public HttpStatus notFoundExceptionHandler() {
		return HttpStatus.NOT_FOUND;
	}
}

*/