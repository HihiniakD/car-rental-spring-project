package com.example.carrentalspringproject.service;

import com.example.carrentalspringproject.controller.dto.SignUpUserDto;
import com.example.carrentalspringproject.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    @Query(value = "SELECT * FROM carrental.user WHERE email=?", nativeQuery = true)
    User findByEmail(String email);
    User save(SignUpUserDto signUp);
}
