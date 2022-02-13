package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.repos.BrandRepository;
import com.example.carrentalspringproject.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {this.brandRepository = brandRepository;}
}
