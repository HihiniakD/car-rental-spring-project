package com.example.carrentalspringproject.controller;

import com.example.carrentalspringproject.controller.dto.SignUpUserDto;
import com.example.carrentalspringproject.model.entity.User;
import com.example.carrentalspringproject.model.entity.enums.Role;
import com.example.carrentalspringproject.service.CarService;
import com.example.carrentalspringproject.service.OrderService;
import com.example.carrentalspringproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

import static com.example.carrentalspringproject.controller.Constants.*;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public SignUpUserDto userRegistrationDto() {
        return new SignUpUserDto();
    }

    @GetMapping("/admin_page")
    public String showAdminPage(){
        return "admin_page";
    }

    @GetMapping("/admin_page/managers")
    public String showAllManagers(Model model){
        List<User> managers = userService.findByRole(Role.MANAGER);
        model.addAttribute(MANAGERS_PARAMETER, managers);
        return "managers";
    }

    @GetMapping("/admin_page/managers/processBlocking")
    public String showAllManagers(@RequestParam(name = "id") int managerId,
                                  @RequestParam(name = "blocked") boolean blocked,
                                  RedirectAttributes redirectAttributes){
        userService.changeBlockedStatus(managerId, blocked);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, SUCCESS_MESSAGE);
        return "redirect:/admin_page/managers";
    }

    @GetMapping("/admin_page/managers/addManager")
    public String addManager(){
        return "add_manager";
    }

    @PostMapping("/admin_page/managers/addManager/process")
    public String processAddManager(@ModelAttribute("user") @Valid SignUpUserDto userDto,
                                    BindingResult result, Model model, RedirectAttributes redirectAttributes){

        User user = userService.findByEmail(userDto.getEmail());
        if(user != null) {
            model.addAttribute(ERROR_PARAMETER, EMAIL_EXISTS);
            return "add_manager";
        }
        if (result.hasErrors()){
            return "add_manager";
        }
        userService.createManager(userDto);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, SUCCESS_MESSAGE);
        return "redirect:/admin_page/managers";
    }
}
