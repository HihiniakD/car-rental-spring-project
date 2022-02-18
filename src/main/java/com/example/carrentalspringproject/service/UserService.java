package com.example.carrentalspringproject.service;

import com.example.carrentalspringproject.controller.dto.SignUpUserDto;
import com.example.carrentalspringproject.model.entity.User;
import com.example.carrentalspringproject.model.entity.enums.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService extends UserDetailsService {
    @Query(value = "SELECT * FROM carrental.user WHERE email=?", nativeQuery = true)
    User findByEmail(String email);
    User save(SignUpUserDto signUp);
    User checkUsernameChange(HttpSession session, String name);
    List<User> findByRole(Role role);
    void changeBlockedStatus(int userId, boolean blocked);
    User createManager(SignUpUserDto manager);
}
