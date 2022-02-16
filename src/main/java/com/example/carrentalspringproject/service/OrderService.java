package com.example.carrentalspringproject.service;

import com.example.carrentalspringproject.model.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAllByUserId(int id);
}
