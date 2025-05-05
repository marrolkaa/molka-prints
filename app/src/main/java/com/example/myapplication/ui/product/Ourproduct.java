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
import com.example.myapplication.ui.cart.CartActivity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.widget.Button;


public class Ourproduct extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ourproduct);

        SearchView searchView = findViewById(R.id.searchView);
        LinearLayout productListLayout = findViewById(R.id.productListLayout);

        List<Produit> products = new ArrayList<>();
        products.add(new Produit("A beautiful custom notebook", "A beautiful custom notebook.", "20dt", R.drawable.m1, "Stationery", 4.5f, true));
        products.add(new Produit("A sticky notes", "A sticky notes.", "50dt", R.drawable.st2, "Stationery", 4.0f, true));
        products.add(new Produit("A study mug", "A study mug.", "23dt", R.drawable.mu3, "Stationery", 4.2f, true));
        products.add(new Produit("A Poster", "A Poster.", "30dt", R.drawable.p4, "Stationery", 4.5f, true));
        products.add(new Produit("A nails artist planner", "A nails artist planner.", "23dt", R.drawable.m6, "Stationery", 4.2f, true));
        products.add(new Produit("A heart sticker", "A heart sticker.", "24dt", R.drawable.s7, "Stationery", 4.5f, true));
        products.add(new Produit("A wedding planner", "A wedding planner.", "28.5dt", R.drawable.m8, "Stationery", 4.0f, true));
        products.add(new Produit("A small note book", "A small note book.", "34dt", R.drawable.m9, "Stationery", 4.2f, true));
        products.add(new Produit("A planner", "A planner.", "10dt", R.drawable.m10, "Stationery", 4.5f, true));
        products.add(new Produit("A planner", "A planner.", "40dt", R.drawable.m11, "Stationery", 4.0f, true));
        products.add(new Produit("A diary", "A diary.", "41dt", R.drawable.m12, "Stationery", 4.2f, true));


        // 🔍 Recherche

                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchView.clearFocus(); // Évite les soumissions multiples
                    String lowerQuery = query.toLowerCase();

                    switch (lowerQuery) {
                        case "stickers":
                        case "sticker":
                            startActivity(new Intent(Ourproduct.this, StickersActivity.class));
                            break;
                        case "flower bouquet":
                            startActivity(new Intent(Ourproduct.this, Flower.class));
                            break;
                        case "mug":
                                startActivity(new Intent(Ourproduct.this, Mug.class));
                            break;
                        case "posters":
                        case "poster":
                            startActivity(new Intent(Ourproduct.this, Posters.class));
                            break;
                        case "note books":
                            startActivity(new Intent(Ourproduct.this, Notebook.class));
                            break;
                        default:
                            Toast.makeText(Ourproduct.this, "Aucun produit trouvé pour : " + query, Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(Ourproduct.this, CartActivity.class);
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
                Intent intent = new Intent(Ourproduct.this, ProductDetailActivity.class);
                intent.putExtra("product", product); // Serializable
                startActivity(intent);
            });

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
                Toast.makeText(Ourproduct.this, product.getTitle() + " ajouté au panier", Toast.LENGTH_SHORT).show();
            });

            productItem.addView(imageView);
            productItem.addView(priceView);
            productItem.addView(addToCartButton);
            productListLayout.addView(productItem);
        }
    }
}
