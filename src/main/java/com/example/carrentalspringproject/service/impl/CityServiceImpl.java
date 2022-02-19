package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.model.entity.City;
import com.example.carrentalspringproject.repos.CityRepository;
import com.example.carrentalspringproject.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {this.cityRepository = cityRepository;}


    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }


    @Override
    public City findById(int id) {
        return cityRepository.findCityById(id);
    }
}
