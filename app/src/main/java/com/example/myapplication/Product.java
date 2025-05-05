package com.example.myapplication;

import java.io.Serializable;

public class Product implements Serializable {
    private int imageResId;
    private String price;
    private String description;
    private String title;
    private String category;
    private float rating;
    private boolean available;

    public Product(int imageResId, String price, String description, String title,
                   String category, float rating, boolean available) {
        this.imageResId = imageResId;
        this.price = price;
        this.description = description;
        this.title = title;
        this.category = category;
        this.rating = rating;
        this.available = available;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getRating() {
        return rating;
    }

    public boolean isAvailable() {
        return available;
    }
}