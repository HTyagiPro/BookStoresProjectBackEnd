package com.example.bookStoreProject.services;

import java.util.List;

import com.example.bookStoreProject.entity.Customer;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long customerId, Customer customer);
    void deleteCustomer(Long customerId);
}

