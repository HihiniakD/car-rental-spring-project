package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.model.entity.Brand;
import com.example.carrentalspringproject.repos.BrandRepository;
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
class BrandServiceImplTest {

    public static final int ID = 1;
    public static final String BRAND = "Audi";

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    void findAll() {
        Mockito.when(brandRepository.findAll()).thenReturn(Collections.singletonList(createBrand()));
        List<Brand> brands = brandService.findAll();
        assertNotNull(brands);
    }

    @Test
    void findById() {
        Mockito.when(brandRepository.findBrandById(anyInt())).thenReturn(createBrand());
        Brand brand = brandService.findById(ID);
        assertNotNull(brand);
        assertEquals(BRAND, brand.getName());
    }

    private Brand createBrand(){
        Brand brand = new Brand();
        brand.setId(ID);
        brand.setName(BRAND);
        return brand;
    }
}