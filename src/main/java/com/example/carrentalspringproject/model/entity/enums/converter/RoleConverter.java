package com.example.carrentalspringproject.model.entity.enums.converter;

import com.example.carrentalspringproject.model.entity.enums.Role;

import javax.persistence.AttributeConverter;

public class RoleConverter implements AttributeConverter<Role, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Role role) {
        if (role == null)
            return null;

        switch (role){
            case USER: return 1;
            case ADMIN: return 2;
            case MANAGER: return 3;
            default:
                throw new IllegalArgumentException(role + " not supported.");
        }
    }

    @Override
    public Role convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        switch (dbData){
            case 1: return Role.USER;
            case 2: return Role.ADMIN;
            case 3: return Role.MANAGER;
            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }
}
