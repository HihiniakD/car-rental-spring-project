package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.controller.dto.SignUpUserDto;
import com.example.carrentalspringproject.exception.ServiceException;
import com.example.carrentalspringproject.model.entity.User;
import com.example.carrentalspringproject.model.entity.enums.Role;
import com.example.carrentalspringproject.repos.UserRepository;
import com.example.carrentalspringproject.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.carrentalspringproject.controller.Constants.*;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public User save(SignUpUserDto signUp) {
        User user = new User();
        user.setEmail(signUp.getEmail());
        user.setPassword(passwordEncoder.encode(signUp.getPassword()));
        user.setName(signUp.getName());
        user.setPhone(signUp.getPhone());
        user.setBlocked(false);
        user.setRole(Role.USER);
        logger.info(NEW_USER_CREATED + user.getEmail());
        return userRepository.save(user);
    }


    @Override
    @Transactional
    public User checkUsernameChange(HttpSession session, String name) {
        if (name == null || name.isBlank())
            throw new ServiceException(NAME_NOT_VALID);

        User user = (User) session.getAttribute(USER_PARAMETER);
        if (!user.getName().equals(name)){
            userRepository.changeUserNameById(name, user.getId());
            user.setName(name);
        }
        return user;
    }


    @Override
    public List<User> findByRole(Role role) {
        return userRepository.findUsersByRole(role);
    }


    @Override
    @Transactional
    public void changeBlockedStatus(int userId, boolean blocked) {
        userRepository.changeBlockedStatusById(!blocked, userId);
    }


    @Override
    public User createManager(SignUpUserDto manager) {
        User user = new User();
        user.setEmail(manager.getEmail());
        user.setPassword(passwordEncoder.encode(manager.getPassword()));
        user.setName(manager.getName());
        user.setPhone(manager.getPhone());
        user.setBlocked(false);
        user.setRole(Role.MANAGER);
        logger.info(NEW_MANAGER_CREATED + user.getEmail());
        return userRepository.save(user);
    }


    @Override
    public Page<User> findUserPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return userRepository.findAll(pageable);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null || user.isBlocked()) {
            logger.info(FAILED_LOGIN + email);
            throw new UsernameNotFoundException("Invalid username/password or user blocked");
        }
        logger.info(SUCCESS_LOGIN + email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), mapRoleToAuthorities(user.getRole()));
    }


    private Collection < ? extends GrantedAuthority > mapRoleToAuthorities(Role userRole) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
        list.add(simpleGrantedAuthority);
        return list;
    }
}
