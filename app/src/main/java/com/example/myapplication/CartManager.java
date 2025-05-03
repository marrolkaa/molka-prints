package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static final List<Product> cartItems = new ArrayList<>();

    public static void addToCart(Product product) {
        cartItems.add(product);
    }

    public static List<Product> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public static void removeFromCart(Product product) {
        cartItems.remove(product);
    }

    public static void clearCart() {
        cartItems.clear();
    }

    // ✅ Calcule le total des prix
    public static float getTotalPrice() {
        float total = 0;
        for (Product product : cartItems) {
            try {
                String priceStr = product.getPrice().replace("dt", "").trim();
                total += Float.parseFloat(priceStr);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Ignorer les erreurs de parsing
            }
        }
        return total;
    }
}
