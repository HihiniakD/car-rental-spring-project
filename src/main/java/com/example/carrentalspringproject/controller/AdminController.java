package com.example.carrentalspringproject.controller;

import com.example.carrentalspringproject.controller.dto.SignUpUserDto;
import com.example.carrentalspringproject.exception.ServiceException;
import com.example.carrentalspringproject.model.entity.*;
import com.example.carrentalspringproject.model.entity.enums.Role;
import com.example.carrentalspringproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

import static com.example.carrentalspringproject.controller.Constants.*;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CityService cityService;

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
    public String processBlockingManager(@RequestParam(name = "id") int managerId,
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


    @GetMapping("/admin_page/users")
    public String showAllUsers(Model model){
        return findPaginatedUsers(1, model);
    }


    @GetMapping("/admin_page/users/page/{pageNo}")
    public String findPaginatedUsers(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 10;
        Page< User > page = userService.findUserPaginated(pageNo, pageSize);
        List < User > users = page.getContent();
        model.addAttribute(CURRENT_PAGE_PARAMETER, pageNo);
        model.addAttribute(NUMBER_OF_PAGES_PARAMETER, page.getTotalPages());
        model.addAttribute(TOTAL_ITEMS_PARAMETER, page.getTotalElements());
        model.addAttribute(USERS_PARAMETER, users);
        return "users";
    }


    @GetMapping("/admin_page/users/processBlocking")
    public String processBlockingUser(@RequestParam(name = "id") int userId,
                                  @RequestParam(name = "blocked") boolean blocked,
                                  RedirectAttributes redirectAttributes){
        userService.changeBlockedStatus(userId, blocked);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, SUCCESS_MESSAGE);
        return "redirect:/admin_page/users";
    }


    @GetMapping("/admin_page/cars")
    public String showAllCars(Model model){
        return findPaginatedCars(1, model);
    }


    @GetMapping("/admin_page/cars/page/{pageNo}")
    public String findPaginatedCars(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 10;
        Page<Car> page = carService.findCarPaginated(pageNo, pageSize);
        List < Car > cars = page.getContent();
        model.addAttribute(CURRENT_PAGE_PARAMETER, pageNo);
        model.addAttribute(NUMBER_OF_PAGES_PARAMETER, page.getTotalPages());
        model.addAttribute(TOTAL_ITEMS_PARAMETER, page.getTotalElements());
        model.addAttribute(CARS_PARAMETER, cars);
        return "cars";
    }


    @GetMapping("/admin_page/cars/deleteCar")
    public String deleteCar(@RequestParam(name = "id") int carID,
                            RedirectAttributes redirectAttributes){
        carService.deleteCar(carID);
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, SUCCESS_MESSAGE);
        return "redirect:/admin_page/cars";
    }


    @GetMapping("/admin_page/cars/editCar")
    public String editCar(@RequestParam(name = "id") int carID,
                            Model model){
        Car car = carService.findCarById(carID);
        model.addAttribute(CAR_PARAMETER, car);
        return "edit_car";
    }


    @PostMapping("/admin_page/cars/editCar/process")
    public String processEditCar(@RequestParam(name = "id") int carID,
                                 @RequestParam(name = "price") String price,
                                 @RequestParam(name = "url") String imageUrl,
                          RedirectAttributes redirectAttributes){
        try {
            carService.editCar(carID, price, imageUrl);
            redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, SUCCESS_MESSAGE);
        }catch (ServiceException exception){
            redirectAttributes.addFlashAttribute(FAIL_MESSAGE, FAIL_MESSAGE);
        }
        return "redirect:/admin_page/cars";
    }


    @GetMapping("/admin_page/cars/addCar")
    public String addCar(Model model){
        List<Brand> brands = brandService.findAll();
        List<Category> categories = categoryService.findAll();
        List<City> cities = cityService.findAll();
        model.addAttribute(BRANDS_PARAMETER, brands);
        model.addAttribute(CATEGORIES_PARAMETER, categories);
        model.addAttribute(CITIES_PARAMETER, cities);
        return "add_car";
    }


    @PostMapping("/admin_page/cars/addCar/process")
    public String processAddCar(@RequestParam(name = "city") int cityID,
                                 @RequestParam(name = "brand") int brandId,
                                 @RequestParam(name = "model") String modelName,
                                 @RequestParam(name = "category") int categoryId,
                                 @RequestParam(name = "transmission") String transmission,
                                 @RequestParam(name = "passengers") int passengers,
                                 @RequestParam(name = "price") String price,
                                 @RequestParam(name = "url") String imageUrl,
                                 Model model, RedirectAttributes redirectAttributes){
        Brand brand = brandService.findById(brandId);
        Category category = categoryService.findById(categoryId);
        City city = cityService.findById(cityID);
        try {
            carService.addCar(brand, modelName, passengers, price, transmission,
                    category, city, imageUrl);
            redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, SUCCESS_MESSAGE);
            return "redirect:/admin_page/cars";
        }catch (ServiceException exception){
            redirectAttributes.addFlashAttribute(FAIL_MESSAGE, FAIL_MESSAGE);
        }
        return "redirect:/admin_page/cars";
    }
}
