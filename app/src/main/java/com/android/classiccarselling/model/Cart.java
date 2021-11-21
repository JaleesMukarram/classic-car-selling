package com.android.classiccarselling.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private String userId;
    private List<String> carIds;

    public Cart() {
    }

    public Cart(String userId) {
        this.userId = userId;
        this.carIds = new ArrayList<>();
    }

    public Cart(String userId, List<String> carIds) {
        this.userId = userId;
        this.carIds = carIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getCarIds() {
        return carIds;
    }

    public void setCarIds(List<String> carIds) {
        this.carIds = carIds;
    }

    @NonNull
    @Override
    public String toString() {
        return "Cart{" +
                "userId='" + userId + '\'' +
                ", carIds=" + carIds +
                '}';
    }
}
