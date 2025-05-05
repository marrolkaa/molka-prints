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

public class Posters extends AppCompatActivity {

    public SearchView searchView;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ourproduct);
        searchView = findViewById(R.id.searchView);

        LinearLayout productListLayout = findViewById(R.id.productListLayout);

        // Add cute animated message
        TextView infoText = new TextView(this);
        infoText.setText("✨ all the poster are 5dt💖");
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
        products.add(new Produit("poster A", "Cute posters", "3dt", R.drawable.p3, "posters", 4.5f, true));
        products.add(new Produit("Sticker B", "pink poster", "3dt", R.drawable.p2, "posters", 4.0f, true));
        products.add(new Produit("Sticker C", "flowers poster", "3dt", R.drawable.p4, "posters", 4.2f, false));
        products.add(new Produit("Sticker D", "cherry poster", "3dt", R.drawable.p5, "posters", 4.5f, true));
        products.add(new Produit("Sticker E", "pink poster", "3dt", R.drawable.p6, "posters", 4.2f, false));
        products.add(new Produit("Sticker F", "cherry poster", "3dt", R.drawable.p7, "posters", 4.5f, true));
        products.add(new Produit("Sticker G", "pink poster", "3dt", R.drawable.p8, "posters", 4.0f, true));
        products.add(new Produit("Sticker H", "cute poster", "3dt", R.drawable.p9, "posters", 4.2f, false));



        // Search filter
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = null;
                switch (query.toLowerCase()) {
                    case "stickers":
                    case "sticker":
                        intent = new Intent(Posters.this, StickersActivity.class);
                        break;
                    case "flower bouquet":
                        intent = new Intent(Posters.this, Flower.class);
                        break;
                    case "mug":
                        intent = new Intent(Posters.this, Mug.class);
                        break;
                    case "posters":
                        Toast.makeText(Posters.this, "DEJA DANS LA PAGE DE POSTERS : " + query, Toast.LENGTH_SHORT).show();
                        break;
                    case "note books":
                        intent = new Intent(Posters.this, Notebook.class);
                        break;
                    case "daily planner":
                        intent = new Intent(Posters.this, Dailyplanner.class);
                        break;
                    default:
                        Toast.makeText(Posters.this, "Aucun produit trouvé pour : " + query, Toast.LENGTH_SHORT).show();
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
        ImageView cartImage = findViewById(R.id.cartImageView);
        cartImage.setOnClickListener(v -> {
            Intent intent = new Intent(Posters.this, CartActivity.class);
            startActivity(intent);
        });
        // Display products dynamically
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
            priceView.setText("Prix : " + product.getPrice());

            Button addToCartButton = new Button(this);
            addToCartButton.setText("Ajouter au panier");
            addToCartButton.setBackgroundColor(ContextCompat.getColor(this, R.color.pink));
            addToCartButton.setTextColor(Color.WHITE); // Pour que le texte soit bien visible
            addToCartButton.setOnClickListener(v -> {
                CartManager.addToCart(product);
                Toast.makeText(Posters.this, product.getTitle() + " ajouté au panier", Toast.LENGTH_SHORT).show();
            });

            productItem.addView(imageView);
            productItem.addView(titleView);
            productItem.addView(priceView);
            productItem.addView(addToCartButton);

            productListLayout.addView(productItem);
        }


    }}
