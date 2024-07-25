package com.example.demo.service.order_service;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Set;

public interface OrderService {
    void createOrder(Order order);
    Order getOrderById(long idOrder);
    List<Order> getOrderList();
    Set<User> getUserListByIdOrder(long idOrder);
    void deleteOrder(Order order);
}
