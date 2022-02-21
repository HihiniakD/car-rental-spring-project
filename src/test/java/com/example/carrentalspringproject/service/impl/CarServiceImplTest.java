package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.controller.Constants;
import com.example.carrentalspringproject.exception.ServiceException;
import com.example.carrentalspringproject.model.entity.Brand;
import com.example.carrentalspringproject.model.entity.Category;
import com.example.carrentalspringproject.model.entity.City;
import com.example.carrentalspringproject.model.entity.enums.Status;
import com.example.carrentalspringproject.repos.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    public static final int ID = 1;
    public static final String MODEL = "Audi Q7";
    public static final String BRAND_NAME = "Audi";
    public static final String CITY_NAME = "Kyiv";
    public static final String CATEGORY_NAME = "Premium";
    public static final int PASSENGERS = 4;
    public static final String VALID_PRICE = "100";
    public static final String NOT_VALID_PRICE = "50$";
    public static final String TRANSMISSION = "manual";
    public static final String VALID_URL = "https://www.kimballstock.com/pix/car/p/02/aut-15-iz0930-01p.jpg";
    public static final String NOT_VALID_URL = "some_email";


    @Test
    public void shouldThrowServiceExceptionWithInvalidPriceAddCar(){
        Brand brand = new Brand(ID, BRAND_NAME);
        City city = new City(ID, CITY_NAME);
        Category category = new Category(ID, CATEGORY_NAME);

        ServiceException exception = Assertions.assertThrows(
                ServiceException.class,
                () -> { carService.addCar(brand, MODEL, PASSENGERS, NOT_VALID_PRICE, TRANSMISSION, category,
                        city, VALID_URL); }
        );
        assertEquals(Constants.DATA_NOT_VALID, exception.getMessage());

    }

    @Test
    public void shouldThrowServiceExceptionWithInvalidImgUrlAddCar(){
        Brand brand = new Brand(ID, BRAND_NAME);
        City city = new City(ID, CITY_NAME);
        Category category = new Category(ID, CATEGORY_NAME);

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { carService.addCar(brand, MODEL, PASSENGERS, VALID_PRICE, TRANSMISSION, category,
                        city, NOT_VALID_URL); }
        );

        assertEquals(Constants.DATA_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithInvalidImgUrlEditCar(){

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { carService.editCar(ID, VALID_PRICE, NOT_VALID_URL); }
        );

        assertEquals(Constants.DATA_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithInvalidPriceEditCar(){

        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { carService.editCar(ID, NOT_VALID_PRICE, VALID_URL); }
        );

        assertEquals(Constants.DATA_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWhenCarIsBusyCarIsAvailable(){
        Mockito.when(carRepository.existsByIdAndStatus(ID, Status.AVAILABLE)).thenReturn(false);
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { carService.carIsAvailable(ID); }
        );
        assertEquals(Constants.CAR_NOT_AVAILABLE_ERROR, exception.getMessage());
    }
}