package com.android.classiccarselling.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.UUID;

public class Car implements Parcelable {

    private String id;

    private String brand;
    private String name;

    private String year;
    private String registrationDate;
    private String color;
    private String kilometer;

    private double price;

    private List<StorageImage> images;

    public Car() {
    }

    public Car(String brand, String name, String year, String registrationDate, String color, String kilometer, double price, List<StorageImage> images) {
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

    protected Car(Parcel in) {
        id = in.readString();
        brand = in.readString();
        name = in.readString();
        year = in.readString();
        registrationDate = in.readString();
        color = in.readString();
        kilometer = in.readString();
        price = in.readDouble();
        images = in.createTypedArrayList(StorageImage.CREATOR);
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
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

    public String getFullName() {

        return this.brand + " " + this.name;
    }

    public String getListedPrice() {

        return "RM" + this.price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(brand);
        parcel.writeString(name);
        parcel.writeString(year);
        parcel.writeString(registrationDate);
        parcel.writeString(color);
        parcel.writeString(kilometer);
        parcel.writeDouble(price);
        parcel.writeTypedList(images);
    }
}
