package com.kaminsky.marketpayment.entity;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class PayedOrder {
    private UUID id;

    @NotNull(message = "Стоимость не может быть null")
    private int price;

    private Status status;

    private boolean isPaid;

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return id + " " + price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
