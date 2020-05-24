package com.spring.thread.model;

import java.io.Serializable;

public class Apple implements Serializable {

    private Double weight;

    private Double price;

    private Double amount;

    public Apple() {
    }

    public Apple(Double weight, Double price) {
        this.weight = weight;
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
