package com.example.carrentalspringproject.repos;

import com.example.carrentalspringproject.model.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer> {
}
