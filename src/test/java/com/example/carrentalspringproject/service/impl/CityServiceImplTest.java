package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.model.entity.City;
import com.example.carrentalspringproject.repos.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    public static final int ID = 1;
    public static final String CITY = "Kyiv";

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    void findAll() {
        Mockito.when(cityRepository.findAll()).thenReturn(Collections.singletonList(createCity()));
        List<City> cities = cityService.findAll();
        assertNotNull(cities);
    }

    @Test
    void findById() {
        Mockito.when(cityRepository.findCityById(anyInt())).thenReturn(createCity());
        City city = cityService.findById(ID);
        assertNotNull(city);
        assertEquals(CITY, city.getName());
    }

    private City createCity(){
        City city = new City();
        city.setId(ID);
        city.setName(CITY);
        return city;
    }
}