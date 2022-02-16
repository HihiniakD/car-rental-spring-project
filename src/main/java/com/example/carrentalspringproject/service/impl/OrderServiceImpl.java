package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.controller.dto.CarDto;
import com.example.carrentalspringproject.exception.ServiceException;
import com.example.carrentalspringproject.model.entity.Order;
import com.example.carrentalspringproject.model.entity.User;
import com.example.carrentalspringproject.model.entity.enums.Status;
import com.example.carrentalspringproject.repos.OrderRepository;
import com.example.carrentalspringproject.service.OrderService;
import com.example.carrentalspringproject.service.util.PriceService;
import com.example.carrentalspringproject.service.util.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.carrentalspringproject.controller.Constants.*;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAllByUserId(int id) {
        return orderRepository.findAllByUserId(id);
    }

    @Override
    public long calculateTotalPrice(int price, long days) {
        return PriceService.calculateTotalPrice(price, days);
    }

    @Override
    public long calculateCarDriverPrice(long days) {
        return PriceService.calculateCarDrivePrice(days);
    }

    @Override
    public boolean validateOrderPayment(String creditCardName, String creditCardNumber, String creditCardExpiration, String creditCardCvv) {
        if (!SecurityService.isCreditCardNameValid(creditCardName))
            throw new ServiceException(CC_NAME_NOT_VALID_ERROR);

        if (!SecurityService.isCreditCardNumberValid(creditCardNumber))
            throw new ServiceException(CC_NUMBER_NOT_VALID_ERROR);

        if (!SecurityService.isCreditCardExpirationValid(creditCardExpiration))
            throw new ServiceException(CC_EXPIRATION_NOT_VALID_ERROR);

        if (!SecurityService.isCreditCardCvvValid(creditCardCvv))
            throw new ServiceException(CC_CVV_NOT_VALID_ERROR);

        return true;
    }

    @Override
    public Order processOrder(User user, CarDto car, String pickUpDate, String dropOffDate, long totalPrice, boolean withDriver) {
        Order order = new Order();

        order.setUser(user);
        order.setCar(CarDto.mapDtoToCar(car));
        order.setCity(car.getCity());
        order.setPickUpDate(LocalDate.parse(pickUpDate));
        order.setDropOffDate(LocalDate.parse(dropOffDate));
        order.setTotalPrice((int) totalPrice);
        order.setStatus(Status.PROCESSING);
        order.setWithDriver(withDriver);
        order.setComment("");
        System.out.println(order);

        return orderRepository.save(order);
    }
}
