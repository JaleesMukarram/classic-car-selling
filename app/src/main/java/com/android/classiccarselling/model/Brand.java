package com.android.classiccarselling.model;

import androidx.annotation.NonNull;

import java.util.UUID;

public class Brand {

    private String id;
    private String name;
    private StorageImage image;


    public Brand(String name, StorageImage image) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.image = image;
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

    public StorageImage getImage() {
        return image;
    }

    public void setImage(StorageImage image) {
        this.image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", image=" + image +
                '}';
    }
}
