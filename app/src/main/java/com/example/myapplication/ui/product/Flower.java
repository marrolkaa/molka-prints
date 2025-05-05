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
import com.example.myapplication.Product;
import com.example.myapplication.R;
import com.example.myapplication.ui.cart.CartActivity;

import java.util.ArrayList;
import java.util.List;

public class Flower extends AppCompatActivity {
    public SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mug2);
        searchView = findViewById(R.id.searchView);

        LinearLayout productListLayout = findViewById(R.id.productListLayout);

        // Cute animated message
        TextView infoText = new TextView(this);
        infoText.setText("✨Flowers 💖");
        infoText.setTextSize(16);
        infoText.setTextColor(ContextCompat.getColor(this, R.color.pink));
        infoText.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        infoText.setPadding(48, 24, 24, 24);
        productListLayout.addView(infoText, 0);

        // Animation
        Animation popUpAnim = new ScaleAnimation(0.8f, 1.0f, 0.8f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        popUpAnim.setDuration(700);
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(700);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(popUpAnim);
        animationSet.addAnimation(fadeIn);
        infoText.startAnimation(animationSet);

        // Product data
        List<Product> products = new ArrayList<>();
        products.add(new Product(R.drawable.f1, "20dt", "coffee girlie mug", "blue graduation box", "", 0, true));
        products.add(new Product(R.drawable.f2, "25dt", "Sticker B", "Heart flower box", "Stickers", 4.0f, true));
        products.add(new Product(R.drawable.f4, "30dt", "Sticker C", "Heart flower box", "Stickers", 4.2f, false));
        products.add(new Product(R.drawable.f5, "25dt", "Sticker D", "soft purple flower box", "Stickers", 4.5f, true));
        products.add(new Product(R.drawable.f6, "25dt", "Sticker E", "pinky flower box", "Stickers", 4.2f, false));
        products.add(new Product(R.drawable.f3, "23dt", "Sticker F", "white flower box", "Stickers", 4.5f, true));
        products.add(new Product(R.drawable.f7, "60dt", "Sticker G", "3 boxes of flower", "Stickers", 4.0f, true));
        products.add(new Product(R.drawable.f8, "40dt", "Sticker H", "2 graduation boxes", "Stickers", 4.2f, false));
        products.add(new Product(R.drawable.f9, "29dt", "Sticker H", "Red flower box", "Stickers", 4.2f, false));
        products.add(new Product(R.drawable.f10, "30dt", "Sticker H", "Red graduation box", "Stickers", 4.2f, false));
        products.add(new Product(R.drawable.f11, "24dt", "Sticker H", "cute pinky flower box", "Stickers", 4.2f, false));
        products.add(new Product(R.drawable.f13, "45dt", "Sticker H", "2 white flower box", "Stickers", 4.2f, false));

        // Search filter
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = null;
                switch (query.toLowerCase()) {
                    case "stickers":
                    case "sticker":
                        intent = new Intent(Flower.this, StickersActivity.class);
                        break;
                    case "flower bouquet":
                        Toast.makeText(Flower.this, "You are already in flowers page : " + query, Toast.LENGTH_SHORT).show();
                        break;
                    case "mug":
                        intent = new Intent(Flower.this, Mug.class);
                        break;
                    case "posters":
                    case "poster":
                        intent = new Intent(Flower.this, Posters.class);
                        break;
                    case "note books":
                        break;
                    case "sticky notes":
                        startActivity(new Intent(Flower.this, Stickynotes.class));
                        break;
                    case "daily planner":
                        intent = new Intent(Flower.this, Dailyplanner.class);
                        break;
                    default:
                        Toast.makeText(Flower.this, "no product called by : " + query, Toast.LENGTH_SHORT).show();
                        return true;
                }
                if (intent != null) startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        ImageView cartImage = findViewById(R.id.cartImageView);
        cartImage.setOnClickListener(v -> {
            Intent intent = new Intent(Flower.this, CartActivity.class);
            startActivity(intent);
        });

        // Display products with image, title, price and button
        for (Product product : products) {
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
            titleView.setTextColor(Color.BLACK);

            TextView priceView = new TextView(this);
            priceView.setText("Price: " + product.getPrice());
            priceView.setTextSize(16);
            priceView.setTextColor(Color.DKGRAY);

            Button addToCartButton = new Button(this);
            addToCartButton.setText("ADD TO CART");
            addToCartButton.setBackgroundColor(ContextCompat.getColor(this, R.color.pink));
            addToCartButton.setTextColor(Color.WHITE);
            addToCartButton.setOnClickListener(v -> {
                CartManager.addToCart(product);
                Toast.makeText(Flower.this, product.getTitle() + " ADD TO CART", Toast.LENGTH_SHORT).show();
            });

            productItem.addView(imageView);
            productItem.addView(titleView);     // Titre du produit
            productItem.addView(priceView);     // Prix sous l'image
            productItem.addView(addToCartButton);

            productListLayout.addView(productItem);
        }
    }
}
