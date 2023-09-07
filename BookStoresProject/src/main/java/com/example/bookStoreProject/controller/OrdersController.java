package com.example.bookStoreProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.bookStoreProject.entity.Orders;
import com.example.bookStoreProject.exception.ResourceNotFoundException;
import com.example.bookStoreProject.exception.ResourceNotModifiedException;
import com.example.bookStoreProject.services.OrdersService;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public List<Orders> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders order = ordersService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        Orders createdOrder = ordersService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders order) {
        Orders updatedOrder = ordersService.updateOrder(id, order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        ordersService.deleteOrder(id);
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
