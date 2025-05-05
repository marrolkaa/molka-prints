package com.example.myapplication.ui.product;

import java.io.Serializable;

public class Product implements Serializable {

    private String title;
    private String description;
    private String price;
    private int imageResId;
    private String category;
    private float rating;
    private boolean available;

    // ✅ Champ pour la quantité dans le panier
    private int quantity = 1;

    // 🔹 Constructeur complet
    public Product(String title, String description, String price, int imageResId,
                   String category, float rating, boolean available) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
        this.category = category;
        this.rating = rating;
        this.available = available;
    }

    // 🔹 Getters et Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // 🔹 Gestion de la quantité
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
