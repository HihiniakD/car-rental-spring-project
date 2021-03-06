package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.controller.dto.CarDto;
import com.example.carrentalspringproject.exception.ServiceException;
import com.example.carrentalspringproject.model.entity.Brand;
import com.example.carrentalspringproject.model.entity.Car;
import com.example.carrentalspringproject.model.entity.Category;
import com.example.carrentalspringproject.model.entity.City;
import com.example.carrentalspringproject.model.entity.enums.Status;
import com.example.carrentalspringproject.model.entity.enums.Transmission;
import com.example.carrentalspringproject.repos.CarRepository;
import com.example.carrentalspringproject.service.CarService;
import com.example.carrentalspringproject.service.util.DateService;
import com.example.carrentalspringproject.service.util.SecurityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;


import static com.example.carrentalspringproject.controller.Constants.*;

@Service
public class CarServiceImpl implements CarService {

    private static final Logger logger = Logger.getLogger(CarServiceImpl.class);
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {this.carRepository = carRepository;}


    @Override
    public List<CarDto> findAllAvailableCars(int brandId, int cityId, int categoryId,
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
        return CarDto.mapCarListToDtoList(carList, days);
    }


    @Override
    public List<CarDto> findAllAvailableCarsSortedByPrice(int brandId, int cityId, int categoryId, HttpSession session) {
        int statusId = Status.AVAILABLE.getStatus();
        long days = (long) session.getAttribute(DAYS_PARAMETER);
        List<Car> carList = carRepository.findAllAvailableCarsSortedByPrice(brandId, cityId, categoryId, statusId);
        return CarDto.mapCarListToDtoList(carList, days);
    }


    @Override
    public List<CarDto> findAllAvailableCarsSortedByName(int brandId, int cityId, int categoryId, HttpSession session) {
        int statusId = Status.AVAILABLE.getStatus();
        long days = (long) session.getAttribute(DAYS_PARAMETER);
        List<Car> carList = carRepository.findAllAvailableCarsSortedByName(brandId, cityId, categoryId, statusId);
        return CarDto.mapCarListToDtoList(carList, days);
    }


    @Override
    public CarDto findCarById(int carId, long days) {
        Car car = carRepository.findCarById(carId);
        return CarDto.mapCarToDto(car, days);
    }


    @Override
    public boolean carIsAvailable(int id) {
        boolean isAvailable = carRepository.existsByIdAndStatus(id, Status.AVAILABLE);
        if (!isAvailable)
            throw new ServiceException(CAR_NOT_AVAILABLE_ERROR);
        return isAvailable;
    }


    @Override
    public boolean changeStatus(int id, Status status) {
        carRepository.changeStatus(status.getStatus(), id);
        return true;
    }


    @Override
    public Page<Car> findCarPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return carRepository.findAll(pageable);
    }


    @Override
    @Transactional
    public void deleteCar(int id) {
        logger.info(CAR_DELETED + id);
        carRepository.deleteCarById(id);
    }


    @Override
    public Car findCarById(int carId) {
        return carRepository.findCarById(carId);
    }


    @Override
    public void editCar(int carId, String price, String imageUrl) {
        validateEditCar(price, imageUrl);
        carRepository.editCar(Integer.parseInt(price), imageUrl, carId);
    }


    @Override
    public void addCar(Brand brand, String model, int passengers, String price, String transmission, Category category,
                       City city, String imageUrl) {
        validateAddCar(price, model, imageUrl);
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setPassengers(passengers);
        car.setPrice(Integer.parseInt(price));
        car.setStatus(Status.AVAILABLE);
        car.setTransmission(Transmission.valueOf(transmission));
        car.setCity(city);
        car.setCategory(category);
        car.setImageUrl(imageUrl);
        logger.info(String.format("%s %s, %s, %s, %d", NEW_CAR_ADDED, car.getBrand().getName(),
                car.getModel(), car.getCity().getName(), car.getPrice()));
        carRepository.save(car);
    }


    private void validateEditCar(String price, String imageUrl) {
        try{
            Integer.parseInt(price);
        }catch (NumberFormatException e){
            throw new ServiceException(DATA_NOT_VALID);
        }
        if (!SecurityService.isUrlValid(imageUrl)){
            throw new ServiceException(DATA_NOT_VALID);
        }
    }


    /**
     * AddCar form validation
     */
    private void validateAddCar(String price, String model, String imageUrl) {
        try{
            Integer.parseInt(price);
        }catch (NumberFormatException e){
            throw new ServiceException(DATA_NOT_VALID);
        }
        if (!SecurityService.isUrlValid(imageUrl)){
            throw new ServiceException(DATA_NOT_VALID);
        }
        if (model == null || model.isBlank())
            throw new ServiceException(DATA_NOT_VALID);
    }
}
