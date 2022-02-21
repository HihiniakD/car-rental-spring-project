package com.example.carrentalspringproject.repos;

import com.example.carrentalspringproject.model.entity.User;
import com.example.carrentalspringproject.model.entity.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    @Transactional
    @Modifying
    @Query(value = "UPDATE CarRentalSpring.user SET name=? WHERE id=?", nativeQuery = true)
    void changeUserNameById(String name, int userID);

    List<User> findUsersByRole(Role role);

    @Transactional
    @Modifying
    @Query(value = "UPDATE CarRentalSpring.user SET blocked=? WHERE id=?", nativeQuery = true)
    void changeBlockedStatusById(boolean blocked, int userID);

    Page<User> findAll(Pageable pageable);
}
