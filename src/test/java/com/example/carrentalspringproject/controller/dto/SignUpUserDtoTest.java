package com.example.carrentalspringproject.controller.dto;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SignUpUserDtoValidationTest {

    private Validator validator;

    private static final String VALID_EMAIL = "email@gmail.com";
    private static final String VALID_PASSWORD = "Qwerty90@";
    private static final String VALID_NAME = "Steve Rambo";
    private static final String VALID_PHONE = "380965554422";
    private static final String BLANK_FIELD = " ";
    private static final String NOT_VALID_EMAIL_WITHOUT_DOT = "email@com";
    private static final String NOT_VALID_EMAIL_WITHOUT_AT = "email.com";
    private static final String NOT_VALID_EMAIL_WITH_CYRILLIC_LETTERS = "еmail@gmail.com";
    private static final String NOT_VALID_EMAIL_WITH_PROHIBIT_SYMBOL = "em,ail@gmail.com";
    private static final String NOT_VALID_EMAIL_WITH_DOUBLE_DOT = "email@gmail..com";
    private static final String NOT_VALID_EMAIL_WITH_SPACE = "ema il@gmail.com";
    private static final String NOT_VALID_SHORT_PASSWORD = "short";
    private static final String NOT_VALID_LONG_PASSWORD = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet...";
    private static final String NOT_VALID_SHORT_PHONE = "1234567";
    private static final String NOT_VALID_PHONE = "1234567d";

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldPassValidationWithValidInput() {
        SignUpUserDto userDto = new SignUpUserDto();
        userDto.setEmail(VALID_EMAIL);
        userDto.setPassword(VALID_PASSWORD);
        userDto.setName(VALID_NAME);
        userDto.setPhone(VALID_PHONE);
        Set<ConstraintViolation<SignUpUserDto>> violations = validator.validate(userDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWithInvalidEmailFirstCase() {
        SignUpUserDto userDto = new SignUpUserDto();
        userDto.setEmail(NOT_VALID_EMAIL_WITHOUT_DOT);
        userDto.setPassword(VALID_PASSWORD);
        userDto.setName(VALID_NAME);
        userDto.setPhone(VALID_PHONE);
        Set<ConstraintViolation<SignUpUserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWithInvalidEmailThirdCase() {
        SignUpUserDto userDto = new SignUpUserDto();
        userDto.setEmail(NOT_VALID_EMAIL_WITH_CYRILLIC_LETTERS);
        userDto.setPassword(VALID_PASSWORD);
        userDto.setName(VALID_NAME);
        userDto.setPhone(VALID_PHONE);
        Set<ConstraintViolation<SignUpUserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWithInvalidEmailFourthCase() {
        SignUpUserDto userDto = new SignUpUserDto();
        userDto.setEmail(NOT_VALID_EMAIL_WITH_PROHIBIT_SYMBOL);
        userDto.setPassword(VALID_PASSWORD);
        userDto.setName(VALID_NAME);
        userDto.setPhone(VALID_PHONE);
        Set<ConstraintViolation<SignUpUserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWithInvalidEmailFifthCase() {
        SignUpUserDto userDto = new SignUpUserDto();
        userDto.setEmail(NOT_VALID_EMAIL_WITH_DOUBLE_DOT);
        userDto.setPassword(VALID_PASSWORD);
        userDto.setName(VALID_NAME);
        userDto.setPhone(VALID_PHONE);
        Set<ConstraintViolation<SignUpUserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWithInvalidEmailSixthCase() {
        SignUpUserDto userDto = new SignUpUserDto();
        userDto.setEmail(NOT_VALID_EMAIL_WITH_SPACE);
        userDto.setPassword(VALID_PASSWORD);
        userDto.setName(VALID_NAME);
        userDto.setPhone(VALID_PHONE);
        Set<ConstraintViolation<SignUpUserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWithInvalidEmailSecondCase() {
        SignUpUserDto userDto = new SignUpUserDto();
        userDto.setEmail(NOT_VALID_EMAIL_WITHOUT_AT);
        userDto.setPassword(VALID_PASSWORD);
        userDto.setName(VALID_NAME);
        userDto.setPhone(VALID_PHONE);
        Set<ConstraintViolation<SignUpUserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWithBlankPassword() {
        SignUpUserDto userDto = new SignUpUserDto();
        userDto.setEmail(VALID_EMAIL);
        userDto.setPassword(BLANK_FIELD);
        userDto.setName(VALID_NAME);
        userDto.setPhone(VALID_PHONE);
        Set<ConstraintViolation<SignUpUserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWithShortPassword() {
        SignUpUserDto userDto = new SignUpUserDto();
        userDto.setEmail(VALID_EMAIL);
        userDto.setPassword(NOT_VALID_SHORT_PASSWORD);
        userDto.setName(VALID_NAME);
        userDto.setPhone(VALID_PHONE);
        Set<ConstraintViolation<SignUpUserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWithLongPassword() {
        SignUpUserDto userDto = new SignUpUserDto();
        userDto.setEmail(VALID_EMAIL);
        userDto.setPassword(NOT_VALID_LONG_PASSWORD);
        userDto.setName(VALID_NAME);
        userDto.setPhone(VALID_PHONE);
        Set<ConstraintViolation<SignUpUserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWithBlankName() {
        SignUpUserDto userDto = new SignUpUserDto();
        userDto.setEmail(VALID_EMAIL);
        userDto.setPassword(VALID_PASSWORD);
        userDto.setName(BLANK_FIELD);
        userDto.setPhone(VALID_PHONE);
        Set<ConstraintViolation<SignUpUserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWithBlankPhone() {
        SignUpUserDto userDto = new SignUpUserDto();
        userDto.setEmail(VALID_EMAIL);
        userDto.setPassword(VALID_PASSWORD);
        userDto.setName(VALID_NAME);
        userDto.setPhone(BLANK_FIELD);
        Set<ConstraintViolation<SignUpUserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWithShortPhone() {
        SignUpUserDto userDto = new SignUpUserDto();
        userDto.setEmail(VALID_EMAIL);
        userDto.setPassword(VALID_PASSWORD);
        userDto.setName(VALID_NAME);
        userDto.setPhone(NOT_VALID_SHORT_PHONE);
        Set<ConstraintViolation<SignUpUserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWithIncorrectPhone() {
        SignUpUserDto userDto = new SignUpUserDto();
        userDto.setEmail(VALID_EMAIL);
        userDto.setPassword(VALID_PASSWORD);
        userDto.setName(VALID_NAME);
        userDto.setPhone(NOT_VALID_PHONE);
        Set<ConstraintViolation<SignUpUserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
    }
}