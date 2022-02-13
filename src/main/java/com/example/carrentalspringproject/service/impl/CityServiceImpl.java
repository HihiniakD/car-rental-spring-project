package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.repos.CityRepository;
import com.example.carrentalspringproject.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {this.cityRepository = cityRepository;}
}
