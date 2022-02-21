package com.example.carrentalspringproject.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import static com.example.carrentalspringproject.controller.Constants.*;

@Getter
@Setter
public class SignUpUserDto {

    public static final String REGEX_URL = "(?:[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Pattern(regexp = REGEX_URL, message = EMAIL_NOT_VALID)
    private String email;

    @NotBlank(message = PASSWORD_IS_BLANK)
    @Size(min = 8, max = 64, message = PASSWORD_LENGTH_NOT_VALID)
    private String password;

    @NotBlank(message = NAME_NOT_VALID)
    private String name;

    @NotBlank(message = PHONE_IS_BLANK)
    @Size(min = 8,message = PHONE_LENGTH_NOT_VALID)
    @Pattern(regexp = "\\d+", message = PHONE_MUST_CONTAINS_ONLY_DIGITS)
    private String phone;
}
