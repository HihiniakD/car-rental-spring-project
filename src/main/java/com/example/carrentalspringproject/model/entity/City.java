package com.example.carrentalspringproject.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city")
    private List<Car> cars;

    @OneToMany(mappedBy = "city")
    private List<Order> orders;

    public City(){}

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addCarToCity(Car car){
        if (cars == null)
            cars = new ArrayList<>();
        cars.add(car);
        car.setCity(this);
    }

    public void addOrderToCity(Order order){
        if (orders == null)
            orders = new ArrayList<>();
        orders.add(order);
        order.setCity(this);
    }
}
