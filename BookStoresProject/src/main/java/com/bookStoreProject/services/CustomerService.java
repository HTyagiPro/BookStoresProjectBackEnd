package com.bookStoreProject.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.bookStoreProject.entity.Customer;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long customerId, Customer customer);
    void deleteCustomer(Long customerId);
    public ResponseEntity<String> addCustomer(Map<String, String>map);
    public ResponseEntity<Customer> getAddedCustomer();
}

