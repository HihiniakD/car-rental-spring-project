package com.example.carrentalspringproject.service;

import com.example.carrentalspringproject.controller.dto.SearchCarsDto;
import com.example.carrentalspringproject.model.entity.Car;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CarService {
    List<SearchCarsDto> findAllAvailableCars(int brandId, int cityId, int categoryId,
                                             String pickUpDate, String dropOffDate,
                                             HttpSession session);
    List<SearchCarsDto> findAllAvailableCarsSortedByPrice(int brandId, int cityId, int categoryId,
                                                          HttpSession session);
    List<SearchCarsDto> findAllAvailableCarsSortedByName(int brandId, int cityId, int categoryId,
                                                          HttpSession session);
}
