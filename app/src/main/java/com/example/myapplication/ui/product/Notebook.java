package com.example.myapplication.ui.product;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.myapplication.databinding.ActivityMug2Binding;
import com.example.myapplication.ui.cart.CartActivity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.widget.Button;


public class Notebook extends AppCompatActivity {

    private SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);

        searchView = findViewById(R.id.searchView);
        LinearLayout productListLayout = findViewById(R.id.productListLayout);

        List<Produit> products = new ArrayList<>();
        products.add(new Produit("Custom Notebook", "A beautiful custom notebook.", "20dt", R.drawable.m1, "Stationery", 4.5f, true));
        products.add(new Produit("Stylish Planner", "A stylish planner.", "50dt", R.drawable.m2, "Stationery", 4.0f, true));
        products.add(new Produit("Compact Diary", "A compact diary.", "23dt", R.drawable.m3, "Stationery", 4.2f, false));
        products.add(new Produit("Custom Notebook", "A beautiful custom notebook.", "30dt", R.drawable.m4, "Stationery", 4.5f, true));
        products.add(new Produit("Compact Diary", "A compact diary.", "23dt", R.drawable.m6, "Stationery", 4.2f, false));
        products.add(new Produit("Custom Notebook", "A beautiful custom notebook.", "24dt", R.drawable.m7, "Stationery", 4.5f, true));
        products.add(new Produit("Stylish Planner", "A stylish planner.", "28.5dt", R.drawable.m8, "Stationery", 4.0f, true));
        products.add(new Produit("Compact Diary", "A compact diary.", "34dt", R.drawable.m9, "Stationery", 4.2f, false));
        products.add(new Produit("Custom Notebook", "A beautiful custom notebook.", "10dt", R.drawable.m10, "Stationery", 4.5f, true));
        products.add(new Produit("Stylish Planner", "A stylish planner.", "40dt", R.drawable.m11, "Stationery", 4.0f, true));
        products.add(new Produit("Compact Diary", "A compact diary.", "41dt", R.drawable.m12, "Stationery", 4.2f, false));


        List<Product> products = new ArrayList<>();
        products.add(new Product(R.drawable.m1, "20dt",                     "A beautiful custom notebook.", "Custom Notebook", "Stationery", 4.5f, true));
        products.add(new Product(R.drawable.m2, "50dt", "A quran journal.", " quran journal", "Stationery", 4.0f, true));
        products.add(new Product(R.drawable.m3, "23dt", "A diary.", " Diary", "Stationery", 4.2f, false));
        products.add(new Product(R.drawable.m4, "30dt", "our memories book.", "memories note book", "Stationery", 4.5f, true));
        products.add(new Product(R.drawable.m6, "23dt", "nail artist note book.", "nail artist note book.", "Stationery", 4.2f, false));
        products.add(new Product(R.drawable.m7, "24dt", "A beautiful custom notebook.", "Custom Notebook", "Stationery", 4.5f, true));
        products.add(new Product(R.drawable.m8, "28.5dt","wedding planner.", "wedding Planner", "Stationery", 4.0f, true));
        products.add(new Product(R.drawable.m9, "34dt", "A small note book .", "A small note book ", "Stationery", 4.2f, false));
        products.add(new Product(R.drawable.m10, "10dt", "my planner.", "my planner", "Stationery", 4.5f, true));
        products.add(new Product(R.drawable.m11, "40dt", "A goals planner.", "Stylish Planner", "Stationery", 4.0f, true));
        products.add(new Product(R.drawable.m12, "41dt", " diary.", "Compact Diary", "Stationery", 4.2f, false));

        // 🔍 Recherche
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                switch (query.toLowerCase()) {
                    case "stickers":
                    case "sticker":
                        startActivity(new Intent(Notebook.this, StickersActivity.class));
                        break;
                    case "flower bouquet":
                        startActivity(new Intent(Notebook.this, Flower.class));
                        break;
                    case "mug":
                        startActivity(new Intent(Notebook.this, Mug.class));
                        break;
                    case "posters":
                    case "poster":
                        startActivity(new Intent(Notebook.this, Posters.class));
                        break;
                    case "sticky notes":
                        startActivity(new Intent(Notebook.this, Stickynotes.class));
                        break;
                    case "note books":
                        Toast.makeText(Notebook.this, " you are already in the note  books page : " + query, Toast.LENGTH_SHORT).show();
                        break;
                    case "daily planner":
                        startActivity(new Intent(Notebook.this, Dailyplanner.class));
                        break;
                    default:
                        Toast.makeText(Notebook.this, "no product called by  : " + query, Toast.LENGTH_SHORT).show();
                        break;
                }
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
            Intent intent = new Intent(Notebook.this, CartActivity.class);
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
            imageView.setOnClickListener(v -> {
                Intent intent = new Intent(Notebook.this, ProductDetailActivity.class);
                intent.putExtra("product", product); // Serializable
                startActivity(intent);
            });

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
                Toast.makeText(Notebook.this, product.getTitle() + " ADDED TO CART", Toast.LENGTH_SHORT).show();
            });
            productItem.addView(imageView);
            productItem.addView(titleView);
            productItem.addView(priceView);
            productItem.addView(addToCartButton);
            productListLayout.addView(productItem);
        }
    }
}
