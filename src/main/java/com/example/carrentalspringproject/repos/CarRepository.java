package com.example.carrentalspringproject.repos;

import com.example.carrentalspringproject.model.entity.Car;
import com.example.carrentalspringproject.model.entity.enums.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {
    @Query(value = "SELECT * FROM CarRental.car WHERE brand_id=? AND city_id=? AND category_id=? AND status_id=?", nativeQuery = true)
    List<Car> findAllAvailableCars(int brandId, int cityId, int categoryId, int statusId);
    @Query(value = "SELECT * FROM CarRental.car WHERE brand_id=? AND city_id=? AND category_id=? AND status_id=? ORDER BY price", nativeQuery = true)
    List<Car> findAllAvailableCarsSortedByPrice(int brandId, int cityId, int categoryId, int statusId);
    @Query(value = "SELECT * FROM CarRental.car WHERE brand_id=? AND city_id=? AND category_id=? AND status_id=? ORDER BY model", nativeQuery = true)
    List<Car> findAllAvailableCarsSortedByName(int brandId, int cityId, int categoryId, int statusId);
    Car findCarById(int carId);
    boolean existsByIdAndStatus(int carId, Status status);
    @Query(value = "UPDATE CarRental.car SET status_id=? WHERE id=?", nativeQuery = true)
    boolean changeStatus(int statusId, Status status);

}
