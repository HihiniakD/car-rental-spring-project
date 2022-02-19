package com.example.carrentalspringproject.controller;

import com.example.carrentalspringproject.exception.ServiceException;
import com.example.carrentalspringproject.model.entity.Order;
import com.example.carrentalspringproject.model.entity.enums.Status;
import com.example.carrentalspringproject.service.CarService;
import com.example.carrentalspringproject.service.OrderService;
import com.example.carrentalspringproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.example.carrentalspringproject.controller.Constants.*;

import java.util.List;


@Controller
@PreAuthorize("hasAnyAuthority('MANAGER')")
public class ManagerController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;


    @GetMapping("/manager_page")
    public String showManagerPage(Model model){
        List<Order> newOrders = orderService.findAllNewOrders();
        List<Order> ordersInProgress = orderService.findAllInProgressOrders();
        List<Order> finishedOrders = orderService.findAllFinishedOrders();
        List<Order> declinedOrders = orderService.findAllDeclinedOrders();
        model.addAttribute(NEW_ORDERS_PARAMETER, newOrders);
        model.addAttribute(IN_PROGRESS_ORDERS_PARAMETER, ordersInProgress);
        model.addAttribute(FINISHED_ORDERS_PARAMETER, finishedOrders);
        model.addAttribute(DECLINED_ORDERS_PARAMETER, declinedOrders);
        return "manager_page";
    }


    @GetMapping("/manager_page/approveBooking")
    public String approveBooking(@RequestParam(name = "id") int orderId, RedirectAttributes redirectAttributes){
        orderService.changeStatusById(orderId, Status.APPROVED);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, SUCCESS_MESSAGE);
        return "redirect:/manager_page";
    }


    @GetMapping("/manager_page/declineBooking")
    public String declineBooking(@RequestParam(name = "id") int orderId, Model model){
        Order order = orderService.findById(orderId);
        model.addAttribute(ORDER_PARAMETER, order);
        return "decline_order";
    }


    @PostMapping("/manager_page/declineBooking/process")
    public String processDeclineBooking(@RequestParam(name = "id") int orderId,
                                        @RequestParam(name = "comment") String comment,
                                        @RequestParam(name = "carId") int carId,
                                        RedirectAttributes redirectAttributes){
        orderService.declineOrder(orderId, comment);
        carService.changeStatus(carId, Status.AVAILABLE);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, SUCCESS_MESSAGE);
        return "redirect:/manager_page";
    }


    @GetMapping("/manager_page/finishBooking")
    public String finishBooking(@RequestParam(name = "id") int orderId, Model model){
        Order order = orderService.findById(orderId);
        model.addAttribute(ORDER_PARAMETER, order);
        return "finish_booking";
    }


    @PostMapping("/manager_page/finishBooking/process")
    public String processFinishBooking(@RequestParam(name = "id") int orderId,
                                        @RequestParam(name = "comment") String comment,
                                        @RequestParam(name = "carId") int carId,
                                       @RequestParam(name = "penalty") String penalty,
                                       RedirectAttributes redirectAttributes){
        try{
            orderService.finishOrder(orderId, comment, penalty);
            carService.changeStatus(carId, Status.AVAILABLE);
            redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, SUCCESS_MESSAGE);
        }catch (ServiceException exception){
            redirectAttributes.addFlashAttribute(FAIL_MESSAGE, FAIL_MESSAGE);
        }
        return "redirect:/manager_page";
    }
}
