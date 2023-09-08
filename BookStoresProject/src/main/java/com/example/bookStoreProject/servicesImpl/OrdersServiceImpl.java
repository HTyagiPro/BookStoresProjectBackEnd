package com.example.bookStoreProject.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bookStoreProject.entity.Book;
import com.example.bookStoreProject.entity.Customer;
import com.example.bookStoreProject.entity.Inventory;
import com.example.bookStoreProject.entity.OrderItems;
import com.example.bookStoreProject.entity.Orders;
import com.example.bookStoreProject.entity.Payments;
import com.example.bookStoreProject.repository.BookRepository;
import com.example.bookStoreProject.repository.CustomerRepository;
import com.example.bookStoreProject.repository.InventoryRepository;
import com.example.bookStoreProject.repository.OrderItemsRepository;
import com.example.bookStoreProject.repository.OrdersRepository;
import com.example.bookStoreProject.repository.PaymentsRepository;
import com.example.bookStoreProject.services.OrdersService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    OrderItemsRepository orderItemsRepository;
    
    @Autowired
    PaymentsRepository paymentsRepository;
    
    @Autowired
    InventoryRepository inventoryRepository; 

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders getOrderById(Long orderId) {
        return ordersRepository.findById(orderId).orElse(null);
    }

    @Override
    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }

    @Override
    public Orders updateOrder(Long orderId, Orders order) {
        if (ordersRepository.existsById(orderId)) {
            order.setOrderID(orderId);
            return ordersRepository.save(order);
        }
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {
        ordersRepository.deleteById(orderId);
    }

	@Override
	public ResponseEntity<String> placeOrder(Map<String, String> map) {
		// TODO Auto-generated method stub
		try {
			Customer customer = customerRepository.getCustomerByEmail(map.get("email"));
			Optional<Book> book = bookRepository.findById(Long.parseLong(map.get("book")));
			Orders order = new Orders();
			order.setCustomer(customer);
			order.setShippingAddress(map.get("address"));
			order.setOrderDate(LocalDateTime.now()); 
			double discount = 10*Double.valueOf((book.get().getPrice()).toString())/100;
			order.setDiscountAmount(BigDecimal.valueOf(discount));
			double totalAmount = Integer.parseInt(map.get("quantity"))*Double.valueOf(book.get().getPrice().toString()) + Double.parseDouble(map.get("tax"))*Double.valueOf(book.get().getPrice().toString()) - discount ;
			order.setTotalAmount(BigDecimal.valueOf(totalAmount));
			order.setTaxAmount(BigDecimal.valueOf(Double.valueOf(map.get("tax"))));
			
			
			OrderItems orderItem = new OrderItems();
			orderItem.setBook(book.get());
			orderItem.setOrder(order);
			orderItem.setCustomer(customer);
			orderItem.setQuantity(Integer.parseInt(map.get("quantity")));
			orderItem.setPriceOfUnitQuantity(book.get().getPrice());
			
			
			
			Payments payment = new Payments();
			payment.setCustomer(customer);
			payment.setOrder(order);
			payment.setPaymentDate(LocalDateTime.now());
			payment.setAmount(BigDecimal.valueOf(totalAmount));
			payment.setStatus("Paid");
			
			
			if(Objects.isNull(payment) || Objects.isNull(order) || Objects.isNull(orderItem) || Objects.isNull(book)|| Objects.isNull(customer)) {
				return new ResponseEntity<String>("Order Not Placed !!!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			Inventory inventory = inventoryRepository.getInventoryByBookID(book.get().getBookID());
			if(map.get("condition").equals("New") && inventory.getStockLevelNew() > Integer.parseInt(map.get("quantity"))) {
				inventory.setStockLevelNew(inventory.getStockLevelNew()- Integer.parseInt(map.get("quantity")));
				inventoryRepository.save(inventory);
				ordersRepository.save(order);
				orderItemsRepository.save(orderItem);
				paymentsRepository.save(payment);
			}else if(map.get("condition").equals("Used") && inventory.getStockLevelUsed() > Integer.parseInt(map.get("quantity"))) {
				inventory.setStockLevelUsed(inventory.getStockLevelUsed() - Integer.parseInt(map.get("quantity")));
				inventoryRepository.save(inventory);
				ordersRepository.save(order);
				orderItemsRepository.save(orderItem);
				paymentsRepository.save(payment);
			}
			
			System.out.println(order);
			System.out.println(orderItem);
			System.out.println(payment);
			System.out.println(book.get());
			System.out.println(customer);
			
			return new ResponseEntity<String>("Order Placed Successfully", HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
    
    
    
}

