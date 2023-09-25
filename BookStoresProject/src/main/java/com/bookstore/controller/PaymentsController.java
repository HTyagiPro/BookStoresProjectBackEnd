package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookstore.entity.Payments;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.exception.ResourceNotModifiedException;
import com.bookstore.services.PaymentsService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/payments")
public class PaymentsController {
    private final PaymentsService paymentsService;

    @Autowired
    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    // Get all payments
    @GetMapping
    public List<Payments> getAllPayments() {
        return paymentsService.getAllPayments();
    }

    // Get a payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Payments> getPaymentById(@PathVariable Long id) {
        Payments payment = paymentsService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    // Create a new payment
    @PostMapping
    public ResponseEntity<Payments> createPayment(@RequestBody Payments payment) {
        Payments createdPayment = paymentsService.createPayment(payment);
        return ResponseEntity.ok(createdPayment);
    }

    // Update an existing payment by ID
    @PutMapping("/{id}")
    public ResponseEntity<Payments> updatePayment(@PathVariable Long id, @RequestBody Payments payment) {
        Payments updatedPayment = paymentsService.updatePayment(id, payment);
        return ResponseEntity.ok(updatedPayment);
    }

    // Delete a payment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentsService.deletePayment(id);
        return ResponseEntity.noContent().build();
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

import com.bookStoreProject.entity.Payments;
import com.bookStoreProject.exception.ResourceNotFoundException;
import com.bookStoreProject.exception.ResourceNotModifiedException;
import com.bookStoreProject.services.PaymentsService;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/payments")
public class PaymentsController {
    private final PaymentsService paymentsService;

    @Autowired
    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping
    public List<Payments> getAllPayments() {
        return paymentsService.getAllPayments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payments> getPaymentById(@PathVariable Long id) {
        Payments payment = paymentsService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @PostMapping
    public ResponseEntity<Payments> createPayment(@RequestBody Payments payment) {
        Payments createdPayment = paymentsService.createPayment(payment);
        return ResponseEntity.ok(createdPayment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payments> updatePayment(@PathVariable Long id, @RequestBody Payments payment) {
        Payments updatedPayment = paymentsService.updatePayment(id, payment);
        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentsService.deletePayment(id);
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

*/
