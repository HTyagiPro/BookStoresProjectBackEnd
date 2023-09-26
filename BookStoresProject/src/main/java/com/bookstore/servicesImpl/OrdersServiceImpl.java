package com.bookstore.servicesImpl;

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

import com.bookstore.entity.Book;
import com.bookstore.entity.CartItem;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Inventory;
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
import com.bookstore.services.OrdersService;

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

    // Get a list of all orders
    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    // Get an order by its ID
    @Override
    public Orders getOrderById(Long orderId) {
        return ordersRepository.findById(orderId).orElse(null);
    }

    // Create a new order
    @Override
    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }

    // Update an existing order
    @Override
    public Orders updateOrder(Long orderId, Orders order) {
        if (ordersRepository.existsById(orderId)) {
            order.setOrderID(orderId);
            return ordersRepository.save(order);
        }
        return null;
    }

    // Delete an order by its ID
    @Override
    public void deleteOrder(Long orderId) {
        ordersRepository.deleteById(orderId);
    }

    // Place an order based on user input
    @Override
    public ResponseEntity<String> placeOrder(Map<String, String> map) {
        try {
            Customer customer = customerRepository.getCustomerByEmail(map.get("email"));
            Optional<Book> book = bookRepository.findById(Long.parseLong(map.get("book")));
            Orders order = new Orders();
            order.setCustomer(customer);
            order.setShippingAddress(map.get("address"));
            order.setOrderDate(LocalDateTime.now());
            
            double discount;
            
            discount = 10 * Double.valueOf(book.get().getPrice().toString()) *
                              Integer.parseInt(map.get("quantity")) / 100;
            
            if(map.get("condition").equals("Used")) {
            	discount *= 2;
            }
            
            order.setDiscountAmount(BigDecimal.valueOf(discount));
            double totalAmount = Integer.parseInt(map.get("quantity")) *
                                 Double.valueOf(book.get().getPrice().toString()) +
                                 Double.parseDouble(map.get("tax")) *
                                 Double.valueOf(book.get().getPrice().toString()) *
                                 Integer.parseInt(map.get("quantity")) / 100 -
                                 discount;
            order.setTotalAmount(BigDecimal.valueOf(totalAmount));
            order.setTaxAmount(BigDecimal.valueOf(Double.parseDouble(map.get("tax")) *
                                                  Double.valueOf(book.get().getPrice().toString()) *
                                                  Integer.parseInt(map.get("quantity")) / 100));

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
            payment.setStatus("Pending");

            if (Objects.isNull(payment) || Objects.isNull(order) || Objects.isNull(orderItem) ||
                Objects.isNull(book) || Objects.isNull(customer)) {
                return new ResponseEntity<String>("Order Not Placed !!!", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Inventory inventory = inventoryRepository.getInventoryByBookID(book.get().getBookID());
            if (map.get("condition").equals("New") && inventory.getStockLevelNew() > Integer.parseInt(map.get("quantity"))) {
                inventory.setStockLevelNew(inventory.getStockLevelNew() - Integer.parseInt(map.get("quantity")));
                inventoryRepository.save(inventory);
                ordersRepository.save(order);
                orderItemsRepository.save(orderItem);
                paymentsRepository.save(payment);
                return new ResponseEntity<String>("Proceed for Payments...", HttpStatus.OK);
            } else if (map.get("condition").equals("Used") && inventory.getStockLevelUsed() > Integer.parseInt(map.get("quantity"))) {
                inventory.setStockLevelUsed(inventory.getStockLevelUsed() - Integer.parseInt(map.get("quantity")));
                inventoryRepository.save(inventory);
                ordersRepository.save(order);
                orderItemsRepository.save(orderItem);
                paymentsRepository.save(payment);
                return new ResponseEntity<String>("Proceed for Payments...", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Out Of Stocks!!!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Get the details of placed orders
    @Override
    public ResponseEntity<Map<String, Object>> getPlacedOrderDetails() {
        try {
            if (ordersRepository.getOrderDetails() != null)
                return new ResponseEntity<Map<String, Object>>(ordersRepository.getOrderDetails(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> order = null;
        return new ResponseEntity<Map<String, Object>>(order, HttpStatus.NO_CONTENT);
    }

    // Get the order history
    @Override
    public ResponseEntity<List<Map<Object, Object>>> getOrderHistory() {
        try {
            List<Map<Object, Object>> orders = ordersRepository.getOrderHistory();
            if (!orders.isEmpty())
                return new ResponseEntity<List<Map<Object, Object>>>(orders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<Object, Object>> orders = null;
        return new ResponseEntity<List<Map<Object, Object>>>(orders, HttpStatus.NO_CONTENT);
    }

    // Place an order based on the user's shopping cart
    @Override
    public ResponseEntity<String> placeOrderByCart(Map<String, String> map) {
        Users user = myUserDetailsService.getUserDetails();
        Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
        List<CartItem> cart = cartItemRepository.getCartByCustomerID(customer.getCustomerID());
        
        
        
        
        double subtotal = 0;
        for (CartItem c : cart) {
            subtotal = subtotal + Double.valueOf(c.getBook().getPrice().toString()) * Double.valueOf(c.getQuantity());
            if (c.getConditions().equals("Used")) {
				subtotal = subtotal - 10 * Double.valueOf(c.getBook().getPrice().toString()) * c.getQuantity() / 100;
			}
            System.out.println(c + "--------------" + subtotal);
        }

        double discount = 10 * subtotal / 100;
        
        
        double totalAmount = subtotal + 18 * subtotal / 100 - discount;

        
        
        Orders order = new Orders();
        order.setCustomer(customer);
        order.setShippingAddress(map.get("address"));
        order.setOrderDate(LocalDateTime.now());
        order.setDiscountAmount(BigDecimal.valueOf(discount));
        order.setTotalAmount(BigDecimal.valueOf(totalAmount));
        order.setTaxAmount(BigDecimal.valueOf(18 * subtotal / 100));
        ordersRepository.save(order);

        order = ordersRepository.getLastOrder();

        for (CartItem c : cart) {
            OrderItems orderItem = new OrderItems();
            orderItem.setBook(c.getBook());
            orderItem.setOrder(order);
            orderItem.setCustomer(customer);
            orderItem.setQuantity(c.getQuantity());
            orderItem.setPriceOfUnitQuantity(c.getBook().getPrice());

            Inventory inventory = inventoryRepository.getInventoryByBookID(c.getBook().getBookID());

            if (inventory.getStockLevelNew() >= c.getQuantity() && c.getConditions().equals("New")) {
                inventory.setStockLevelNew(inventory.getStockLevelNew() - c.getQuantity());
                inventoryRepository.save(inventory);
                orderItemsRepository.save(orderItem);
            } else {
                return new ResponseEntity<String>("Item Out of Stock!!!", HttpStatus.BAD_REQUEST);
            }
            
            if (inventory.getStockLevelUsed() >= c.getQuantity() && c.getConditions().equals("Used")) {
                inventory.setStockLevelUsed(inventory.getStockLevelUsed() - c.getQuantity());
                inventoryRepository.save(inventory);
                orderItemsRepository.save(orderItem);
            } else {
                return new ResponseEntity<String>("Item Out of Stock!!!", HttpStatus.BAD_REQUEST);
            }
        }

        Payments payment = new Payments();
        payment.setCustomer(customer);
        payment.setOrder(order);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setAmount(BigDecimal.valueOf(totalAmount));
        payment.setStatus("Paid");
        paymentsRepository.save(payment);

        if (Objects.isNull(payment) || Objects.isNull(order) || Objects.isNull(customer)) {
            return new ResponseEntity<String>("Order Not Placed, Please try after sometime !!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        cartItemRepository.deleteCartByCustomerID(customer.getCustomerID());
        return new ResponseEntity<String>("Order Placed Successfully!!!", HttpStatus.OK);
    }

    // Get the order history for the current user
    @Override
    public ResponseEntity<List<Map<Object, Object>>> getMyOrderHistory() {
        try {
            Users user = myUserDetailsService.getUserDetails();
            Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
            if (customer != null)
                return new ResponseEntity<List<Map<Object, Object>>>(ordersRepository.getMyOrderHistory(customer.getCustomerID()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Map<Object, Object>> orders = null;
        return new ResponseEntity<List<Map<Object, Object>>>(orders, HttpStatus.NOT_FOUND);
    }
}
























//package com.bookStoreProject.servicesImpl;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.bookStoreProject.entity.Book;
//import com.bookStoreProject.entity.CartItem;
//import com.bookStoreProject.entity.Customer;
//import com.bookStoreProject.entity.Inventory;
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
//import com.bookStoreProject.services.OrdersService;
//
//@Service
//public class OrdersServiceImpl implements OrdersService {
//	
//    private final OrdersRepository ordersRepository;
//
//    @Autowired
//    public OrdersServiceImpl(OrdersRepository ordersRepository) {
//        this.ordersRepository = ordersRepository;
//    }
//    
//    
//    @Autowired
//    CustomerRepository customerRepository;
//    
//    @Autowired
//    BookRepository bookRepository;
//    
//    @Autowired
//    OrderItemsRepository orderItemsRepository;
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
//    @Override
//    public List<Orders> getAllOrders() {
//        return ordersRepository.findAll();
//    }
//
//    @Override
//    public Orders getOrderById(Long orderId) {
//        return ordersRepository.findById(orderId).orElse(null);
//    }
//
//    @Override
//    public Orders createOrder(Orders order) {
//        return ordersRepository.save(order);
//    }
//
//    @Override
//    public Orders updateOrder(Long orderId, Orders order) {
//        if (ordersRepository.existsById(orderId)) {
//            order.setOrderID(orderId);
//            return ordersRepository.save(order);
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteOrder(Long orderId) {
//        ordersRepository.deleteById(orderId);
//    }
//
//	@Override
//	public ResponseEntity<String> placeOrder(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		try {
//			Customer customer = customerRepository.getCustomerByEmail(map.get("email"));
//			Optional<Book> book = bookRepository.findById(Long.parseLong(map.get("book")));
//			Orders order = new Orders();
//			order.setCustomer(customer);
//			order.setShippingAddress(map.get("address"));
//			order.setOrderDate(LocalDateTime.now()); 
//			double discount = 10*Double.valueOf((book.get().getPrice()).toString())*Integer.parseInt(map.get("quantity"))/100;
//			order.setDiscountAmount(BigDecimal.valueOf(discount));
//			double totalAmount = Integer.parseInt(map.get("quantity"))*Double.valueOf(book.get().getPrice().toString()) + Double.parseDouble(map.get("tax"))*Double.valueOf(book.get().getPrice().toString())*Integer.parseInt(map.get("quantity"))/100 - discount ;
//			order.setTotalAmount(BigDecimal.valueOf(totalAmount));
//			order.setTaxAmount(BigDecimal.valueOf(Double.parseDouble(map.get("tax"))*Double.valueOf(book.get().getPrice().toString())*Integer.parseInt(map.get("quantity"))/100));
//			
//			
//			OrderItems orderItem = new OrderItems();
//			orderItem.setBook(book.get());
//			orderItem.setOrder(order);
//			orderItem.setCustomer(customer);
//			orderItem.setQuantity(Integer.parseInt(map.get("quantity")));
//			orderItem.setPriceOfUnitQuantity(book.get().getPrice());
//			
//			
//			
//			Payments payment = new Payments();
//			payment.setCustomer(customer);
//			payment.setOrder(order);
//			payment.setPaymentDate(LocalDateTime.now());
//			payment.setAmount(BigDecimal.valueOf(totalAmount));
//			payment.setStatus("Pending");
//			
//			
//			if(Objects.isNull(payment) || Objects.isNull(order) || Objects.isNull(orderItem) || Objects.isNull(book)|| Objects.isNull(customer)) {
//				return new ResponseEntity<String>("Order Not Placed !!!", HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//			
//			Inventory inventory = inventoryRepository.getInventoryByBookID(book.get().getBookID());
//			if(map.get("condition").equals("New") && inventory.getStockLevelNew() > Integer.parseInt(map.get("quantity"))) {
//				inventory.setStockLevelNew(inventory.getStockLevelNew()- Integer.parseInt(map.get("quantity")));
//				inventoryRepository.save(inventory);
//				ordersRepository.save(order);
//				orderItemsRepository.save(orderItem);
//				paymentsRepository.save(payment);
//				return new ResponseEntity<String>("Order Placed Successfully", HttpStatus.OK);
//			}else if(map.get("condition").equals("Used") && inventory.getStockLevelUsed() > Integer.parseInt(map.get("quantity"))) {
//				inventory.setStockLevelUsed(inventory.getStockLevelUsed() - Integer.parseInt(map.get("quantity")));
//				inventoryRepository.save(inventory);
//				ordersRepository.save(order);
//				orderItemsRepository.save(orderItem);
//				paymentsRepository.save(payment);
//				return new ResponseEntity<String>("Order Placed Successfully", HttpStatus.OK);
//			}else {
//				return new ResponseEntity<String>("Out Of Stocks!!!", HttpStatus.INTERNAL_SERVER_ERROR);
//			}			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@Override
//	public ResponseEntity<Map<String, Object>> getPlacedOrderDetails() {
//		// TODO Auto-generated method stub
//		try {
//			if (ordersRepository.getOrderDetails() != null)
//				return new ResponseEntity<Map<String, Object>>(ordersRepository.getOrderDetails(), HttpStatus.OK);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		Map<String, Object> order = null;
//		return new ResponseEntity<Map<String, Object>>(order, HttpStatus.NO_CONTENT);
//	}
//
//	@Override
//	public ResponseEntity<List<Map<Object, Object>>> getOrderHistory() {
//		// TODO Auto-generated method stub
//		try {
//			List<Map<Object, Object>> orders = ordersRepository.getOrderHistory();
//			if(orders.isEmpty() == false)
//				return new ResponseEntity<List<Map<Object, Object>>>(orders, HttpStatus.OK);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		List<Map<Object, Object>> orders = null;
//		return new ResponseEntity<List<Map<Object, Object>>>(orders, HttpStatus.NO_CONTENT);
//	}
//
//	@Override
//	public ResponseEntity<String> placeOrderByCart(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		Users user = myUserDetailsService.getUserDetails();
////		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////		String token = authentication.getName();
////		jwtUtil.extractUsername(token)
//		
//
//		
//		
//		Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
//		List<CartItem> cart = cartItemRepository.getCartByCustomerID(customer.getCustomerID());
//		
//		double subtotal = 0;
//		for(CartItem c :cart) {
//			subtotal = subtotal + Double.valueOf(c.getBook().getPrice().toString())*Double.valueOf(c.getQuantity());
//		}
//		
//		double discount = 10*subtotal/100;
//		
//		double totalAmount = subtotal + 18*subtotal/100 - discount ;
//		
//		
//		Orders order = new Orders();
//		order.setCustomer(customer);
//		order.setShippingAddress(map.get("address"));
//		order.setOrderDate(LocalDateTime.now());
//		order.setDiscountAmount(BigDecimal.valueOf(discount));
//		order.setTotalAmount(BigDecimal.valueOf(totalAmount));
//		order.setTaxAmount(BigDecimal.valueOf(18*subtotal/100));
//		ordersRepository.save(order);
//		
//		order = ordersRepository.getLastOrder();
//		
//		for(CartItem c : cart) {
//			OrderItems orderItem = new OrderItems();
//			orderItem.setBook(c.getBook());
//			orderItem.setOrder(order);
//			orderItem.setCustomer(customer);
//			orderItem.setQuantity(c.getQuantity());
//			orderItem.setPriceOfUnitQuantity(c.getBook().getPrice());
////			orderItemsRepository.save(orderItem);
//			
//			Inventory inventory = inventoryRepository.getInventoryByBookID(c.getBook().getBookID());
//			
//			if(inventory.getStockLevelNew() >= c.getQuantity()) {
//				inventory.setStockLevelNew(inventory.getStockLevelNew()- c.getQuantity());
//				inventoryRepository.save(inventory);
//				orderItemsRepository.save(orderItem);
//				
//			}else {
//				return new ResponseEntity<String>("Item Out of Stock!!!", HttpStatus.BAD_REQUEST);
//			}
//	}
//		
//		Payments payment = new Payments();
//		payment.setCustomer(customer);
//		payment.setOrder(order);
//		payment.setPaymentDate(LocalDateTime.now());
//		payment.setAmount(BigDecimal.valueOf(totalAmount));
//		payment.setStatus("Paid");
//		paymentsRepository.save(payment);
//		
//		
//		if(Objects.isNull(payment) || Objects.isNull(order) || Objects.isNull(customer)) {
//			return new ResponseEntity<String>("Order Not Placed, Please try after sometime !!!", HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//			
//			cartItemRepository.deleteCartByCustomerID(customer.getCustomerID());
//			return new ResponseEntity<String>("Order Placed Successfully!!!", HttpStatus.OK);
//	
//	}
//
//	@Override
//	public ResponseEntity<List<Map<Object, Object>>> getMyOrderHistory() {
//		// TODO Auto-generated method stub
//		try {
//			Users user = myUserDetailsService.getUserDetails();
//			Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
//			if (customer != null)
//			return new ResponseEntity<List<Map<Object, Object>>>(ordersRepository.getMyOrderHistory(customer.getCustomerID()),HttpStatus.OK);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		List<Map<Object, Object>> orders = null;
//		return new ResponseEntity<List<Map<Object, Object>>>(orders,HttpStatus.NOT_FOUND);
//	}
//    
//    
//    
//}
//
