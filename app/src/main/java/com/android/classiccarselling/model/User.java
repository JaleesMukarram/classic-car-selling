package com.android.classiccarselling.model;

import androidx.annotation.NonNull;

import java.util.List;

public class User {

    private String id;
    private String name;
    private String city;

    private StorageImage profilePic;

    private List<String> wishListCarIds;
    private List<String> savedListCarIds;

    public User() {
    }

    public User(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public User(String id, String name, String city, StorageImage profilePic) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.profilePic = profilePic;
    }

    public User(String id, String name, String city, StorageImage profilePic, List<String> wishListCarIds, List<String> savedListCarIds) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.profilePic = profilePic;
        this.wishListCarIds = wishListCarIds;
        this.savedListCarIds = savedListCarIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public StorageImage getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(StorageImage profilePic) {
        this.profilePic = profilePic;
    }

    public List<String> getWishListCarIds() {
        return wishListCarIds;
    }

    public void setWishListCarIds(List<String> wishListCarIds) {
        this.wishListCarIds = wishListCarIds;
    }

    public List<String> getSavedListCarIds() {
        return savedListCarIds;
    }

    public void setSavedListCarIds(List<String> savedListCarIds) {
        this.savedListCarIds = savedListCarIds;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", profilePic=" + profilePic +
                ", wishListCarIds=" + wishListCarIds +
                ", savedListCarIds=" + savedListCarIds +
                '}';
    }
}
