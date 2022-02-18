package com.example.carrentalspringproject.controller;

import com.example.carrentalspringproject.controller.dto.CarDto;
import com.example.carrentalspringproject.exception.ServiceException;
import com.example.carrentalspringproject.model.entity.Brand;
import com.example.carrentalspringproject.model.entity.Category;
import com.example.carrentalspringproject.model.entity.City;
import com.example.carrentalspringproject.service.BrandService;
import com.example.carrentalspringproject.service.CarService;
import com.example.carrentalspringproject.service.CategoryService;
import com.example.carrentalspringproject.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import static com.example.carrentalspringproject.controller.Constants.*;

@Controller
public class MainController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CarService carService;

    @GetMapping("/")
    public String mainPage(Model model){
        List<Brand> brands = brandService.findAll();
        List<Category> categories = categoryService.findAll();
        List<City> cities = cityService.findAll();
        model.addAttribute(BRANDS_PARAMETER, brands);
        model.addAttribute(CATEGORIES_PARAMETER, categories);
        model.addAttribute(CITIES_PARAMETER, cities);
        model.addAttribute(PICKUP_DATE_PARAMETER, LocalDate.now().plusDays(1));
        model.addAttribute(DROPOFF_DATE_PARAMETER, LocalDate.now().plusDays(3+ (int) (Math.random()*4)));
        return "index";
    }

    @GetMapping("/search_cars")
    public String searchCars(@RequestParam int brand, @RequestParam int city, @RequestParam int category,
                           @RequestParam String pickupDate, @RequestParam String dropoffDate,
                           HttpSession session, Model model, RedirectAttributes redirectAttributes){
        List<CarDto> availableCars = null;
        try{
            availableCars = carService.findAllAvailableCars(brand, city, category, pickupDate, dropoffDate, session);
        }catch (ServiceException exception){
            redirectAttributes.addFlashAttribute(ERROR_PARAMETER, exception.getMessage());
            return "redirect:/";
        }

        System.out.println(availableCars);
        model.addAttribute(CARS_PARAMETER, availableCars);
        return "search_cars";
    }

    @GetMapping("/search_cars/sortByPrice")
    public String searchCarsSortByPrice(@RequestParam int brand, @RequestParam int city, @RequestParam int category,
                                        HttpSession session, Model model){
        List<CarDto> availableCars = carService.findAllAvailableCarsSortedByPrice(brand, city, category, session);
        System.out.println(availableCars);
        model.addAttribute(CARS_PARAMETER, availableCars);
        return "search_cars";
    }

    @GetMapping("/search_cars/sortByName")
    public String searchCarsSortByName(@RequestParam int brand, @RequestParam int city, @RequestParam int category,
                                       HttpSession session, Model model){
        List<CarDto> availableCars = carService.findAllAvailableCarsSortedByName(brand, city, category, session);
        System.out.println(availableCars);
        model.addAttribute(CARS_PARAMETER, availableCars);
        return "search_cars";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/success")
    public String loginPageRedirect(Authentication authResult){
        String role = authResult.getAuthorities().toString().strip();
        System.out.println("ROLE - " + role);
        String url = "";
        switch (role){
            case "[ADMIN]": url = "/admin_page"; break;
            case "[MANAGER]":url = "/manager_page"; break;
            default: url = "/my_booking";
        }
        String redirectUrl = "redirect:" + url;
        return redirectUrl;
    }



}
