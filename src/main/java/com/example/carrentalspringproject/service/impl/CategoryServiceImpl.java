package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.model.entity.Category;
import com.example.carrentalspringproject.repos.CategoryRepository;
import com.example.carrentalspringproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {this.categoryRepository = categoryRepository;}


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


    @Override
    public Category findById(int id) {
        return categoryRepository.findCategoryById(id);
    }
}
