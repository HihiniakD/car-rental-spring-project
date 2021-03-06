package com.example.carrentalspringproject.repos;

import com.example.carrentalspringproject.model.entity.Car;
import com.example.carrentalspringproject.model.entity.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {
    @Query(value = "SELECT * FROM CarRentalSpring.car WHERE brand_id=? AND city_id=? AND category_id=? AND status_id=?", nativeQuery = true)
    List<Car> findAllAvailableCars(int brandId, int cityId, int categoryId, int statusId);

    @Query(value = "SELECT * FROM CarRentalSpring.car WHERE brand_id=? AND city_id=? AND category_id=? AND status_id=? ORDER BY price", nativeQuery = true)
    List<Car> findAllAvailableCarsSortedByPrice(int brandId, int cityId, int categoryId, int statusId);

    @Query(value = "SELECT * FROM CarRentalSpring.car WHERE brand_id=? AND city_id=? AND category_id=? AND status_id=? ORDER BY model", nativeQuery = true)
    List<Car> findAllAvailableCarsSortedByName(int brandId, int cityId, int categoryId, int statusId);

    Car findCarById(int carId);

    boolean existsByIdAndStatus(int carId, Status status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE CarRentalSpring.car SET status_id=? WHERE id=?", nativeQuery = true)
    void changeStatus(int statusId, int carId);

    Page<Car> findAll(Pageable pageable);

    @Transactional
    @Modifying
    void deleteCarById(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE CarRentalSpring.car SET price=?, image_url=? WHERE id=?", nativeQuery = true)
    void editCar(int price, String imageUrl, int id);
}
