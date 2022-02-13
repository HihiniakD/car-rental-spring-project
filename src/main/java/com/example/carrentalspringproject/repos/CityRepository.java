package com.example.carrentalspringproject.repos;

import com.example.carrentalspringproject.model.entity.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Integer> {
    List<City> findAll();
}
