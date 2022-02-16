package com.example.carrentalspringproject.service;

import com.example.carrentalspringproject.controller.dto.CarDto;
import com.example.carrentalspringproject.model.entity.enums.Status;

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
    Boolean changeStatus(int id, Status status);


}
