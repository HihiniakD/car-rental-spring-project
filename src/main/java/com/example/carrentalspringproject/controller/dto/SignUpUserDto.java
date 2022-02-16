package com.example.carrentalspringproject.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import static com.example.carrentalspringproject.controller.Constants.*;

@Getter
@Setter
public class SignUpUserDto {

    @Email(message = EMAIL_NOT_VALID)
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
