package com.example.myapplication.ui.product;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;

import com.example.myapplication.Product;
import com.example.myapplication.R;


import java.util.ArrayList;
import java.util.List;

public class Stickynotes extends AppCompatActivity {

    public SearchView searchView;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stickynotes);
        searchView = findViewById(R.id.searchView);

        LinearLayout productListLayout = findViewById(R.id.productListLayout);

        // Add cute animated message
        TextView infoText = new TextView(this);
        infoText.setText("✨ all our sticky notes are 2dt 💖");
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
        products.add(new Product(R.drawable.st1, "1dt", "Sticker A", "Cute Star Sticker", "Stickers", 4.5f, true));
        products.add(new Product(R.drawable.st2, "1dt", "Sticker B", "Heart Sticker", "Stickers", 4.0f, true));
        products.add(new Product(R.drawable.st3, "1dt", "Sticker C", "Animal Sticker", "Stickers", 4.2f, false));
        products.add(new Product(R.drawable.st4, "1dt", "Sticker D", "Floral Sticker", "Stickers", 4.5f, true));
        products.add(new Product(R.drawable.st5, "1dt", "Sticker E", "Rainbow Sticker", "Stickers", 4.2f, false));
        products.add(new Product(R.drawable.st6, "1dt", "Sticker F", "Fruit Sticker", "Stickers", 4.5f, true));
        products.add(new Product(R.drawable.st7, "1dt", "Sticker G", "Cloud Sticker", "Stickers", 4.0f, true));
        products.add(new Product(R.drawable.st8, "1dt", "Sticker H", "Coffee Sticker", "Stickers", 4.2f, false));
        products.add(new Product(R.drawable.st10, "1dt", "Sticker I", "Plant Sticker", "Stickers", 4.5f, true));


        // Search filter
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = null;
                switch (query.toLowerCase()) {
                    case "stickers":
                    case "sticker":
                        intent = new Intent(Stickynotes.this, StickersActivity.class);
                        break;
                    case "flower bouquet":
                        intent = new Intent(Stickynotes.this, Flower.class);
                        break;
                    case "mug":
                        intent = new Intent(Stickynotes.this, Mug.class);
                        break;
                    case "posters":
                    case "poster":
                        intent = new Intent(Stickynotes.this, Posters.class);
                        break;
                    case "note books":
                        intent = new Intent(Stickynotes.this, Notebook.class);
                        break;
                    case "daily planner":
                        intent = new Intent(Stickynotes.this, Dailyplanner.class);
                        break;
                    default:
                        Toast.makeText(Stickynotes.this, "Aucun produit trouvé pour : " + query, Toast.LENGTH_SHORT).show();
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

        // Display products dynamically
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

            productItem.addView(imageView);
            productListLayout.addView(productItem);
        }
    }
}
