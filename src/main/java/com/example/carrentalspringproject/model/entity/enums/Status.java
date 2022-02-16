package com.example.carrentalspringproject.model.entity.enums;

/**
 * ENUM for all DB statuses
 *
 */
public enum Status {
    AVAILABLE(1),
    BUSY(2),
    PROCESSING(3),
    APPROVED(4),
    CANCELED(5),
    DONE(6);

    private int index;

    Status(int status) {
        this.index = status;
    }

    public int getStatus() {
        return index;
    }

}
