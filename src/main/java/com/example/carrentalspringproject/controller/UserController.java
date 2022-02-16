package com.example.carrentalspringproject.controller;

import com.example.carrentalspringproject.model.entity.Order;
import com.example.carrentalspringproject.model.entity.User;
import com.example.carrentalspringproject.service.CarService;
import com.example.carrentalspringproject.service.OrderService;
import com.example.carrentalspringproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('USER')")
public class UserController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;

    @GetMapping("/my_booking")
    public String showMyBooking(Model model, HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        List<GrantedAuthority> list = new ArrayList<>();
        authentication.getAuthorities().stream().map(role -> list.add(role));
        User user = userService.findByEmail(email);
        List<Order> userOrders = orderService.findAllByUserId(user.getId());
        System.out.println(userOrders);
        model.addAttribute("orders", userOrders);
        session.setAttribute("user", user);
        System.out.println(session.getAttribute("user") + " USER FROM SESSION");

        return "my_booking";
    }

    @GetMapping("/search_cars/view_deal")
    public String showViewDeal(@RequestParam int id, Model model, HttpSession session){


        return "view_deal";
    }

}
