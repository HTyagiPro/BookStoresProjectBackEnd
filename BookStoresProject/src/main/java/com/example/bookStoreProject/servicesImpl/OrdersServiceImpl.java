package com.example.bookStoreProject.servicesImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bookStoreProject.entity.Book;
import com.example.bookStoreProject.entity.CartItem;
import com.example.bookStoreProject.entity.Customer;
import com.example.bookStoreProject.entity.Inventory;
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
import com.example.bookStoreProject.services.OrdersService;

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
    
    @Autowired
    MyUserDetailsService myUserDetailsService;
    
    @Autowired
    CartItemRepository cartItemRepository;

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
			double discount = 10*Double.valueOf((book.get().getPrice()).toString())*Integer.parseInt(map.get("quantity"))/100;
			order.setDiscountAmount(BigDecimal.valueOf(discount));
			double totalAmount = Integer.parseInt(map.get("quantity"))*Double.valueOf(book.get().getPrice().toString()) + Double.parseDouble(map.get("tax"))*Double.valueOf(book.get().getPrice().toString())*Integer.parseInt(map.get("quantity"))/100 - discount ;
			order.setTotalAmount(BigDecimal.valueOf(totalAmount));
			order.setTaxAmount(BigDecimal.valueOf(Double.parseDouble(map.get("tax"))*Double.valueOf(book.get().getPrice().toString())*Integer.parseInt(map.get("quantity"))/100));
			
			
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
				return new ResponseEntity<String>("Order Placed Successfully", HttpStatus.OK);
			}else if(map.get("condition").equals("Used") && inventory.getStockLevelUsed() > Integer.parseInt(map.get("quantity"))) {
				inventory.setStockLevelUsed(inventory.getStockLevelUsed() - Integer.parseInt(map.get("quantity")));
				inventoryRepository.save(inventory);
				ordersRepository.save(order);
				orderItemsRepository.save(orderItem);
				paymentsRepository.save(payment);
				return new ResponseEntity<String>("Order Placed Successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
			}			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Map<String, Object>> getPlacedOrderDetails() {
		// TODO Auto-generated method stub
		try {
			if (ordersRepository.getOrderDetails() != null)
				return new ResponseEntity<Map<String, Object>>(ordersRepository.getOrderDetails(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
		}
		Map<String, Object> order = null;
		return new ResponseEntity<Map<String, Object>>(order, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<List<Map<Object, Object>>> getOrderHistory() {
		// TODO Auto-generated method stub
		try {
			List<Map<Object, Object>> orders = ordersRepository.getOrderHistory();
			if(orders.isEmpty() == false)
				return new ResponseEntity<List<Map<Object, Object>>>(orders, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		List<Map<Object, Object>> orders = null;
		return new ResponseEntity<List<Map<Object, Object>>>(orders, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<String> placeOrderByCart(Map<String, String> map) {
		// TODO Auto-generated method stub
		Users user = myUserDetailsService.getUserDetails();
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String token = authentication.getName();
//		jwtUtil.extractUsername(token)
		

		
		
		Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
		List<CartItem> cart = cartItemRepository.getCartByCustomerID(customer.getCustomerID());
		
		double subtotal = 0;
		for(CartItem c :cart) {
			subtotal = subtotal + Double.valueOf(c.getBook().getPrice().toString())*Double.valueOf(c.getQuantity());
		}
		
		double discount = 10*subtotal/100;
		
		double totalAmount = subtotal + 18*subtotal/100 - discount ;
		
		
		Orders order = new Orders();
		order.setCustomer(customer);
		order.setShippingAddress(map.get("address"));
		order.setOrderDate(LocalDateTime.now());
		order.setDiscountAmount(BigDecimal.valueOf(discount));
		order.setTotalAmount(BigDecimal.valueOf(totalAmount));
		order.setTaxAmount(BigDecimal.valueOf(18*subtotal/100));
		ordersRepository.save(order);
		
		order = ordersRepository.getLastOrder();
		
		for(CartItem c : cart) {
			OrderItems orderItem = new OrderItems();
			orderItem.setBook(c.getBook());
			orderItem.setOrder(order);
			orderItem.setCustomer(customer);
			orderItem.setQuantity(c.getQuantity());
			orderItem.setPriceOfUnitQuantity(c.getBook().getPrice());
			orderItemsRepository.save(orderItem);
			
			//Inventory inventory = inventoryRepository.getInventoryByBookID(c.getBook().getBookID());
			
			
//			if(inventory.getStockLevelNew() > c.getQuantity()) {
//				System.out.println("Here wrong -----------------");
//				inventory.setStockLevelNew(inventory.getStockLevelNew()- c.getQuantity());
//				inventoryRepository.save(inventory);
//				orderItemsRepository.save(orderItem);
//				
//			}else {
//				return new ResponseEntity<String>("Item Out of Stock!!!", HttpStatus.BAD_REQUEST);
//			}
	}
		
		Payments payment = new Payments();
		payment.setCustomer(customer);
		payment.setOrder(order);
		payment.setPaymentDate(LocalDateTime.now());
		payment.setAmount(BigDecimal.valueOf(totalAmount));
		payment.setStatus("Paid");
		paymentsRepository.save(payment);
		
		
		if(Objects.isNull(payment) || Objects.isNull(order) || Objects.isNull(customer)) {
			return new ResponseEntity<String>("Order Not Placed, Please try after sometime !!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
			cartItemRepository.deleteCartByCustomerID(customer.getCustomerID());
			return new ResponseEntity<String>("Order Placed Successfully!!!", HttpStatus.OK);
	
	}

	@Override
	public ResponseEntity<List<Map<Object, Object>>> getMyOrderHistory() {
		// TODO Auto-generated method stub
		try {
			Users user = myUserDetailsService.getUserDetails();
			Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
			if (customer != null)
			return new ResponseEntity<List<Map<Object, Object>>>(ordersRepository.getMyOrderHistory(customer.getCustomerID()),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		List<Map<Object, Object>> orders = null;
		return new ResponseEntity<List<Map<Object, Object>>>(orders,HttpStatus.NOT_FOUND);
	}
    
    
    
}

