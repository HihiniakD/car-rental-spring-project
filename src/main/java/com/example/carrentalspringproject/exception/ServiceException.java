package com.example.carrentalspringproject.exception;

public class ServiceException extends RuntimeException{
    public ServiceException(String wrongData) {
        super(wrongData);
    }
}
