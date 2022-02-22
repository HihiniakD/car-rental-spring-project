package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.model.entity.Category;
import com.example.carrentalspringproject.repos.CategoryRepository;
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
class CategoryServiceImplTest {

    public static final int ID = 1;
    public static final String CATEGORY = "Economy";

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void findAll() {
        Mockito.when(categoryRepository.findAll()).thenReturn(Collections.singletonList(createCategory()));
        List<Category> categories = categoryService.findAll();
        assertNotNull(categories);
    }

    @Test
    void findById() {
        Mockito.when(categoryRepository.findCategoryById(anyInt())).thenReturn(createCategory());
        Category category = categoryService.findById(ID);
        assertNotNull(category);
        assertEquals(CATEGORY, category.getName());
    }

    private Category createCategory(){
        Category category = new Category();
        category.setId(ID);
        category.setName(CATEGORY);
        return category;
    }
}