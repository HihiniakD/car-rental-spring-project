package com.example.carrentalspringproject.service.util;

public class SecurityService {
    //validation regex
    public static final String CC_NUMBER_REGEX = "(^4[0-9]{12}(?:[0-9]{3})?$)|(^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$)|(3[47][0-9]{13})|(^3(?:0[0-5]|[68][0-9])[0-9]{11}$)|(^6(?:011|5[0-9]{2})[0-9]{12}$)|(^(?:2131|1800|35\\d{3})\\d{11}$)";
    public static final String CC_NAME_REGEX = "(?<! )[-a-zA-Z' ]{2,26}";
    public static final String CC_EXPIRATION_REGEX = "^(0[1-9]|1[0-2])\\/?([0-9]{2})$";
    public static final String CC_CVV_REGEX = "^[0-9]{3,4}$";
    public static final String URL_REGEX = "^https.*";

    /**
     * Credit card name validation
     */
    public static boolean isCreditCardNameValid(String creditCardName) {
        return creditCardName != null && !creditCardName.isBlank() && creditCardName.matches(CC_NAME_REGEX);
    }

    /**
     * Credit card number validation
     */
    public static boolean isCreditCardNumberValid(String creditCardNumber) {
        return creditCardNumber != null && !creditCardNumber.isBlank() && creditCardNumber.matches(CC_NUMBER_REGEX);
    }

    /**
     * Credit card expiration date validation
     */
    public static boolean isCreditCardExpirationValid(String creditCardExpiration) {
        return creditCardExpiration != null && !creditCardExpiration.isBlank() && creditCardExpiration.matches(CC_EXPIRATION_REGEX);
    }

    /**
     * Credit card CVV code validation
     */
    public static boolean isCreditCardCvvValid(String creditCardCvv) {
        return creditCardCvv != null && !creditCardCvv.isBlank() && creditCardCvv.matches(CC_CVV_REGEX);
    }

}
