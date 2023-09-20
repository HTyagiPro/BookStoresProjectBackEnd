package com.bookStoreProject.servicesImpl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookStoreProject.entity.Customer;
import com.bookStoreProject.repository.CustomerRepository;
import com.bookStoreProject.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Get a list of all customers
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get a customer by their ID
    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    // Create a new customer
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Update an existing customer
    @Override
    public Customer updateCustomer(Long customerId, Customer customer) {
        if (customerRepository.existsById(customerId)) {
            customer.setCustomerID(customerId);
            return customerRepository.save(customer);
        }
        return null;
    }

    // Delete a customer by their ID
    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    // Add a new customer based on provided data
    @Override
    public ResponseEntity<String> addCustomer(Map<String, String> map) {
        try {
            Customer customer = customerRepository.getCustomerByEmail(map.get("email"));

            if (Objects.isNull(customer)) {
                customer = new Customer();
                customer.setCustName(map.get("name"));
                customer.setEmail(map.get("email"));
                customer.setCustAddress(map.get("address"));
                customer.setContactNo(Long.parseLong(map.get("phoneNo")));
                customerRepository.save(customer);
                return new ResponseEntity<String>("Customer Added Successfully!!!", HttpStatus.OK);
            }
            return new ResponseEntity<String>("Customer with email already exists!!!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Get the recently added customer
    @Override
    public ResponseEntity<Customer> getAddedCustomer() {
        try {
            return new ResponseEntity<Customer>(customerRepository.getAddedCustomer(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Customer>(new Customer(), HttpStatus.OK);
    }
}

























//package com.bookStoreProject.servicesImpl;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.bookStoreProject.entity.Customer;
//import com.bookStoreProject.repository.CustomerRepository;
//import com.bookStoreProject.services.CustomerService;
//
//@Service
//public class CustomerServiceImpl implements CustomerService {
//    private final CustomerRepository customerRepository;
//
//    @Autowired
//    public CustomerServiceImpl(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
//
//    @Override
//    public List<Customer> getAllCustomers() {
//        return customerRepository.findAll();
//    }
//
//    @Override
//    public Customer getCustomerById(Long customerId) {
//        return customerRepository.findById(customerId).orElse(null);
//    }
//
//    @Override
//    public Customer createCustomer(Customer customer) {
//        return customerRepository.save(customer);
//    }
//
//    @Override
//    public Customer updateCustomer(Long customerId, Customer customer) {
//        if (customerRepository.existsById(customerId)) {
//            customer.setCustomerID(customerId);
//            return customerRepository.save(customer);
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteCustomer(Long customerId) {
//        customerRepository.deleteById(customerId);
//    }
//
//	@Override
//	public ResponseEntity<String> addCustomer(Map<String, String> map) {
//		try {
//			Customer customer = customerRepository.getCustomerByEmail(map.get("email"));
//			System.out.println(customer);
//			if(Objects.isNull(customer)) {
//				customer = new Customer();
//				customer.setCustName(map.get("name"));
//				customer.setEmail(map.get("email"));
//				customer.setCustAddress(map.get("address"));
//				customer.setContactNo(Long.parseLong(map.get("phoneNo")));
//				customerRepository.save(customer);
//				return new ResponseEntity<String>("Customer Added Successfully!!!", HttpStatus.OK);
//			}
//			return new ResponseEntity<String>("Customer with email already exist!!!", HttpStatus.BAD_REQUEST);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//    	return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@Override
//	public ResponseEntity<Customer> getAddedCustomer() {
//		// TODO Auto-generated method stub
//		try {
//			return new ResponseEntity<Customer>(customerRepository.getAddedCustomer(), HttpStatus.OK);
//		} catch (Exception e) {
//			// TODO: handle exception
//		e.printStackTrace();
//		}
//		return new ResponseEntity<Customer>(new Customer(), HttpStatus.OK);
//	}
//}
//
