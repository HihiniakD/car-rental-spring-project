package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.exception.ServiceException;
import com.example.carrentalspringproject.model.entity.User;
import com.example.carrentalspringproject.model.entity.enums.Role;
import com.example.carrentalspringproject.repos.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;


    @InjectMocks
    UserServiceImpl userService;

    public static final String EMAIL = "email@gmail.com";
    public static final String PASSWORD = "$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm";
    public static final String NAME = "Steve Rambo";
    public static final String PHONE = "380961111111";
    public static final Role ROLE = Role.USER;
    public static final String BLANK_FIELD = " ";
    public static final String EXCEPTION_MESSAGE_LOGIN = "Invalid username/password or user blocked";
    public static final String EXCEPTION_MESSAGE_NAME_NOT_VALID = "nameNotValid";


    @Test
    public void shouldThrowServiceExceptionWithBlockedUserLoadUserByUsername(){
        User user = new User();
        user.setBlocked(true);
        Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(user);
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> { userService.loadUserByUsername(EMAIL); }
        );
        assertEquals(EXCEPTION_MESSAGE_LOGIN,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithNotFoundUserLoadUserByUsername(){
        Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(null);

        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> { userService.loadUserByUsername(EMAIL); }
        );

        assertEquals(EXCEPTION_MESSAGE_LOGIN,exception.getMessage());
    }

    @Test
    public void findByEmail(){
        Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(createUser());
        User user = userService.findByEmail(EMAIL);
        assertNotNull(user);
    }


    @Test
    public void shouldThrowServiceExceptionWithNullNameCheckUsernameChange(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { userService.checkUsernameChange(new MockHttpSession(),null); }
        );

        assertEquals(EXCEPTION_MESSAGE_NAME_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithBlankNameCheckUsernameChange(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { userService.checkUsernameChange(new MockHttpSession(),BLANK_FIELD); }
        );

        assertEquals(EXCEPTION_MESSAGE_NAME_NOT_VALID,exception.getMessage());
    }

    @Test
    void findByRole() {
        Mockito.when(userRepository.findUsersByRole(ROLE)).thenReturn(Collections.singletonList(createUser()));
        List<User> users = userService.findByRole(ROLE);
        assertNotNull(users);
    }

    private User createUser(){
        User user = new User();
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setName(NAME);
        user.setPhone(PHONE);
        user.setRole(ROLE);
        user.setBlocked(false);
        return user;
    }
}