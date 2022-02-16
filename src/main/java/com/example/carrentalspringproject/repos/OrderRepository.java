package com.example.carrentalspringproject.repos;

import com.example.carrentalspringproject.model.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    @Query(value = "SELECT * FROM CarRental.order where user_id=?", nativeQuery = true)
    List<Order> findAllByUserId(int id);
}
