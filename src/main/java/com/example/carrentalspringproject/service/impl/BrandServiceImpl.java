package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.model.entity.Brand;
import com.example.carrentalspringproject.repos.BrandRepository;
import com.example.carrentalspringproject.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {this.brandRepository = brandRepository;}

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findById(int id) {
        return brandRepository.findBrandById(id);
    }
}
