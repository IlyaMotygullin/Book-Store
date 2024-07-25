package com.example.demo.service.order_service;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.repository.order_repository.OrderRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public Order getOrderById(long idOrder) {
        return repository.findById(idOrder).orElse(null);
    }

    @Override
    public List<Order> getOrderList() {
        return repository.findAll();
    }

    @Override
    public Set<User> getUserListByIdOrder(long idOrder) {
        return repository.findById(idOrder).orElse(null).getUserSet();
    }

    @Override
    public void deleteOrder(Order order) {
        repository.delete(order);
    }

}
