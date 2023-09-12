package com.example.bookStoreProject.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bookStoreProject.entity.Author;
import com.example.bookStoreProject.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    // Define custom queries or methods if needed
	@Query(value = 
			"SELECT c.cust_Name, c.email, c.cust_Address, o.orderID, o.order_Date, o.shipping_Address, " +
                    "o.tax_Amount, o.discount_Amount, p.amount, p.payment_date, p.status, " +
                    "oi.quantity, b.title, b.isbnCode, b.price " +
                    "FROM orders o " +
                    "INNER JOIN payments p ON o.orderID = p.orderID " +
                    "LEFT JOIN customer c ON o.customerID = c.customerID " +
                    "LEFT JOIN order_items oi ON o.orderID = oi.orderID " +
                    "LEFT JOIN books b ON b.bookID = oi.bookID " +
                    "ORDER BY o.orderID DESC " +
                    "LIMIT 1",
	        nativeQuery = true)
	   public Map<String, Object> getOrderDetails();
	
	@Query(value = 
			"SELECT c.cust_Name, c.email, c.cust_Address, o.orderID, o.order_Date, o.shipping_Address, " +
                    "o.tax_Amount, o.discount_Amount, p.amount, p.payment_date, p.status, " +
                    "oi.quantity, b.title, b.isbnCode, b.price " +
                    "FROM orders o " +
                    "INNER JOIN payments p ON o.orderID = p.orderID " +
                    "LEFT JOIN customer c ON o.customerID = c.customerID " +
                    "LEFT JOIN order_items oi ON o.orderID = oi.orderID " +
                    "LEFT JOIN books b ON b.bookID = oi.bookID ",
	        nativeQuery = true)
	   public List<Map<Object, Object>> getOrderHistory();
	
	
	@Query(nativeQuery = true,
			value = "SELECT * FROM Orders " +
            "ORDER BY OrderID DESC " +
            "LIMIT 1")
	public Orders getLastOrder();
	
	
	@Query(value = 
			"SELECT c.cust_Name, c.email, c.cust_Address, o.orderID, o.order_Date, o.shipping_Address,\r\n"
			+ "                    o.tax_Amount, o.discount_Amount, p.amount, p.payment_date, p.status,\r\n"
			+ "				oi.quantity, b.title, b.isbnCode, b.price\r\n"
			+ "                   FROM orders o \r\n"
			+ "                    INNER JOIN payments p ON o.orderID = p.orderID \r\n"
			+ "                    LEFT JOIN customer c ON o.customerID = c.customerID \r\n"
			+ "                    LEFT JOIN order_items oi ON o.orderID = oi.orderID \r\n"
			+ "                    LEFT JOIN books b ON b.bookID = oi.bookID where o.customerID=:customerID",
	        nativeQuery = true)
	   public List<Map<Object, Object>> getMyOrderHistory(@Param("customerID") Long customerID);
	
	
}
