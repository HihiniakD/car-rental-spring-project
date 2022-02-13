package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.repos.OrderRepository;
import com.example.carrentalspringproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {this.orderRepository = orderRepository;}
}
