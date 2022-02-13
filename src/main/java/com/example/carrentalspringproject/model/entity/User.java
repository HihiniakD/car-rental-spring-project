package com.example.carrentalspringproject.model.entity;

import com.example.carrentalspringproject.model.entity.enums.Role;
import com.example.carrentalspringproject.model.entity.enums.converter.RoleConverter;
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
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Convert(converter = RoleConverter.class)
    @Column(name = "role_id")
    private Role role;

    @Column(name = "blocked")
    private boolean blocked;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User(){}

    public User(int id, String name, String password, String email, String phone, Role role, boolean blocked) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.blocked = blocked;
    }

    public void addOrderToUser(Order order){
        if (orders == null)
            orders = new ArrayList<>();
        orders.add(order);
        order.setUser(this);
    }
}
