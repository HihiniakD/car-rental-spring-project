package com.example.carrentalspringproject.model.entity;

import com.example.carrentalspringproject.model.entity.enums.Status;
import com.example.carrentalspringproject.model.entity.enums.converter.StatusConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "pickup_date")
    private LocalDate pickUpDate;

    @Column(name = "dropoff_date")
    private LocalDate dropOffDate;

    @Column(name = "total_price")
    private int totalPrice;

    @Convert(converter = StatusConverter.class)
    @Column(name = "status_id")
    private Status status;

    @Column(name = "driver")
    private boolean withDriver;

    @Column(name = "comment")
    private String comment;

    public Order(){}

    public Order(int id, User user, Car car, City city, LocalDate pickUpDate, LocalDate dropOffDate, int totalPrice, Status status, boolean withDriver, String comment) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.city = city;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.withDriver = withDriver;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", pickUpDate=" + pickUpDate +
                ", dropOffDate=" + dropOffDate +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", withDriver=" + withDriver +
                ", comment='" + comment + '\'' +
                '}';
    }
}
