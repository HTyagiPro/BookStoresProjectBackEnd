package com.example.bookStoreProject.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.bookStoreProject.entity.Orders;

public interface OrdersService {
    List<Orders> getAllOrders();
    Orders getOrderById(Long orderId);
    Orders createOrder(Orders order);
    Orders updateOrder(Long orderId, Orders order);
    void deleteOrder(Long orderId);
    public ResponseEntity<String> placeOrder(Map<String, String> map);
    public Map<String, Object> getPlacedOrderDetails();
    public List<Map<Object, Object>> getOrderHistory();
    public ResponseEntity<String> placeOrderByCart(Map<String, String> map);
}
