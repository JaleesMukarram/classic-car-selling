package com.android.classiccarselling.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Cart implements Parcelable {

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

    protected Cart(Parcel in) {
        userId = in.readString();
        carIds = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeStringList(carIds);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

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
