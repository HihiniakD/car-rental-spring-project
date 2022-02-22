package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.controller.Constants;
import com.example.carrentalspringproject.controller.dto.CarDto;
import com.example.carrentalspringproject.exception.ServiceException;
import com.example.carrentalspringproject.model.entity.Brand;
import com.example.carrentalspringproject.model.entity.Car;
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
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockHttpSession;
import static com.example.carrentalspringproject.controller.Constants.*;


import javax.servlet.http.HttpSession;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

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
    public static final String NOT_VALID_PICK_UP_DATE = "2022-01-01";
    public static final String VALID_PICK_UP_DATE = "2023-01-01";
    public static final String NOT_VALID_DROP_OFF_DATE = "2022-01-05";
    public static final String VALID_DROP_OFF_DATE = "2023-01-05";
    public static final long DAYS = 5l;

    @Test
    public void shouldPassTestWithValidInputAddCar(){
        Brand brand = new Brand(ID, BRAND_NAME);
        City city = new City(ID, CITY_NAME);
        Category category = new Category(ID, CATEGORY_NAME);

        assertDoesNotThrow(()-> carService.addCar(brand, MODEL, PASSENGERS, VALID_PRICE, TRANSMISSION, category,
                city, VALID_URL));
    }

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

    @Test
    public void shouldThrowServiceExceptionWhenDateNotValidFindAllAvailableCars(){
        HttpSession session = new MockHttpSession();
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { carService.findAllAvailableCars(ID, ID, ID,NOT_VALID_PICK_UP_DATE,
                        NOT_VALID_DROP_OFF_DATE, session); }
        );
        assertEquals(Constants.DATE_NOT_VALID, exception.getMessage());
    }

    @Test
    public void shouldReturnListOfCarDtoWhenInputDataISValidFindAllAvailableCars(){
        HttpSession session = new MockHttpSession();
        Mockito.when(carRepository.findAllAvailableCars(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(Collections.singletonList(createCar()));
        List<CarDto> carDto = carService.findAllAvailableCars(ID, ID, ID,VALID_PICK_UP_DATE,
                        VALID_DROP_OFF_DATE, session);
        assertNotNull(carDto);
        assertEquals(VALID_PICK_UP_DATE, session.getAttribute(PICKUP_DATE_PARAMETER));
        assertEquals(VALID_DROP_OFF_DATE, session.getAttribute(DROPOFF_DATE_PARAMETER));
        assertEquals(ID, session.getAttribute(CITY_ID_PARAMETER));
        assertEquals(ID, session.getAttribute(BRAND_ID_PARAMETER));
        assertEquals(ID, session.getAttribute(CITY_ID_PARAMETER));
    }

    @Test
    public void shouldReturnListOfCarDtoWhenInputDataISValidFindAllAvailableCarsSortedByName(){
        HttpSession session = new MockHttpSession();
        session.setAttribute(DAYS_PARAMETER, DAYS);
        Mockito.when(carRepository.findAllAvailableCarsSortedByName(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(Collections.singletonList(createCar()));
        List<CarDto> carDto = carService.findAllAvailableCarsSortedByName(ID, ID, ID, session);
        assertNotNull(carDto);
        assertEquals(DAYS, session.getAttribute(DAYS_PARAMETER));
    }

    @Test
    public void shouldReturnListOfCarDtoWhenInputDataISValidFindAllAvailableCarsSortedByPrice(){
        HttpSession session = new MockHttpSession();
        session.setAttribute(DAYS_PARAMETER, DAYS);
        Mockito.when(carRepository.findAllAvailableCarsSortedByPrice(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(Collections.singletonList(createCar()));
        List<CarDto> carDto = carService.findAllAvailableCarsSortedByPrice(ID, ID, ID, session);
        assertNotNull(carDto);
        assertEquals(DAYS, session.getAttribute(DAYS_PARAMETER));
    }

    @Test
    public void findCarById() {
        Mockito.when(carRepository.findCarById(anyInt())).thenReturn(createCar());
        Car car = carService.findCarById(ID);
        assertNotNull(car);
    }

    @Test
    public void changeStatus(){
        boolean result = carService.changeStatus(ID, Status.BUSY);
        assertTrue(result);
    }

    @Test
    public void findCarByIdOverloaded() {
        Mockito.when(carRepository.findCarById(anyInt())).thenReturn(createCar());
        CarDto car = carService.findCarById(ID, DAYS);
        assertNotNull(car);
    }

    @Test
    public void findCarPaginated() {
        int pageNo = 1;
        int pageSize = 5;
        Mockito.when(carRepository.findAll(any())).thenReturn(Page.empty());
        Page<Car> page = carService.findCarPaginated(pageNo, pageSize);
        assertNotNull(page);
    }


    private Car createCar(){
        Car car = new Car();
        return car;
    }

    @Test
    public void shouldPassTestWithValidInputDeleteCar(){
        assertDoesNotThrow(()-> carService.deleteCar(ID));
    }

    @Test
    public void shouldPassTestWithValidInputEditCar(){
        assertDoesNotThrow(()-> carService.editCar(ID, VALID_PRICE, VALID_URL));
    }

}