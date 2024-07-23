package com.example.demo.service.order_service;

import com.example.demo.entity.Order;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);
    List<Order> getOrderList();
}
