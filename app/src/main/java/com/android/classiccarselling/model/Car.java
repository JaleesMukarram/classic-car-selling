package com.android.classiccarselling.model;

public class Car {



    private Brand brand;
    private String model;
    private double price;

    public Car(Brand brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
