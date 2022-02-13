package com.example.carrentalspringproject.repos;

import com.example.carrentalspringproject.model.entity.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrandRepository extends CrudRepository<Brand, Integer> {
    List<Brand> findAll();
}
