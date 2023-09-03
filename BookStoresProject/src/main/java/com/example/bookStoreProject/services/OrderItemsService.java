package com.example.bookStoreProject.services;

import java.util.List;

import com.example.bookStoreProject.entity.OrderItems;

public interface OrderItemsService {
    List<OrderItems> getAllOrderItems();
    OrderItems getOrderItemById(Long orderItemId);
    OrderItems createOrderItem(OrderItems orderItem);
    OrderItems updateOrderItem(Long orderItemId, OrderItems orderItem);
    void deleteOrderItem(Long orderItemId);
}

