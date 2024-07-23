package com.example.demo.service.order_service;

import com.example.demo.entity.Order;
import com.example.demo.repository.order_repository.OrderRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "orderService")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {
    final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createOrder(Order order) {
        repository.save(order);
    }

    @Override
    public List<Order> getOrderList() {
        return repository.findAll();
    }
}
