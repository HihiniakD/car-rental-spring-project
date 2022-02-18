package com.example.carrentalspringproject.controller;

import com.example.carrentalspringproject.controller.dto.CarDto;
import com.example.carrentalspringproject.exception.ServiceException;
import com.example.carrentalspringproject.model.entity.Order;
import com.example.carrentalspringproject.model.entity.User;
import com.example.carrentalspringproject.model.entity.enums.Status;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import static com.example.carrentalspringproject.controller.Constants.*;

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
        long days = (long) session.getAttribute(DAYS_PARAMETER);
        CarDto car = carService.findCarById(id, days);
        session.setAttribute(CAR_PARAMETER, car);
        return "view_deal";
    }

    @PostMapping("/search_cars/view_deal/book")
    public String showBookPage(@RequestParam(required = false) String driver, HttpServletRequest request, HttpSession session){
        CarDto car = (CarDto) session.getAttribute(CAR_PARAMETER);
        long days = (long) session.getAttribute(DAYS_PARAMETER);
        long driverPrice = 0;
        boolean withDriver = driver != null;
        if (withDriver){
            session.setAttribute(DRIVER_PARAMETER, true);
            driverPrice = orderService.calculateCarDriverPrice(days);
            session.setAttribute(DRIVER_PRICE_PARAMETER, driverPrice);
        }
        else
            request.setAttribute(DRIVER_PARAMETER, false);
        long carRentPrice = orderService.calculateTotalPrice(car.getPriceForOneDay(), days);
        session.setAttribute(CAR_RENTAL_PRICE_PARAMETER, carRentPrice);
        long totalPrice = carRentPrice + driverPrice;
        session.setAttribute(TOTAL_PRICE_PARAMETER, totalPrice);

        return "book";
    }

    @PostMapping("/search_cars/view_deal/book/processBooking")
    public String processBooking(@RequestParam String name, @RequestParam(name = "cc_name") String ccName,
                                 @RequestParam(name = "cc_number") String ccNumber,
                                 @RequestParam(name = "cc_expiration") String ccExp,
                                 @RequestParam(name = "cc_cvv") String ccCvv,
                                 HttpSession session, Model model, RedirectAttributes redirectAttributes){
        CarDto carDto = (CarDto) session.getAttribute(CAR_PARAMETER);
        long totalPrice = (long) session.getAttribute(TOTAL_PRICE_PARAMETER);
        String pickUpDate = (String) session.getAttribute(PICKUP_DATE_PARAMETER);
        String dropOffDate = (String) session.getAttribute(DROPOFF_DATE_PARAMETER);
        boolean withDriver = !(session.getAttribute(DRIVER_PARAMETER) == null);
        User user = null;

        try{
            orderService.validateOrderPayment(ccName, ccNumber, ccExp, ccCvv);
        }catch (ServiceException exception){
            System.out.println(exception.getMessage());
            model.addAttribute(ERROR_PARAMETER, exception.getMessage());
            return "book";
        }

        try{
            carService.carIsAvailable(carDto.getId());
        }catch (ServiceException exception){
            redirectAttributes.addFlashAttribute(ERROR_PARAMETER, exception.getMessage());
            return "redirect:/";
        }

        try {
            user = userService.checkUsernameChange(session, name);
            session.setAttribute(USER_PARAMETER, user);
        }catch (ServiceException exception){
            model.addAttribute(ERROR_PARAMETER, exception.getMessage());
            return "book";
        }

        orderService.processOrder(user, carDto, pickUpDate, dropOffDate, totalPrice, withDriver);
        carService.changeStatus(carDto.getId(), Status.BUSY);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_PARAMETER, SUCCESS_BOOKING_MESSAGE);
        return "redirect:/my_booking";

    }

}
