package com.example.carrentalspringproject.model.entity;

import com.example.carrentalspringproject.model.entity.enums.Status;
import com.example.carrentalspringproject.model.entity.enums.Transmission;
import com.example.carrentalspringproject.model.entity.enums.converter.StatusConverter;
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
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "model")
    private String model;

    @Column(name = "passengers")
    private int passengers;

    @Column(name = "price")
    private int price;

    @Convert(converter = StatusConverter.class)
    @Column(name = "status_id")
    private Status status;

    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "car")
    private List<Order> orders;

    public Car(){}

    public Car(int id, Brand brand, String model, int passengers, int price, Status status, Transmission transmission, City city, Category category, String imageUrl) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.passengers = passengers;
        this.price = price;
        this.status = status;
        this.transmission = transmission;
        this.city = city;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public void addOrderToCar(Order order){
        if (orders == null)
            orders = new ArrayList<>();
        orders.add(order);
        order.setCar(this);
    }
}
