package com.example.carrentalspringproject.repos;

import com.example.carrentalspringproject.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
