package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.controller.dto.SearchCarsDto;
import com.example.carrentalspringproject.model.entity.Car;
import com.example.carrentalspringproject.model.entity.enums.Status;
import com.example.carrentalspringproject.repos.CarRepository;
import com.example.carrentalspringproject.service.CarService;
import com.example.carrentalspringproject.service.util.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import static com.example.carrentalspringproject.controller.Constants.*;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {this.carRepository = carRepository;}

    @Override
    public List<SearchCarsDto> findAllAvailableCars(int brandId, int cityId, int categoryId,
                                                    String pickUpDate, String dropOffDate,
                                                    HttpSession session) {
        long days = DateService.countDays(pickUpDate, dropOffDate);
        int statusId = Status.AVAILABLE.getStatus();
        session.setAttribute(PICKUP_DATE_PARAMETER, pickUpDate);
        session.setAttribute(DROPOFF_DATE_PARAMETER, dropOffDate);
        session.setAttribute(DAYS_PARAMETER, days);
        session.setAttribute(BRAND_ID_PARAMETER, brandId);
        session.setAttribute(CITY_ID_PARAMETER, cityId);
        session.setAttribute(CATEGORY_ID_PARAMETER, categoryId);
        List<Car> carList = carRepository.findAllAvailableCars(brandId, cityId, categoryId, statusId);
        return SearchCarsDto.mapCarListToDtoList(carList, days);
    }

    @Override
    public List<SearchCarsDto> findAllAvailableCarsSortedByPrice(int brandId, int cityId, int categoryId, HttpSession session) {
        int statusId = Status.AVAILABLE.getStatus();
        long days = (long) session.getAttribute(DAYS_PARAMETER);
        List<Car> carList = carRepository.findAllAvailableCarsSortedByPrice(brandId, cityId, categoryId, statusId);
        return SearchCarsDto.mapCarListToDtoList(carList, days);
    }

    @Override
    public List<SearchCarsDto> findAllAvailableCarsSortedByName(int brandId, int cityId, int categoryId, HttpSession session) {
        int statusId = Status.AVAILABLE.getStatus();
        long days = (long) session.getAttribute(DAYS_PARAMETER);
        List<Car> carList = carRepository.findAllAvailableCarsSortedByName(brandId, cityId, categoryId, statusId);
        return SearchCarsDto.mapCarListToDtoList(carList, days);
    }
}
