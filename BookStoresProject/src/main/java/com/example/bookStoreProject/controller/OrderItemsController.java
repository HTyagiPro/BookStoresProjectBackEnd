package com.example.bookStoreProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.bookStoreProject.entity.OrderItems;
import com.example.bookStoreProject.exception.ResourceNotFoundException;
import com.example.bookStoreProject.exception.ResourceNotModifiedException;
import com.example.bookStoreProject.services.OrderItemsService;

import java.util.List;
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
    
    @ExceptionHandler(ResourceNotModifiedException.class)
	public HttpStatus notModifiedExceptionHandler() {
		return HttpStatus.NOT_MODIFIED;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public HttpStatus notFoundExceptionHandler() {
		return HttpStatus.NOT_FOUND;
	}
}
