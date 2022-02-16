package com.example.carrentalspringproject.controller.dto;

import com.example.carrentalspringproject.model.entity.Brand;
import com.example.carrentalspringproject.model.entity.Car;
import com.example.carrentalspringproject.model.entity.Category;
import com.example.carrentalspringproject.model.entity.City;
import com.example.carrentalspringproject.model.entity.enums.Status;
import com.example.carrentalspringproject.model.entity.enums.Transmission;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SearchCarsDto {

    private int id;
    private Brand brand;
    private String model;
    private int passengers;
    private int priceForOneDay;
    private int priceAllDays;
    private Transmission transmission;
    private City city;
    private Category category;
    private String imageUrl;

    public SearchCarsDto(){}

    public SearchCarsDto(int id, Brand brand, String model, int passengers, int priceForOneDay, int priceAllDays, Transmission transmission, City city, Category category, String imageUrl) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.passengers = passengers;
        this.priceForOneDay = priceForOneDay;
        this.priceAllDays = priceAllDays;
        this.transmission = transmission;
        this.city = city;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public static List<SearchCarsDto> mapCarListToDtoList(List<Car> carList, long days){
        List<SearchCarsDto> dtoList = new ArrayList<>();
        for (Car car: carList){
            SearchCarsDto carsDto = new SearchCarsDto();
            carsDto.id = car.getId();
            carsDto.brand = car.getBrand();
            carsDto.model = car.getModel();
            carsDto.passengers = car.getPassengers();
            carsDto.priceForOneDay = car.getPrice();
            carsDto.priceAllDays = (int) (car.getPrice() * days);
            carsDto.transmission = car.getTransmission();
            carsDto.city = car.getCity();
            carsDto.category = car.getCategory();
            carsDto.imageUrl = car.getImageUrl();
            dtoList.add(carsDto);
        }
        return dtoList;
    }

    @Override
    public String toString() {
        return "SearchCarsDto{" +
                "id=" + id +
                ", passengers=" + passengers +
                ", priceForOneDay=" + priceForOneDay +
                ", priceAllDays=" + priceAllDays +
                ", transmission=" + transmission +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
