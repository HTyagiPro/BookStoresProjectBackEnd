package com.example.bookStoreProject.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bookStoreProject.entity.Book;
import com.example.bookStoreProject.entity.Customer;
import com.example.bookStoreProject.repository.CustomerRepository;
import com.example.bookStoreProject.services.CustomerService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customer) {
        if (customerRepository.existsById(customerId)) {
            customer.setCustomerID(customerId);
            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<String> addCustomer(Map<String, String> map) {
		try {
			Customer customer = customerRepository.getCustomerByEmail(map.get("email"));
			System.out.println(customer);
			if(Objects.isNull(customer)) {
				customer = new Customer();
				customer.setCustName(map.get("name"));
				customer.setEmail(map.get("email"));
				customer.setCustAddress(map.get("address"));
				customer.setContactNo(Long.parseLong(map.get("phoneNo")));
				System.out.println(customer);
				customerRepository.save(customer);
				return new ResponseEntity<String>("Customer Added Successfully!!!", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Customer with email already exist!!!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

