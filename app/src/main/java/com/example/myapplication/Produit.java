package com.example.myapplication;

import java.io.Serializable;

public class Produit implements Serializable {
    private String title;
    private String description;
    private String price;
    private int imageResId;
    private String category;
    private float rating;
    private boolean available;
    private int quantity = 1;

    public Produit(String title, String description, String price, int imageResId, String category, float rating, boolean available) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
        this.category = category;
        this.rating = rating;
        this.available = available;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public String getCategory() { return category; }
    public float getRating() { return rating; }
    public boolean isAvailable() { return available; }
    public int getQuantity() { return quantity; }

    // Setters
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
