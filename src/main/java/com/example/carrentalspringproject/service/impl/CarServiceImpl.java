package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.repos.CarRepository;
import com.example.carrentalspringproject.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {this.carRepository = carRepository;}
}
