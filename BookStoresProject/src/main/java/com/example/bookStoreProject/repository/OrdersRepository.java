package com.example.bookStoreProject.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
			"SELECT c.cust_Name, c.email, c.cust_Address, o.orderID, o.order_Date, o.shipping_Address,\r\n"
					+ "       o.tax_Amount, o.discount_Amount, p.amount, p.payment_date, p.status,\r\n"
					+ "       oi.quantity, b.bookID, b.title, b.isbnCode, b.price, b.images, b.rating, b.category, b.published_Year, b.book_condition,  \r\n"
					+ "       a.author_Name, pb.publisher_Name\r\n"
					+ "FROM orders o\r\n"
					+ "INNER JOIN payments p ON o.orderID = p.orderID\r\n"
					+ "LEFT JOIN customer c ON o.customerID = c.customerID\r\n"
					+ "LEFT JOIN order_items oi ON o.orderID = oi.orderID\r\n"
					+ "LEFT JOIN books b ON b.bookID = oi.bookID\r\n"
					+ "LEFT JOIN author a ON b.authorID = a.authorID\r\n"
					+ "LEFT JOIN publisher pb ON b.publisherID = pb.publisherID\r\n"
					+ "ORDER BY o.orderID DESC",
	        nativeQuery = true)
	   public List<Map<Object, Object>> getOrderHistory();
	
	
	@Query(nativeQuery = true,
			value = "SELECT * FROM Orders " +
            "ORDER BY OrderID DESC " +
            "LIMIT 1")
	public Orders getLastOrder();
	
	
	@Query(value = 
			"SELECT c.cust_Name, c.email, c.cust_Address, o.orderID, o.order_Date, o.shipping_Address,\r\n"
			+ "       o.tax_Amount, o.discount_Amount, p.amount, p.payment_date, p.status,\r\n"
			+ "       oi.quantity, b.bookID, b.title, b.isbnCode, b.price, b.images, b.rating, b.category, b.published_Year, b.book_condition,  \r\n"
			+ "       a.author_Name, pb.publisher_Name\r\n"
			+ "FROM order_items oi \r\n"
			+ "LEFT JOIN payments p ON oi.orderID = p.orderID\r\n"
			+ "LEFT JOIN customer c ON oi.customerID = c.customerID\r\n"
			+ "LEFT JOIN orders o ON  o.orderID = oi.orderID\r\n"
			+ "LEFT JOIN books b ON b.bookID = oi.bookID\r\n"
			+ "LEFT JOIN author a ON b.authorID = a.authorID\r\n"
			+ "LEFT JOIN publisher pb ON b.publisherID = pb.publisherID\r\n"
			+ "WHERE o.customerID =:customerID",
	        nativeQuery = true)
	   public List<Map<Object, Object>> getMyOrderHistory(@Param("customerID") Long customerID);
	
	
}
