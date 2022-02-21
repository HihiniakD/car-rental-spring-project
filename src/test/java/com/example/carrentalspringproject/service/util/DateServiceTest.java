package com.example.carrentalspringproject.service.util;

import com.example.carrentalspringproject.controller.Constants;
import com.example.carrentalspringproject.exception.ServiceException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateServiceTest {

    public static final String VALID_PICK_UP_DAY = "2022-04-14";
    public static final String VALID_DROP_OFF_DAY = "2022-04-20";

    @Test
    public void shouldReturnDaysWhenInputDataIsValid(){
        long expectedDays = 6L;
        long days = DateService.countDays(VALID_PICK_UP_DAY, VALID_DROP_OFF_DAY);
        assertEquals(expectedDays, days);
    }

    @Test
    public void shouldReturnExceptionWhenPickUpDateIsToday(){
        String pickUpDate = LocalDate.now().toString();
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { DateService.countDays(pickUpDate, VALID_DROP_OFF_DAY); }
        );

        assertEquals(Constants.DATE_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenPickUpDateIsInThePast(){
        String pickUpDate = LocalDate.now().minusDays(1).toString();
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { DateService.countDays(pickUpDate, VALID_DROP_OFF_DAY); }
        );

        assertEquals(Constants.DATE_NOT_VALID,exception.getMessage());
    }


    @Test
    public void shouldReturnExceptionWhenDropOffDateIsToday(){
        String dropOfDays = LocalDate.now().toString();
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { DateService.countDays(VALID_PICK_UP_DAY, dropOfDays); }
        );

        assertEquals(Constants.DATE_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenDropOffDateIsInThePast(){
        String dropOfDays = LocalDate.now().minusDays(1).toString();
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { DateService.countDays(VALID_PICK_UP_DAY, dropOfDays); }
        );

        assertEquals(Constants.DATE_NOT_VALID,exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenDropOffDateIsTomorrow(){
        String dropOfDays = LocalDate.now().plusDays(1).toString();
        ServiceException exception = assertThrows(
                ServiceException.class,
                () -> { DateService.countDays(VALID_PICK_UP_DAY, dropOfDays); }
        );

        assertEquals(Constants.DATE_NOT_VALID,exception.getMessage());
    }

}