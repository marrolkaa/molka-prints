package com.example.myapplication.ui.product;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;

import com.example.myapplication.CartManager;
import com.example.myapplication.Produit;
import com.example.myapplication.R;
import com.example.myapplication.ui.cart.CartActivity;


import java.util.ArrayList;
import java.util.List;

public class StickersActivity extends AppCompatActivity {

    public SearchView searchView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stickers);
        searchView = findViewById(R.id.searchView);

        LinearLayout productListLayout = findViewById(R.id.productListLayout);

        // Add cute animated message
        TextView infoText = new TextView(this);
        infoText.setText("✨all the stickers are 1dt  💖");
        infoText.setTextSize(16);
        infoText.setTextColor(ContextCompat.getColor(this, R.color.pink));
        infoText.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        infoText.setPadding(48, 24, 24, 24); // More padding left
        productListLayout.addView(infoText, 0);

        // Animation
        Animation popUpAnim = new ScaleAnimation(
                0.8f, 1.0f, 0.8f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        popUpAnim.setDuration(700);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(700);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(popUpAnim);
        animationSet.addAnimation(fadeIn);
        infoText.startAnimation(animationSet);

        // Product data
        List<Produit> products = new ArrayList<>();
        products.add(new Produit("Sticker A", "Cute Star Sticker", "1dt", R.drawable.s1, "Stickers", 4.5f, true));
        products.add(new Produit("Sticker B", "Heart Sticker", "1dt", R.drawable.s2, "Stickers", 4.0f, true));
        products.add(new Produit("Sticker C", "Animal Sticker", "1dt", R.drawable.s3, "Stickers", 4.2f, false));
        products.add(new Produit("Sticker D", "Floral Sticker", "1dt", R.drawable.s4, "Stickers", 4.5f, true));
        products.add(new Produit("Sticker E", "Rainbow Sticker", "1dt", R.drawable.s5, "Stickers", 4.2f, false));
        products.add(new Produit("Sticker F", "Fruit Sticker", "1dt", R.drawable.s6, "Stickers", 4.5f, true));
        products.add(new Produit("Sticker G", "Cloud Sticker", "1dt", R.drawable.s7, "Stickers", 4.0f, true));
        products.add(new Produit("Sticker H", "Coffee Sticker", "1dt", R.drawable.s8, "Stickers", 4.2f, false));
        products.add(new Produit("Sticker I", "Plant Sticker", "1dt", R.drawable.m10, "Stickers", 4.5f, true));
        products.add(new Produit("Sticker J", "Ocean Sticker", "1dt", R.drawable.m11, "Stickers", 4.0f, true));
        products.add(new Produit("Sticker K", "Book Sticker", "1dt", R.drawable.m12, "Stickers", 4.2f, false));
        List<Product> products = new ArrayList<>();
        products.add(new Product(R.drawable.s1, "1dt", "Sticker A", "coffee girlie sticker", "Stickers", 4.5f, true));
        products.add(new Product(R.drawable.s2, "1dt", "Sticker B", "professional coffee drinker", "Stickers", 4.0f, true));
        products.add(new Product(R.drawable.s3, "1dt", "Sticker C", "flpwer Sticker", "Stickers", 4.2f, false));
        products.add(new Product(R.drawable.s4, "1dt", "Sticker D", "Floral Sticker", "Stickers", 4.5f, true));
        products.add(new Product(R.drawable.s5, "1dt", "Sticker E", "flower Sticker", "Stickers", 4.2f, false));
        products.add(new Product(R.drawable.s6, "1dt", "Sticker F", "butterfly Sticker", "Stickers", 4.5f, true));
        products.add(new Product(R.drawable.s7, "1dt", "Sticker G", "heart  Sticker", "Stickers", 4.0f, true));
        products.add(new Product(R.drawable.s8, "1dt", "Sticker H", "hearts Sticker", "Stickers", 4.2f, false));

        // Search filter
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = null;
                switch (query.toLowerCase()) {
                    case "stickers":
                    case "sticker":
                        Toast.makeText(StickersActivity.this, "you are already in the stickers page : " + query, Toast.LENGTH_SHORT).show();
                        break;
                    case "flower bouquet":
                        intent = new Intent(StickersActivity.this, Flower.class);
                        break;
                    case "mug":
                        intent = new Intent(StickersActivity.this, Mug.class);
                        break;
                    case "posters":
                    case "poster":
                        intent = new Intent(StickersActivity.this, Posters.class);
                        break;
                    case "sticky notes":
                        startActivity(new Intent(StickersActivity.this, Stickynotes.class));
                        break;
                    case "note books":
                        intent = new Intent(StickersActivity.this, Notebook.class);
                        break;
                    case "daily planner":
                        intent = new Intent(StickersActivity.this, Dailyplanner.class);
                        break;
                    default:
                        Toast.makeText(StickersActivity.this, "no product called by  : " + query, Toast.LENGTH_SHORT).show();
                        return true;
                }
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // 🛒 Vers panier
        ImageView cartImage = findViewById(R.id.cartImageView);
        cartImage.setOnClickListener(v -> {
            Intent intent = new Intent(StickersActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // 🛍️ Affichage des produits
        for (Produit product : products) {
            LinearLayout productItem = new LinearLayout(this);
            productItem.setOrientation(LinearLayout.VERTICAL);
            productItem.setPadding(16, 16, 16, 16);

            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    800));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(product.getImageResId());

            TextView titleView = new TextView(this);
            titleView.setText(product.getTitle());
            titleView.setTextSize(18);

            TextView priceView = new TextView(this);
            priceView.setText("Price : " + product.getPrice());

            Button addToCartButton = new Button(this);
            addToCartButton.setText("ADD TO CART");
            addToCartButton.setBackgroundColor(ContextCompat.getColor(this, R.color.pink));
            addToCartButton.setTextColor(Color.WHITE); // Pour que le texte soit bien visible
            addToCartButton.setOnClickListener(v -> {
                CartManager.addToCart(product);
                Toast.makeText(StickersActivity.this, product.getTitle() + " ADDED TO CART", Toast.LENGTH_SHORT).show();
            });
            productItem.addView(imageView);
            productItem.addView(titleView);
            productItem.addView(priceView);
            productItem.addView(addToCartButton);
            productListLayout.addView(productItem);
        }
    }
}
