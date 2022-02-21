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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.carrentalspringproject.controller.Constants.*;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);
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
    @Transactional
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
        System.out.println(order);
        logger.info(String.format("%s %d, %d, %s, %d", NEW_ORDER_CREATED, user.getId(), car.getId(),
                car.getCity().getName(), totalPrice));
        return orderRepository.save(order);
    }


    @Override
    public List<Order> findAllNewOrders() {
        return orderRepository.findAllByStatus(Status.PROCESSING);
    }


    @Override
    public List<Order> findAllInProgressOrders() {
        return orderRepository.findAllByStatus(Status.APPROVED);
    }


    @Override
    public List<Order> findAllFinishedOrders() {
        return orderRepository.findAllByStatus(Status.DONE);
    }


    @Override
    public List<Order> findAllDeclinedOrders() {
        return orderRepository.findAllByStatus(Status.CANCELED);
    }


    @Override
    @Transactional
    public void changeStatusById(int id, Status status) {
        orderRepository.changeStatusById(status.getStatus(), id);
    }


    @Override
    public Order findById(int id) {
        return orderRepository.findById(id);
    }


    @Override
    @Transactional
    public void declineOrder(int id, String comment) {
        logger.info(String.format("%s %d, %s", ORDER_DECLINED, id, comment));
        orderRepository.changeStatusByIdAndSetComment(Status.CANCELED.getStatus(), comment, id);
    }


    @Override
    @Transactional
    public void finishOrder(int id, String comment, String penalty) {
        validatePenaltyFee(penalty);
        int penaltyFee = Integer.parseInt(penalty);
        Order order = orderRepository.findById(id);
        int newTotalPrice = order.getTotalPrice() + penaltyFee;
        orderRepository.changeStatusAndSetCommentAndPrice(comment,newTotalPrice, Status.DONE.getStatus(), id);
    }


    public boolean validatePenaltyFee(String penaltyFee) {
        if (penaltyFee == null || penaltyFee.isBlank())
            throw new ServiceException(FAIL_MESSAGE);

        try {
            Integer.parseInt(penaltyFee);
        }catch (NumberFormatException exception){
            throw new ServiceException(FAIL_MESSAGE);
        }
        return true;
    }
}
