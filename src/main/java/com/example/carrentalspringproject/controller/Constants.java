package com.example.carrentalspringproject.controller;

public class Constants {
    //Validation errors
    public static final String EMAIL_NOT_VALID = "emailNotValid";
    public static final String NAME_NOT_VALID = "nameNotValid";
    public static final String PHONE_LENGTH_NOT_VALID = "phoneLengthNotValid";
    public static final String PHONE_MUST_CONTAINS_ONLY_DIGITS = "phoneContainsOnlyDigits";
    public static final String PHONE_IS_BLANK = "phoneIsBlank";
    public static final String PASSWORD_LENGTH_NOT_VALID = "passwordLengthNotValid";
    public static final String PASSWORD_IS_BLANK = "passwordIsBlank";
    public static final String EMAIL_EXISTS = "emailExists";


    //Service Exception messages
    public static final String DATE_NOT_VALID = "dateNotValid";


    //constants for retrieving and inserting from/into requests
    public static final String USER_PARAMETER = "user";
    public static final String USERS_PARAMETER = "users";
    public static final String ERROR_PARAMETER = "error";
    public static final String PICKUP_DATE_PARAMETER = "pickupDate";
    public static final String DROPOFF_DATE_PARAMETER = "dropoffDate";
    public static final String DAYS_PARAMETER = "days";
    public static final String CARS_PARAMETER = "cars";
    public static final String CAR_PARAMETER = "car";
    public static final String CITIES_PARAMETER = "cities";
    public static final String BRANDS_PARAMETER = "brands";
    public static final String CATEGORIES_PARAMETER = "categories";
    public static final String DRIVER_PARAMETER = "driver";
    public static final String DRIVER_PRICE_PARAMETER = "driver_price";
    public static final String TOTAL_PRICE_PARAMETER = "total_price";
    public static final String CAR_RENTAL_PRICE_PARAMETER = "car_rental_price";
    public static final String BRAND_ID_PARAMETER = "brand_id";
    public static final String CITY_ID_PARAMETER = "city_id";
    public static final String CATEGORY_ID_PARAMETER = "category_id";
    public static final String SUCCESS_MESSAGE_PARAMETER = "success_message";
    public static final String MESSAGE_PARAMETER = "message";
    public static final String ORDERS_PARAMETER = "orders";
    public static final String ORDER_PARAMETER = "order";
    public static final String NEW_ORDERS_PARAMETER = "newOrders";
    public static final String IN_PROGRESS_ORDERS_PARAMETER = "ordersInProgress";
    public static final String FINISHED_ORDERS_PARAMETER = "finishedOrders";
    public static final String DECLINED_ORDERS_PARAMETER = "declinedOrders";
    public static final String CURRENT_PAGE_PARAMETER = "currentPage";
    public static final String NUMBER_OF_PAGES_PARAMETER = "totalPages";
    public static final String TOTAL_ITEMS_PARAMETER = "totalItems";
    public static final String MANAGERS_PARAMETER = "managers";

    //errors constants
    public static final String DATA_NOT_VALID = "dataNotValid";
    public static final String CAR_NOT_AVAILABLE_ERROR = "carNotAvailable";
    public static final String CC_NAME_NOT_VALID_ERROR = "ccNameNotValid";
    public static final String CC_NUMBER_NOT_VALID_ERROR = "ccNumberNotValid";
    public static final String CC_EXPIRATION_NOT_VALID_ERROR = "ccExpirationNotValid";
    public static final String CC_CVV_NOT_VALID_ERROR = "ccCvvNotValid";


    public static final String SUCCESS_BOOKING_MESSAGE = "successBookingMessage";
    public static final String SUCCESS_MESSAGE = "successMessage";
    public static final String FAIL_MESSAGE = "failMessage";

    //Logger messages
    public static final String FAILED_LOGIN = "Failed login attempt with email - ";
    public static final String SUCCESS_LOGIN = "Success login attempt with email - ";
    public static final String NEW_USER_CREATED = "New user created with email - ";
    public static final String NEW_MANAGER_CREATED = "New manager created with email - ";
    public static final String CAR_DELETED = "Car was deleted. ID - ";
    public static final String NEW_CAR_ADDED = "New car created with values(brand, model, city, price) - ";
    public static final String ORDER_DECLINED = "Order was declined. ID, comment - ";
    public static final String NEW_ORDER_CREATED = "New order created with values(userId, carId, city, price) - ";
}
