package com.example.carrentalspringproject.service.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import com.example.carrentalspringproject.exception.ServiceException;
import static com.example.carrentalspringproject.controller.Constants.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateService {
    /**
     * @return number of days between dropOffDate and pickUpDate
     * @throws ServiceException if date is not valid
     */
    public static long countDays(String pickUpDate, String dropOffDate){
        long days = 0;
        try{
            LocalDate pickUpLocalDate = LocalDate.parse(pickUpDate);
            LocalDate dropOffLocalDate = LocalDate.parse(dropOffDate);
            LocalDate today = LocalDate.now();
            if (pickUpLocalDate.isBefore(today) || pickUpLocalDate.isEqual(today) ||
                    dropOffLocalDate.isBefore(today) || dropOffLocalDate.isEqual(today) ||
                    dropOffLocalDate.isEqual(today.plusDays(1)))
                throw new ServiceException(DATE_NOT_VALID);

            days = DAYS.between(pickUpLocalDate, dropOffLocalDate);
        }catch (DateTimeException exception){
            throw new ServiceException(DATE_NOT_VALID);
        }
        return days;
    }
}
