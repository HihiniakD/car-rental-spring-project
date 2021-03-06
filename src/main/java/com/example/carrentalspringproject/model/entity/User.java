package com.example.carrentalspringproject.model.entity;

import com.example.carrentalspringproject.model.entity.enums.Role;
import com.example.carrentalspringproject.model.entity.enums.converter.RoleConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@EqualsAndHashCode
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", blocked=" + blocked +
                '}';
    }
}
