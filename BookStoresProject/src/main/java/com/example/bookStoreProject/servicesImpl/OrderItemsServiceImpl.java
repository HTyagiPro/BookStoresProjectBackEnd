package com.example.bookStoreProject.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bookStoreProject.entity.Customer;
import com.example.bookStoreProject.entity.OrderItems;
import com.example.bookStoreProject.entity.Orders;
import com.example.bookStoreProject.entity.Payments;
import com.example.bookStoreProject.entity.Users;
import com.example.bookStoreProject.jwt.MyUserDetailsService;
import com.example.bookStoreProject.repository.BookRepository;
import com.example.bookStoreProject.repository.CartItemRepository;
import com.example.bookStoreProject.repository.CustomerRepository;
import com.example.bookStoreProject.repository.InventoryRepository;
import com.example.bookStoreProject.repository.OrderItemsRepository;
import com.example.bookStoreProject.repository.OrdersRepository;
import com.example.bookStoreProject.repository.PaymentsRepository;
import com.example.bookStoreProject.services.OrderItemsService;

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

    
    @Override
    public List<OrderItems> getAllOrderItems() {
        return orderItemsRepository.findAll();
    }

    @Override
    public OrderItems getOrderItemById(Long orderItemId) {
        return orderItemsRepository.findById(orderItemId).orElse(null);
    }

    @Override
    public OrderItems createOrderItem(OrderItems orderItem) {
        return orderItemsRepository.save(orderItem);
    }

    @Override
    public OrderItems updateOrderItem(Long orderItemId, OrderItems orderItem) {
        if (orderItemsRepository.existsById(orderItemId)) {
            orderItem.setId(orderItemId);
            return orderItemsRepository.save(orderItem);
        }
        return null;
    }

    @Override
    public void deleteOrderItem(Long orderItemId) {
        orderItemsRepository.deleteById(orderItemId);
    }

	@Override
	public ResponseEntity<String> returnOrderItem(Map<String, String> map) {
		// TODO Auto-generated method stub
		try {
			Users user = myUserDetailsService.getUserDetails();
			Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
			OrderItems orderItems = orderItemsRepository.getOrderItemByBookIdAndOrderID(Long.parseLong(map.get("bookID")), Long.parseLong(map.get("orderID")));
			Orders order = ordersRepository.findById(Long.parseLong(map.get("orderID"))).get();
			//System.out.println("----------------------------> Correct");
			if(Objects.isNull(orderItems)) {
				return new ResponseEntity<String>("No Order-Item found.", HttpStatus.NOT_FOUND);
			}
			
			Payments refund = new Payments();
			refund.setOrder(order);
			refund.setCustomer(customer);
			refund.setAmount(BigDecimal.valueOf(Double.parseDouble(orderItems.getPriceOfUnitQuantity().toString())  * orderItems.getQuantity() + Double.parseDouble(orderItems.getPriceOfUnitQuantity().toString())*0.18 - Double.parseDouble(orderItems.getPriceOfUnitQuantity().toString())*0.1));
			refund.setStatus("Refund_Init.");
			refund.setPaymentDate(LocalDateTime.now());
			paymentsRepository.save(refund);
			
			//orderItemsRepository.returnOrderItem(Long.parseLong(map.get("bookID")), Long.parseLong(map.get("orderID")));
			return new ResponseEntity<String>("Item and Amount Will be return in 3-4 Working Days.", HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Something Went Wrong, Try Again after Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
