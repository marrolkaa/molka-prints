package com.example.myapplication.ui.product;

import android.annotation.SuppressLint;
import android.content.Intent;
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
        List<Product> products = new ArrayList<>();
        products.add(new Product(R.drawable.s1, "1dt", "Sticker A", "Cute Star Sticker", "Stickers", 4.5f, true));
        products.add(new Product(R.drawable.s2, "1dt", "Sticker B", "Heart Sticker", "Stickers", 4.0f, true));
        products.add(new Product(R.drawable.s3, "1dt", "Sticker C", "Animal Sticker", "Stickers", 4.2f, false));
        products.add(new Product(R.drawable.s4, "1dt", "Sticker D", "Floral Sticker", "Stickers", 4.5f, true));
        products.add(new Product(R.drawable.s5, "1dt", "Sticker E", "Rainbow Sticker", "Stickers", 4.2f, false));
        products.add(new Product(R.drawable.s6, "1dt", "Sticker F", "Fruit Sticker", "Stickers", 4.5f, true));
        products.add(new Product(R.drawable.s7, "1dt", "Sticker G", "Cloud Sticker", "Stickers", 4.0f, true));
        products.add(new Product(R.drawable.s8, "1dt", "Sticker H", "Coffee Sticker", "Stickers", 4.2f, false));
        products.add(new Product(R.drawable.m10, "1dt", "Sticker I", "Plant Sticker", "Stickers", 4.5f, true));
        products.add(new Product(R.drawable.m11, "1dt", "Sticker J", "Ocean Sticker", "Stickers", 4.0f, true));
        products.add(new Product(R.drawable.m12, "1dt", "Sticker K", "Book Sticker", "Stickers", 4.2f, false));

        // Search filter
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = null;
                switch (query.toLowerCase()) {
                    case "stickers":
                    case "sticker":
                        intent = new Intent(StickersActivity.this, StickersActivity.class);
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
                    case "note books":
                        intent = new Intent(StickersActivity.this, Notebook.class);
                        break;
                    case "daily planner":
                        intent = new Intent(StickersActivity.this, Dailyplanner.class);
                        break;
                    default:
                        Toast.makeText(StickersActivity.this, "Aucun produit trouvé pour : " + query, Toast.LENGTH_SHORT).show();
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

            TextView priceView = new TextView(this);
            priceView.setText("Prix : " + product.getPrice());

            Button addToCartButton = new Button(this);
            addToCartButton.setText("Ajouter au panier");
            addToCartButton.setOnClickListener(v -> {
                CartManager.addToCart(product);
                Toast.makeText(StickersActivity.this, product.getTitle() + " ajouté au panier", Toast.LENGTH_SHORT).show();
            });
            productItem.addView(imageView);
            productItem.addView(titleView);
            productItem.addView(priceView);
            productItem.addView(addToCartButton);
            productListLayout.addView(productItem);
        }
    }
}
