package com.example.carrentalspringproject.controller.dto;

import com.example.carrentalspringproject.model.entity.Brand;
import com.example.carrentalspringproject.model.entity.Car;
import com.example.carrentalspringproject.model.entity.Category;
import com.example.carrentalspringproject.model.entity.City;
import com.example.carrentalspringproject.model.entity.enums.Transmission;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CarDto {

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

    public CarDto(){}

    public CarDto(int id, Brand brand, String model, int passengers, int priceForOneDay, int priceAllDays, Transmission transmission, City city, Category category, String imageUrl) {
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

    public static List<CarDto> mapCarListToDtoList(List<Car> carList, long days){
        List<CarDto> dtoList = new ArrayList<>();
        for (Car car: carList){
            CarDto carsDto = new CarDto();
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

    public static CarDto mapCarToDto(Car car, long days){
        CarDto carDto = new CarDto();
        carDto.id = car.getId();
        carDto.brand = car.getBrand();
        carDto.model = car.getModel();
        carDto.passengers = car.getPassengers();
        carDto.priceForOneDay = car.getPrice();
        carDto.priceAllDays = (int) (car.getPrice() * days);
        carDto.transmission = car.getTransmission();
        carDto.city = car.getCity();
        carDto.category = car.getCategory();
        carDto.imageUrl = car.getImageUrl();
        return carDto;
    }

    public static Car mapDtoToCar(CarDto carDto){
        Car car = new Car();
        car.setId(carDto.getId());
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setPassengers(carDto.getPassengers());
        car.setPrice(carDto.getPriceForOneDay());
        car.setTransmission(carDto.getTransmission());
        car.setCity(carDto.getCity());
        car.setCategory(carDto.getCategory());
        car.setImageUrl(carDto.getImageUrl());
        return car;
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
