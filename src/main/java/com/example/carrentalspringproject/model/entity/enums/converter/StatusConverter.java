package com.example.carrentalspringproject.model.entity.enums.converter;

import com.example.carrentalspringproject.model.entity.enums.Status;

import javax.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<Status, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Status status) {
        if (status == null)
            return null;

        switch (status){
            case AVAILABLE: return 1;
            case BUSY: return 2;
            case PROCESSING: return 3;
            case APPROVED: return 4;
            case CANCELED: return 5;
            case DONE: return 6;
            default:
                throw new IllegalArgumentException(status + " not supported.");
        }
    }

    @Override
    public Status convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        switch (dbData){
            case 1: return Status.AVAILABLE;
            case 2: return Status.BUSY;
            case 3: return Status.PROCESSING;
            case 4: return Status.APPROVED;
            case 5: return Status.CANCELED;
            case 6: return Status.DONE;
            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }
}
