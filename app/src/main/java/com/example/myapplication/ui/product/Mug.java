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
import com.example.myapplication.Produit;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Mug extends AppCompatActivity {

    public SearchView searchView;
    private View addButton;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mug2);
        searchView = findViewById(R.id.searchView);

        LinearLayout productListLayout = findViewById(R.id.productListLayout);

        // Cute animated message
        TextView infoText = new TextView(this);
        infoText.setText("✨ all these mugs are 20dt 💖");
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
        List<Produit> products = new ArrayList<>();
        products.add(new Produit("Mug A", "", "20dt", R.drawable.mu1, "Mugs", 4.0f, true));
        products.add(new Produit("Mug B", "Heart Sticker", "20dt", R.drawable.mu2, "Mugs", 4.0f, true));
        products.add(new Produit("Mug C", "Animal Sticker", "20dt", R.drawable.mu4, "Mugs", 4.2f, false));
        products.add(new Produit("Mug D", "Floral Sticker", "20dt", R.drawable.mu5, "Mugs", 4.5f, true));
        products.add(new Produit("Mug E", "Rainbow Sticker", "20dt", R.drawable.mu6, "Mugs", 4.2f, false));
        products.add(new Produit("Mug F", "Fruit Sticker", "20dt", R.drawable.mu7, "Mugs", 4.5f, true));
        products.add(new Produit("Mug G", "Cloud Sticker", "20dt", R.drawable.mu8, "Mugs", 4.0f, true));
        products.add(new Produit("Mug H", "Coffee Sticker", "20dt", R.drawable.mu9, "Mugs", 4.2f, false));
        products.add(new Produit("Mug I", "Coffee Sticker", "20dt", R.drawable.mu10, "Mugs", 4.2f, false));


        // Search filter
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = null;
                switch (query.toLowerCase()) {
                    case "stickers":
                    case "sticker":
                        intent = new Intent(Mug.this, StickersActivity.class);
                        break;
                    case "flower bouquet":
                        intent = new Intent(Mug.this, Flower.class);
                        break;
                    case "mug":
                        intent = new Intent(Mug.this, Mug.class);
                        break;
                    case "posters":
                    case "note books":
                        intent = new Intent(Mug.this, Notebook.class);
                        break;
                    case "daily planner":
                        intent = new Intent(Mug.this, Dailyplanner.class);
                        break;
                    default:
                        Toast.makeText(Mug.this, "Aucun produit trouvé pour : " + query, Toast.LENGTH_SHORT).show();
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

        // Display products with "Ajouter au panier" button
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
            addToCartButton.setOnClickListener(v -> {
                CartManager.addToCart(product);
                Toast.makeText(Mug.this, product.getTitle() + " ajouté au panier", Toast.LENGTH_SHORT).show();
            });
            productItem.addView(imageView);
            productItem.addView(addToCartButton);
            productListLayout.addView(productItem);
        }
    }
}
