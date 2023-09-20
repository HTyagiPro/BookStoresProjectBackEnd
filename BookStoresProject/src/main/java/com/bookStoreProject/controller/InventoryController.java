package com.bookStoreProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookStoreProject.entity.Inventory;
import com.bookStoreProject.exception.ResourceNotFoundException;
import com.bookStoreProject.exception.ResourceNotModifiedException;
import com.bookStoreProject.services.InventoryService;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Get all inventory items
    @GetMapping
    public List<Inventory> getAllInventoryItems() {
        return inventoryService.getAllInventoryItems();
    }

    // Get an inventory item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryItemById(@PathVariable Long id) {
        Inventory inventoryItem = inventoryService.getInventoryItemById(id);
        return ResponseEntity.ok(inventoryItem);
    }

    // Create a new inventory item
    @PostMapping
    public ResponseEntity<Inventory> createInventoryItem(@RequestBody Inventory inventoryItem) {
        Inventory createdInventoryItem = inventoryService.createInventoryItem(inventoryItem);
        return ResponseEntity.ok(createdInventoryItem);
    }

    // Update an existing inventory item by ID
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventoryItem(@PathVariable Long id, @RequestBody Inventory inventoryItem) {
        Inventory updatedInventoryItem = inventoryService.updateInventoryItem(id, inventoryItem);
        return ResponseEntity.ok(updatedInventoryItem);
    }

    // Delete an inventory item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id) {
        inventoryService.deleteInventoryItem(id);
        return ResponseEntity.noContent().build();
    }
    
    // Update inventory using a map of data
    @PostMapping("/updateInv")
    public ResponseEntity<String> updateInv(@RequestBody(required = true) Map<String, String> map) {
        try {
            return inventoryService.updateInv(map);
        } catch (Exception e) {
            // Handle exceptions and log the error
            e.printStackTrace();
        }
        // If an exception occurs, return an internal server error response
        return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
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
 * package com.bookStoreProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookStoreProject.entity.Inventory;
import com.bookStoreProject.exception.ResourceNotFoundException;
import com.bookStoreProject.exception.ResourceNotModifiedException;
import com.bookStoreProject.services.InventoryService;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> getAllInventoryItems() {
        return inventoryService.getAllInventoryItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryItemById(@PathVariable Long id) {
        Inventory inventoryItem = inventoryService.getInventoryItemById(id);
        return ResponseEntity.ok(inventoryItem);
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventoryItem(@RequestBody Inventory inventoryItem) {
        Inventory createdInventoryItem = inventoryService.createInventoryItem(inventoryItem);
        return ResponseEntity.ok(createdInventoryItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventoryItem(@PathVariable Long id, @RequestBody Inventory inventoryItem) {
        Inventory updatedInventoryItem = inventoryService.updateInventoryItem(id, inventoryItem);
        return ResponseEntity.ok(updatedInventoryItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id) {
        inventoryService.deleteInventoryItem(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/updateInv")
    public ResponseEntity<String> updateInv(@RequestBody(required = true)Map<String, String> map){
    	try {
			return inventoryService.updateInv(map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
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