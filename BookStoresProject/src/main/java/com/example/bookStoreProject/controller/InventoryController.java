package com.example.bookStoreProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.bookStoreProject.entity.Inventory;
import com.example.bookStoreProject.exception.ResourceNotFoundException;
import com.example.bookStoreProject.exception.ResourceNotModifiedException;
import com.example.bookStoreProject.services.InventoryService;

import java.util.List;
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
    
    @ExceptionHandler(ResourceNotModifiedException.class)
	public HttpStatus notModifiedExceptionHandler() {
		return HttpStatus.NOT_MODIFIED;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public HttpStatus notFoundExceptionHandler() {
		return HttpStatus.NOT_FOUND;
	}
}
