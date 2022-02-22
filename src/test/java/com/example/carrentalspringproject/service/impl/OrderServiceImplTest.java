package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.controller.Constants;
import com.example.carrentalspringproject.controller.dto.CarDto;
import com.example.carrentalspringproject.exception.ServiceException;
import com.example.carrentalspringproject.model.entity.*;
import com.example.carrentalspringproject.model.entity.enums.Role;
import com.example.carrentalspringproject.model.entity.enums.Status;
import com.example.carrentalspringproject.repos.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    private static final String COMMENT = " Some comment content";
    private static final String NOT_VALID_PENALTY_FEE = "15f";
    private static final int ID = 1;
    private static final String BLANK_FIELD = " ";
    private static final String BRAND = "Audi";
    private static final String CATEGORY = "Premium";
    private static final String CITY = "Kharkiv";
    private static final String MODEL = "Q8";
    private static final String URL = "https://www.kimballstock.com/pix/car/p/02/aut-15-iz0930-01p.jpg";
    private static final int PASSENGERS = 4;
    private static final int ALL_DAYS_PRICE = 500;
    private static final int ONE_DAY_PRICE = 100;
    private static final String EMAIL = "email@gmail.com";
    private static final String NAME = "Steve Rambo";
    private static final String PASSWORD = "$2a$10$xrFKM5SmMK780wPMpaj6bO.oS1zdxaef5xqcWDDlqWf0zX5zh4Wfm";
    private static final String PHONE = "380961111111";
    private static final Role ROLE = Role.USER;
    public static final String VALID_PICK_UP_DATE = "2023-01-01";
    public static final String VALID_DROP_OFF_DATE = "2023-01-05";
    public static final long TOTAL_PRICE = 1500;

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    public void shouldThrowServiceExceptionWithInvalidPenaltyFeeFinishOrder(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.finishOrder(ID, COMMENT, NOT_VALID_PENALTY_FEE); }
        );
        assertEquals(Constants.FAIL_MESSAGE,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithBlankPenaltyFeeFinishOrder(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.finishOrder(ID, COMMENT, BLANK_FIELD); }
        );
        assertEquals(Constants.FAIL_MESSAGE,exception.getMessage());
    }

    @Test
    public void shouldThrowServiceExceptionWithNullPenaltyFeeFinishOrder(){
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { orderService.finishOrder(ID, COMMENT, null); }
        );
        assertEquals(Constants.FAIL_MESSAGE,exception.getMessage());
    }

    @Test
    public void shouldTPassTestWithValidInputProcessOrder(){
        Mockito.when(orderRepository.save(any())).thenReturn(createOrder());
        Order order = orderService.processOrder(createUser(), createCarDto(), VALID_PICK_UP_DATE, VALID_DROP_OFF_DATE,
                TOTAL_PRICE, false);
        assertNotNull(order);
    }

    @Test
    void findAllNewOrders() {
        Mockito.when(orderRepository.findAllByStatus(any())).thenReturn(Collections.singletonList(createOrder()));
        List<Order> orders = orderService.findAllNewOrders();
        assertNotNull(orders);
    }

    @Test
    void findAllInProgressOrders() {
        Mockito.when(orderRepository.findAllByStatus(any())).thenReturn(Collections.singletonList(createOrder()));
        List<Order> orders = orderService.findAllInProgressOrders();
        assertNotNull(orders);
    }

    @Test
    void findAllFinishedOrders() {
        Mockito.when(orderRepository.findAllByStatus(any())).thenReturn(Collections.singletonList(createOrder()));
        List<Order> orders = orderService.findAllFinishedOrders();
        assertNotNull(orders);
    }

    @Test
    void findAllDeclinedOrders() {
        Mockito.when(orderRepository.findAllByStatus(any())).thenReturn(Collections.singletonList(createOrder()));
        List<Order> orders = orderService.findAllDeclinedOrders();
        assertNotNull(orders);
    }

    private CarDto createCarDto(){
        Brand brand = new Brand();
        brand.setName(BRAND);
        City city = new City();
        city.setName(CITY);
        Category category = new Category();
        category.setName(CATEGORY);

        CarDto carDto = new CarDto();
        carDto.setBrand(brand);
        carDto.setModel(MODEL);
        carDto.setCity(city);
        carDto.setImageUrl(URL);
        carDto.setPassengers(PASSENGERS);
        carDto.setPriceAllDays(ALL_DAYS_PRICE);
        carDto.setPriceForOneDay(ONE_DAY_PRICE);
        carDto.setCategory(category);
        return carDto;
    }

    private Car createCar(){
        Brand brand = new Brand();
        brand.setName(BRAND);
        City city = new City();
        city.setName(CITY);
        Category category = new Category();
        category.setName(CATEGORY);

        Car car = new Car();
        car.setBrand(brand);
        car.setModel(MODEL);
        car.setCity(city);
        car.setImageUrl(URL);
        car.setPassengers(PASSENGERS);
        car.setCategory(category);
        return car;
    }

    private User createUser(){
        User user = new User();
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setPhone(PHONE);
        user.setName(NAME);
        user.setRole(ROLE);
        user.setBlocked(false);
        return user;
    }

    private Order createOrder(){
        City city = new City();
        city.setName(CITY);

        Order order = new Order();
        order.setCity(city);
        order.setCar(createCar());
        order.setUser(createUser());
        order.setComment(COMMENT);
        order.setStatus(Status.APPROVED);
        order.setDropOffDate(LocalDate.parse(VALID_DROP_OFF_DATE));
        order.setPickUpDate(LocalDate.parse(VALID_PICK_UP_DATE));
        return order;
    }

}