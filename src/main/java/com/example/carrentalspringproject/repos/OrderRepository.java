package com.example.carrentalspringproject.repos;

import com.example.carrentalspringproject.model.entity.Order;
import com.example.carrentalspringproject.model.entity.enums.Status;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    @Query(value = "SELECT * FROM CarRentalSpring.orders where user_id=?", nativeQuery = true)
    List<Order> findAllByUserId(int id);

    List<Order> findAllByStatus(Status status);

    @Modifying
    @Query(value = "UPDATE CarRentalSpring.orders SET status_id=? WHERE id=?", nativeQuery = true)
    void changeStatusById(int statusId, int orderId);

    Order findById(int id);

    @Modifying
    @Query(value = "UPDATE CarRentalSpring.orders SET status_id=?, comments=? WHERE id=?", nativeQuery = true)
    void changeStatusByIdAndSetComment(int status_id, String comment, int id);

    @Modifying
    @Query(value = "UPDATE CarRentalSpring.orders SET comments=?, total_price=?, status_id=? WHERE id=?", nativeQuery = true)
    void changeStatusAndSetCommentAndPrice(String comment, int newTotalPrice, int status_id, int id);
}
