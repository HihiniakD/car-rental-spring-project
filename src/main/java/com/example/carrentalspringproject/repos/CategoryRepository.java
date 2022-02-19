package com.example.carrentalspringproject.repos;

import com.example.carrentalspringproject.model.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    List<Category> findAll();
    Category findCategoryById(int id);
}
