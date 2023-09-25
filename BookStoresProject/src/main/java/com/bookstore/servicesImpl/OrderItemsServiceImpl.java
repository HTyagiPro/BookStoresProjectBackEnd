package com.bookstore.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderItems;
import com.bookstore.entity.Orders;
import com.bookstore.entity.Payments;
import com.bookstore.entity.Users;
import com.bookstore.jwt.MyUserDetailsService;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.CartItemRepository;
import com.bookstore.repository.CustomerRepository;
import com.bookstore.repository.InventoryRepository;
import com.bookstore.repository.OrderItemsRepository;
import com.bookstore.repository.OrdersRepository;
import com.bookstore.repository.PaymentsRepository;
import com.bookstore.services.OrderItemsService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;

    @Autowired
    public OrderItemsServiceImpl(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }
    
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    PaymentsRepository paymentsRepository;
    
    @Autowired
    InventoryRepository inventoryRepository; 
    
    @Autowired
    MyUserDetailsService myUserDetailsService;
    
    @Autowired
    CartItemRepository cartItemRepository;

    // Get a list of all order items
    @Override
    public List<OrderItems> getAllOrderItems() {
        return orderItemsRepository.findAll();
    }

    // Get an order item by its ID
    @Override
    public OrderItems getOrderItemById(Long orderItemId) {
        return orderItemsRepository.findById(orderItemId).orElse(null);
    }

    // Create a new order item
    @Override
    public OrderItems createOrderItem(OrderItems orderItem) {
        return orderItemsRepository.save(orderItem);
    }

    // Update an existing order item
    @Override
    public OrderItems updateOrderItem(Long orderItemId, OrderItems orderItem) {
        if (orderItemsRepository.existsById(orderItemId)) {
            orderItem.setId(orderItemId);
            return orderItemsRepository.save(orderItem);
        }
        return null;
    }

    // Delete an order item by its ID
    @Override
    public void deleteOrderItem(Long orderItemId) {
        orderItemsRepository.deleteById(orderItemId);
    }

    // Process a return for an order item
    @Override
    public ResponseEntity<String> returnOrderItem(Map<String, String> map) {
        try {
            // Get the current user
            Users user = myUserDetailsService.getUserDetails();
            // Find the customer associated with the user
            Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
            // Find the order item to be returned
            OrderItems orderItems = orderItemsRepository.getOrderItemByBookIdAndOrderID(
                Long.parseLong(map.get("bookID")),
                Long.parseLong(map.get("orderID"))
            );
            // Find the order associated with the order item
            Orders order = ordersRepository.findById(Long.parseLong(map.get("orderID"))).get();

            if (Objects.isNull(orderItems)) {
                return new ResponseEntity<String>("No Order-Item found.", HttpStatus.NOT_FOUND);
            }

            // Calculate the refund amount
            BigDecimal refundAmount = BigDecimal.valueOf((-1)*(
                Double.parseDouble(orderItems.getPriceOfUnitQuantity().toString()) * orderItems.getQuantity() +
                Double.parseDouble(orderItems.getPriceOfUnitQuantity().toString()) * 0.18 -
                Double.parseDouble(orderItems.getPriceOfUnitQuantity().toString()) * 0.1
            ));

            // Create a refund payment record
            Payments refund = new Payments();
            refund.setOrder(order);
            refund.setCustomer(customer);
            refund.setAmount(refundAmount);
            refund.setStatus("Refund_Init.");
            refund.setPaymentDate(LocalDateTime.now());
            paymentsRepository.save(refund);

            // Return a success message
            return new ResponseEntity<String>("Item and Amount Will be returned in 3-4 Working Days.", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Something Went Wrong, Try Again after Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


















//package com.bookStoreProject.servicesImpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.bookStoreProject.entity.Customer;
//import com.bookStoreProject.entity.OrderItems;
//import com.bookStoreProject.entity.Orders;
//import com.bookStoreProject.entity.Payments;
//import com.bookStoreProject.entity.Users;
//import com.bookStoreProject.jwt.MyUserDetailsService;
//import com.bookStoreProject.repository.BookRepository;
//import com.bookStoreProject.repository.CartItemRepository;
//import com.bookStoreProject.repository.CustomerRepository;
//import com.bookStoreProject.repository.InventoryRepository;
//import com.bookStoreProject.repository.OrderItemsRepository;
//import com.bookStoreProject.repository.OrdersRepository;
//import com.bookStoreProject.repository.PaymentsRepository;
//import com.bookStoreProject.services.OrderItemsService;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
//@Service
//public class OrderItemsServiceImpl implements OrderItemsService {
//    private final OrderItemsRepository orderItemsRepository;
//
//    @Autowired
//    public OrderItemsServiceImpl(OrderItemsRepository orderItemsRepository) {
//        this.orderItemsRepository = orderItemsRepository;
//    }
//    
//    @Autowired
//    OrdersRepository ordersRepository;
//
//    @Autowired
//    CustomerRepository customerRepository;
//    
//    @Autowired
//    BookRepository bookRepository;
//    
//    @Autowired
//    PaymentsRepository paymentsRepository;
//    
//    @Autowired
//    InventoryRepository inventoryRepository; 
//    
//    @Autowired
//    MyUserDetailsService myUserDetailsService;
//    
//    @Autowired
//    CartItemRepository cartItemRepository;
//
//    
//    @Override
//    public List<OrderItems> getAllOrderItems() {
//        return orderItemsRepository.findAll();
//    }
//
//    @Override
//    public OrderItems getOrderItemById(Long orderItemId) {
//        return orderItemsRepository.findById(orderItemId).orElse(null);
//    }
//
//    @Override
//    public OrderItems createOrderItem(OrderItems orderItem) {
//        return orderItemsRepository.save(orderItem);
//    }
//
//    @Override
//    public OrderItems updateOrderItem(Long orderItemId, OrderItems orderItem) {
//        if (orderItemsRepository.existsById(orderItemId)) {
//            orderItem.setId(orderItemId);
//            return orderItemsRepository.save(orderItem);
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteOrderItem(Long orderItemId) {
//        orderItemsRepository.deleteById(orderItemId);
//    }
//
//	@Override
//	public ResponseEntity<String> returnOrderItem(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		try {
//			Users user = myUserDetailsService.getUserDetails();
//			Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
//			OrderItems orderItems = orderItemsRepository.getOrderItemByBookIdAndOrderID(Long.parseLong(map.get("bookID")), Long.parseLong(map.get("orderID")));
//			Orders order = ordersRepository.findById(Long.parseLong(map.get("orderID"))).get();
//			//System.out.println("----------------------------> Correct");
//			if(Objects.isNull(orderItems)) {
//				return new ResponseEntity<String>("No Order-Item found.", HttpStatus.NOT_FOUND);
//			}
//			
//			Payments refund = new Payments();
//			refund.setOrder(order);
//			refund.setCustomer(customer);
//			refund.setAmount(BigDecimal.valueOf(Double.parseDouble(orderItems.getPriceOfUnitQuantity().toString())  * orderItems.getQuantity() + Double.parseDouble(orderItems.getPriceOfUnitQuantity().toString())*0.18 - Double.parseDouble(orderItems.getPriceOfUnitQuantity().toString())*0.1));
//			refund.setStatus("Refund_Init.");
//			refund.setPaymentDate(LocalDateTime.now());
//			paymentsRepository.save(refund);
//			
//			//orderItemsRepository.returnOrderItem(Long.parseLong(map.get("bookID")), Long.parseLong(map.get("orderID")));
//			return new ResponseEntity<String>("Item and Amount Will be return in 3-4 Working Days.", HttpStatus.OK);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return new ResponseEntity<String>("Something Went Wrong, Try Again after Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//}
