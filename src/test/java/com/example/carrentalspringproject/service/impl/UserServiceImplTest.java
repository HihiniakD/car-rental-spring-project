package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.model.entity.User;
import com.example.carrentalspringproject.repos.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    public static final String EMAIL = "email@gmail.com";
    public static final String EXCEPTION_MESSAGE = "Invalid username/password or user blocked";


    @Test
    public void shouldThrowServiceExceptionWithBlockedUserLoadUserByUsername(){
        User user = new User();
        user.setBlocked(true);
        Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(user);
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> { userService.loadUserByUsername(EMAIL); }
        );
        assertEquals(EXCEPTION_MESSAGE,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithNotFoundUserLoadUserByUsername(){
        Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(null);

        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> { userService.loadUserByUsername(EMAIL); }
        );

        assertEquals(EXCEPTION_MESSAGE,exception.getMessage());
    }
}