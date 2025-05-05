package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CartManager extends AppCompatActivity {

    private static final List<Produit> cartItems = new ArrayList<>();
    private TextView cartTotal;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toast.makeText(this, "✅ CartManager lancé", Toast.LENGTH_SHORT).show();


        cartTotal = findViewById(R.id.cartTotal);
        checkoutButton = findViewById(R.id.checkoutButton);

        // Vérifie si le bouton est bien trouvé
        if (checkoutButton != null) {
            checkoutButton.setOnClickListener(v -> {
                // Lors du clic sur le bouton "Passer la commande"
                Toast.makeText(this, "✅ Clic détecté sur le bouton Passer la commande", Toast.LENGTH_SHORT).show();

                // Appel de la méthode pour envoyer un e-mail ou faire d'autres actions
                checkout("client@example.com"); // Remplace avec l’e-mail de l’utilisateur connecté si tu en as un
            });
        } else {
            Toast.makeText(this, "⚠️ checkoutButton introuvable dans le layout", Toast.LENGTH_LONG).show();
        }

        // Lors du clic sur le bouton "Passer la commande"
        checkoutButton.setOnClickListener(v -> {
            Toast.makeText(this, "Passer la commande", Toast.LENGTH_SHORT).show();
            checkout("client@example.com"); // Remplace avec l’e-mail de l’utilisateur connecté si tu en as un
        });
    }

    // Ajouter un produit au panier
    public static void addToCart(Produit product) {
        cartItems.add(product);
    }

    // Récupérer les produits dans le panier
    public static List<Produit> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    // Mettre à jour les produits dans le panier
    public static void setCartItems(List<Produit> items) {
        cartItems.clear();
        cartItems.addAll(items);
    }

    // Envoyer un e-mail de confirmation
    public void checkout(String userEmail) {
        StringBuilder orderDetails = new StringBuilder();
        for (Produit product : cartItems) {
            orderDetails.append(product.getTitle())
                    .append(" - ")
                    .append(product.getPrice())
                    .append(" dt\n");
        }

        // Intent pour ouvrir une app d'e-mail
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{userEmail});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Confirmation de commande");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Merci pour votre commande chez Molka Prints !\n\nDétails de votre commande :\n" + orderDetails);

        try {
            startActivity(Intent.createChooser(emailIntent, "Envoyer un e-mail avec :"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Aucune application e-mail installée.", Toast.LENGTH_SHORT).show();
        }
    }
}
