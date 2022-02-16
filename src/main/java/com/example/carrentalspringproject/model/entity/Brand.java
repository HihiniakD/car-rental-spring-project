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
@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Car> cars;

    public Brand(){}

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addCarToBrand(Car car){
        if (cars == null)
            cars = new ArrayList<>();
        cars.add(car);
        car.setBrand(this);
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
