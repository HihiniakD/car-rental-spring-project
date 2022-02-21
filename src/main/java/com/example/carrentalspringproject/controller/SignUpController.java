package com.example.carrentalspringproject.controller;

import com.example.carrentalspringproject.controller.dto.SignUpUserDto;
import com.example.carrentalspringproject.model.entity.User;
import com.example.carrentalspringproject.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import static com.example.carrentalspringproject.controller.Constants.*;

@Controller
@RequestMapping("/sign_up")
public class SignUpController {

    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(SignUpController.class);

    @ModelAttribute("user")
    public SignUpUserDto userRegistrationDto() {
        return new SignUpUserDto();
    }


    @GetMapping
    public String signUp() {return "sign_up";}


    @PostMapping
    public String processSignUp(@ModelAttribute("user") @Valid SignUpUserDto userDto,
                                BindingResult result, Model model, HttpServletRequest request) {
        User user = userService.findByEmail(userDto.getEmail());
        if(user != null) {
            model.addAttribute(ERROR_PARAMETER, EMAIL_EXISTS);
            return "sign_up";
        }
        if (result.hasErrors()){
            return "sign_up";
        }
        userService.save(userDto);
        try {
            request.login(userDto.getEmail(), userDto.getPassword());
        } catch (ServletException exception) {
            logger.error(exception.getMessage());
        }
        return "redirect:/my_booking";
    }
}
