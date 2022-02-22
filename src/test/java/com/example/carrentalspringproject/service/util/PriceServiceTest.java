package com.example.carrentalspringproject.service.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceServiceTest {
    public static final int PRICE = 50;
    public static final int DAYS = 5;
    public static final long RESULT_PRICE = 250;
    public static final long RESULT_DAYS = 100;

    @Test
    public void shouldReturnRightResultCalculateTotalPrice(){

        assertEquals(RESULT_PRICE, PriceService.calculateTotalPrice(PRICE, DAYS));
    }

    @Test
    public void shouldReturnRightResultCalculateCarDrivePrice(){

        assertEquals(RESULT_DAYS, PriceService.calculateCarDrivePrice(DAYS));
    }

}