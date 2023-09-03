package com.example.bookStoreProject.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookStoreProject.entity.Orders;
import com.example.bookStoreProject.repository.OrdersRepository;
import com.example.bookStoreProject.services.OrdersService;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

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
}

