package com.example.carrentalspringproject.repos;

import com.example.carrentalspringproject.model.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
