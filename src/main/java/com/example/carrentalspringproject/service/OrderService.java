package com.example.carrentalspringproject.service;

import com.example.carrentalspringproject.controller.dto.CarDto;
import com.example.carrentalspringproject.model.entity.Order;
import com.example.carrentalspringproject.model.entity.User;


import java.util.List;

public interface OrderService {
    List<Order> findAllByUserId(int id);
    long calculateTotalPrice(int price, long days);
    long calculateCarDriverPrice(long days);
    public boolean validateOrderPayment(String creditCardName, String creditCardNumber,
                                        String creditCardExpiration, String creditCardCvv);
    Order processOrder(User user, CarDto car, String pickUpDate,
                         String dropOffDate, long totalPrice, boolean withDriver);
}
