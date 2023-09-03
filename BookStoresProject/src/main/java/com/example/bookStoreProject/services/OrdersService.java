package com.example.bookStoreProject.services;

import java.util.List;

import com.example.bookStoreProject.entity.Orders;

public interface OrdersService {
    List<Orders> getAllOrders();
    Orders getOrderById(Long orderId);
    Orders createOrder(Orders order);
    Orders updateOrder(Long orderId, Orders order);
    void deleteOrder(Long orderId);
}
