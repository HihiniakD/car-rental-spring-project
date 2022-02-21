package com.example.carrentalspringproject.service.impl;

import com.example.carrentalspringproject.controller.Constants;
import com.example.carrentalspringproject.exception.ServiceException;
import com.example.carrentalspringproject.repos.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    private static final String COMMENT = " Some comment content";
    private static final String NOT_VALID_PENALTY_FEE = "15f";
    private static final int ID = 1;
    private static final String BLANK_FIELD = " ";

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
}