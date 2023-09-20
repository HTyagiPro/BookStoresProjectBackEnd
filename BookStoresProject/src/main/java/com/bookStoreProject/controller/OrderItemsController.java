package com.bookStoreProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookStoreProject.entity.OrderItems;
import com.bookStoreProject.exception.ResourceNotFoundException;
import com.bookStoreProject.exception.ResourceNotModifiedException;
import com.bookStoreProject.services.OrderItemsService;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/order-items")
public class OrderItemsController {
    private final OrderItemsService orderItemsService;

    @Autowired
    public OrderItemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    // Get all order items
    @GetMapping
    public List<OrderItems> getAllOrderItems() {
        return orderItemsService.getAllOrderItems();
    }

    // Get an order item by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderItems> getOrderItemById(@PathVariable Long id) {
        OrderItems orderItem = orderItemsService.getOrderItemById(id);
        return ResponseEntity.ok(orderItem);
    }

    // Create a new order item
    @PostMapping
    public ResponseEntity<OrderItems> createOrderItem(@RequestBody OrderItems orderItem) {
        OrderItems createdOrderItem = orderItemsService.createOrderItem(orderItem);
        return ResponseEntity.ok(createdOrderItem);
    }

    // Update an existing order item by ID
    @PutMapping("/{id}")
    public ResponseEntity<OrderItems> updateOrderItem(@PathVariable Long id, @RequestBody OrderItems orderItem) {
        OrderItems updatedOrderItem = orderItemsService.updateOrderItem(id, orderItem);
        return ResponseEntity.ok(updatedOrderItem);
    }

    // Delete an order item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemsService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
    
    // Return an order item using a map of data
    @PostMapping("/return")
    public ResponseEntity<String> returnOrderItem(@RequestBody(required = true) Map<String, String> map) {
        try {
            return orderItemsService.returnOrderItem(map);
        } catch (Exception e) {
            // Handle exceptions and log the error
            e.printStackTrace();
        }
        
        // If an exception occurs, return an internal server error response
        return new ResponseEntity<String>("Something Went Wrong, Try Again after Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
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

import com.bookStoreProject.entity.OrderItems;
import com.bookStoreProject.exception.ResourceNotFoundException;
import com.bookStoreProject.exception.ResourceNotModifiedException;
import com.bookStoreProject.services.OrderItemsService;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/order-items")
public class OrderItemsController {
    private final OrderItemsService orderItemsService;

    @Autowired
    public OrderItemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @GetMapping
    public List<OrderItems> getAllOrderItems() {
        return orderItemsService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItems> getOrderItemById(@PathVariable Long id) {
        OrderItems orderItem = orderItemsService.getOrderItemById(id);
        return ResponseEntity.ok(orderItem);
    }

    @PostMapping
    public ResponseEntity<OrderItems> createOrderItem(@RequestBody OrderItems orderItem) {
        OrderItems createdOrderItem = orderItemsService.createOrderItem(orderItem);
        return ResponseEntity.ok(createdOrderItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItems> updateOrderItem(@PathVariable Long id, @RequestBody OrderItems orderItem) {
        OrderItems updatedOrderItem = orderItemsService.updateOrderItem(id, orderItem);
        return ResponseEntity.ok(updatedOrderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemsService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/return")
    public ResponseEntity<String> returnOrderItem(@RequestBody(required = true) Map<String, String> map) {
        try {
			return orderItemsService.returnOrderItem(map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
        return new ResponseEntity<String>("Something Went Wrong, Try Again after Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
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