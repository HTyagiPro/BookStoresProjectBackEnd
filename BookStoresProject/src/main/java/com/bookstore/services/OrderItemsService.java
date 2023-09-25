package com.bookstore.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.bookstore.entity.OrderItems;

public interface OrderItemsService {
    List<OrderItems> getAllOrderItems();
    OrderItems getOrderItemById(Long orderItemId);
    OrderItems createOrderItem(OrderItems orderItem);
    OrderItems updateOrderItem(Long orderItemId, OrderItems orderItem);
    void deleteOrderItem(Long orderItemId);
    public ResponseEntity<String> returnOrderItem(@RequestBody(required = true) Map<String, String> map);
}

