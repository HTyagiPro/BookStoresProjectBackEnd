package com.bookStoreProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookStoreProject.entity.Customer;
import com.bookStoreProject.exception.ResourceNotFoundException;
import com.bookStoreProject.exception.ResourceNotModifiedException;
import com.bookStoreProject.services.CustomerService;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get a customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    // Create a new customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    // Update an existing customer by ID
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    // Delete a customer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
    
    // Add a customer using a map of data
    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody(required = true) Map<String, String> map) {
        try {
            return customerService.addCustomer(map);
        } catch (Exception e) {
            // Handle exceptions and log the error
            e.printStackTrace();
        }
        // If an exception occurs, return an internal server error response
        return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // Get the added customer
    @GetMapping("/getAddedCustomer")
    public ResponseEntity<Customer> getAddedCustomer() {
        try {
            return customerService.getAddedCustomer();
        } catch (Exception e) {
            // Handle exceptions and log the error
            e.printStackTrace();
        }
        // If an exception occurs, return an empty customer and an internal server error response
        return new ResponseEntity<Customer>(new Customer(), HttpStatus.INTERNAL_SERVER_ERROR);
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

import com.bookStoreProject.entity.Customer;
import com.bookStoreProject.exception.ResourceNotFoundException;
import com.bookStoreProject.exception.ResourceNotModifiedException;
import com.bookStoreProject.services.CustomerService;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @PostMapping("/addCustomer")
    public ResponseEntity<String> addBook(@RequestBody(required = true) Map<String, String> map){
    	try {
			return customerService.addCustomer(map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return new ResponseEntity<String>("Somwthing Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    @GetMapping("/getAddedCustomer")
    public ResponseEntity<Customer> getAddedCustomer() {
    	try {
			return customerService.getAddedCustomer();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return new ResponseEntity<Customer>(new Customer(), HttpStatus.INTERNAL_SERVER_ERROR);
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
