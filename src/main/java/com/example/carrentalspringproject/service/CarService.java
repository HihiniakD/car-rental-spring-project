package com.example.carrentalspringproject.service;

import com.example.carrentalspringproject.controller.dto.CarDto;
import com.example.carrentalspringproject.model.entity.*;
import com.example.carrentalspringproject.model.entity.enums.Status;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CarService {
    List<CarDto> findAllAvailableCars(int brandId, int cityId, int categoryId,
                                      String pickUpDate, String dropOffDate,
                                      HttpSession session);

    List<CarDto> findAllAvailableCarsSortedByPrice(int brandId, int cityId, int categoryId,
                                                   HttpSession session);

    List<CarDto> findAllAvailableCarsSortedByName(int brandId, int cityId, int categoryId,
                                                  HttpSession session);

    CarDto findCarById(int carId, long days);

    boolean carIsAvailable(int id);

    boolean changeStatus(int id, Status status);

    Page<Car> findCarPaginated(int pageNo, int pageSize);

    void deleteCar(int id);

    Car findCarById(int carId);

    void editCar(int carId, String price, String imageUrl);

    void addCar(Brand brand, String model, int passengers, String price, String transmission,
                Category category, City city, String imageUrl);
}
