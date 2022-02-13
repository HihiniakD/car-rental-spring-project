package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.repos.UserRepository;
import com.example.carrentalspringproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}
}
