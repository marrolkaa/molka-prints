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
import com.example.myapplication.Product;
import com.example.myapplication.R;
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

        List<Product> products = new ArrayList<>();
        products.add(new Product(R.drawable.m1, "20dt", "A beautiful custom notebook.", "Custom Notebook", "Stationery", 4.5f, true));
        products.add(new Product(R.drawable.m2, "50dt", "A stylish planner.", "Stylish Planner", "Stationery", 4.0f, true));
        products.add(new Product(R.drawable.m3, "23dt", "A compact diary.", "Compact Diary", "Stationery", 4.2f, false));
        products.add(new Product(R.drawable.m4, "30dt", "A beautiful custom notebook.", "Custom Notebook", "Stationery", 4.5f, true));
        products.add(new Product(R.drawable.m6, "23dt", "A compact diary.", "Compact Diary", "Stationery", 4.2f, false));
        products.add(new Product(R.drawable.m7, "24dt", "A beautiful custom notebook.", "Custom Notebook", "Stationery", 4.5f, true));
        products.add(new Product(R.drawable.m8, "28.5dt","A stylish planner.", "Stylish Planner", "Stationery", 4.0f, true));
        products.add(new Product(R.drawable.m9, "34dt", "A compact diary.", "Compact Diary", "Stationery", 4.2f, false));
        products.add(new Product(R.drawable.m10, "10dt", "A beautiful custom notebook.", "Custom Notebook", "Stationery", 4.5f, true));
        products.add(new Product(R.drawable.m11, "40dt", "A stylish planner.", "Stylish Planner", "Stationery", 4.0f, true));
        products.add(new Product(R.drawable.m12, "41dt", "A compact diary.", "Compact Diary", "Stationery", 4.2f, false));

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
                    case "note books":
                        startActivity(new Intent(Notebook.this, Notebook.class));
                        break;
                    case "daily planner":
                        startActivity(new Intent(Notebook.this, Dailyplanner.class));
                        break;
                    default:
                        Toast.makeText(Notebook.this, "Aucun produit trouvé pour : " + query, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Notebook.this, product.getTitle() + " ajouté au panier", Toast.LENGTH_SHORT).show();
            });
            productItem.addView(imageView);
            productItem.addView(titleView);
            productItem.addView(priceView);
            productItem.addView(addToCartButton);
            productListLayout.addView(productItem);
        }
    }
}
