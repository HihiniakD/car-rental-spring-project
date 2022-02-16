package com.example.carrentalspringproject.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class OrderDto {

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "(?<! )[-a-zA-Z' ]{2,26}")
    private String cardHolderName;

    @NotBlank
    @Pattern(regexp = "(^4[0-9]{12}(?:[0-9]{3})?$)|(^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$)|(3[47][0-9]{13})|(^3(?:0[0-5]|[68][0-9])[0-9]{11}$)|(^6(?:011|5[0-9]{2})[0-9]{12}$)|(^(?:2131|1800|35\\d{3})\\d{11}$)")
    private String cardNumber;

    @NotBlank
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/?([0-9]{2})$")
    private String cardExpirationDate;

    @NotBlank
    @Pattern(regexp = "^[0-9]{3,4}$")
    private String cardCvv;
}
