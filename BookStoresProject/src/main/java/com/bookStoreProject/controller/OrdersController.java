package com.bookStoreProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookStoreProject.entity.Orders;
import com.bookStoreProject.exception.ResourceNotFoundException;
import com.bookStoreProject.exception.ResourceNotModifiedException;
import com.bookStoreProject.services.OrdersService;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    // Get all orders
    @GetMapping
    public List<Orders> getAllOrders() {
        return ordersService.getAllOrders();
    }

    // Get an order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders order = ordersService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        Orders createdOrder = ordersService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    // Update an existing order by ID
    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders order) {
        Orders updatedOrder = ordersService.updateOrder(id, order);
        return ResponseEntity.ok(updatedOrder);
    }

    // Delete an order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
    
    // Place an order using a map of data
    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody(required = true) Map<String, String> map) {
        try {
            return ordersService.placeOrder(map);
        } catch (Exception e) {
            // Handle exceptions and log the error
            e.printStackTrace();
        }
        
        // If an exception occurs, return an internal server error response
        return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // Place an order using a map of data from a cart
    @PostMapping("/placeOrderByCart")
    public ResponseEntity<String> placeOrderByCart(@RequestBody(required = true) Map<String, String> map) {
        try {
            return ordersService.placeOrderByCart(map);
        } catch (Exception e) {
            // Handle exceptions and log the error
            e.printStackTrace();
        }
        
        // If an exception occurs, return an internal server error response
        return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // Get order history
    @GetMapping("/getOrderHistory")
    public ResponseEntity<List<Map<Object, Object>>> getOrderHistory() {
        return ordersService.getOrderHistory();
    }
    
    // Get details of the last placed order
    @GetMapping("/getPlacedOrder")
    public ResponseEntity<Map<String, Object>> getPlacedOrder() {
        return ordersService.getPlacedOrderDetails();
    }

    // Get order history for the current user
    @GetMapping("/getMyOrderHistory")
    public ResponseEntity<List<Map<Object, Object>>> getMyOrderHistory() {
        return ordersService.getMyOrderHistory();
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

import com.bookStoreProject.entity.Orders;
import com.bookStoreProject.exception.ResourceNotFoundException;
import com.bookStoreProject.exception.ResourceNotModifiedException;
import com.bookStoreProject.services.OrdersService;

import java.util.List;
import java.util.Map;
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
    
    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody(required = true) Map<String, String> map){
    	try {
			return ordersService.placeOrder(map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return new ResponseEntity<String>("Somwthing Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PostMapping("/placeOrderByCart")
    public ResponseEntity<String> placeOrderByCart(@RequestBody(required = true) Map<String, String> map){
    	try {
			return ordersService.placeOrderByCart(map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return new ResponseEntity<String>("Somwthing Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
      
    @GetMapping("/getOrderHistory")
    public ResponseEntity<List<Map<Object, Object>>> getOrderHistory(){
    	return ordersService.getOrderHistory();
    }
    
    @GetMapping("/getPlacedOrder")
    public ResponseEntity<Map<String, Object>> getPlacedOrder(){
    	return ordersService.getPlacedOrderDetails();
    }
    

    @GetMapping("/getMyOrderHistory")
    public ResponseEntity<List<Map<Object, Object>>> getMyOrderHistory(){
    	return ordersService.getMyOrderHistory();
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