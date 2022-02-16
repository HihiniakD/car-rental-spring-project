package com.example.carrentalspringproject.repos;

import com.example.carrentalspringproject.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    @Query(value = "UPDATE CarRental.user SET name=? WHERE id=?", nativeQuery = true)
    boolean changeUserNameById(String name, int userID);
}
