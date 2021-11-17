package com.android.classiccarselling.model;

import java.util.List;
import java.util.UUID;

public class Car {

    private String id;

    private Brand brand;
    private String name;

    private String year;
    private String registrationDate;
    private String color;
    private String kilometer;

    private double price;

    private List<StorageImage> images;

    public Car() {
    }

    public Car(Brand brand, String name, String year, String registrationDate, String color, String kilometer, double price, List<StorageImage> images) {
        this.id = UUID.randomUUID().toString();
        this.brand = brand;
        this.name = name;
        this.year = year;
        this.registrationDate = registrationDate;
        this.color = color;
        this.kilometer = kilometer;
        this.price = price;
        this.images = images;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getKilometer() {
        return kilometer;
    }

    public void setKilometer(String kilometer) {
        this.kilometer = kilometer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<StorageImage> getImages() {
        return images;
    }

    public void setImages(List<StorageImage> images) {
        this.images = images;
    }
}
