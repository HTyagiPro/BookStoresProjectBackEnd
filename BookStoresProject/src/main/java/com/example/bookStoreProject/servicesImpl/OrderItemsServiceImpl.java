package com.example.bookStoreProject.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookStoreProject.entity.OrderItems;
import com.example.bookStoreProject.repository.OrderItemsRepository;
import com.example.bookStoreProject.services.OrderItemsService;

import java.util.List;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;

    @Autowired
    public OrderItemsServiceImpl(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    @Override
    public List<OrderItems> getAllOrderItems() {
        return orderItemsRepository.findAll();
    }

    @Override
    public OrderItems getOrderItemById(Long orderItemId) {
        return orderItemsRepository.findById(orderItemId).orElse(null);
    }

    @Override
    public OrderItems createOrderItem(OrderItems orderItem) {
        return orderItemsRepository.save(orderItem);
    }

    @Override
    public OrderItems updateOrderItem(Long orderItemId, OrderItems orderItem) {
        if (orderItemsRepository.existsById(orderItemId)) {
            orderItem.setId(orderItemId);
            return orderItemsRepository.save(orderItem);
        }
        return null;
    }

    @Override
    public void deleteOrderItem(Long orderItemId) {
        orderItemsRepository.deleteById(orderItemId);
    }
}
