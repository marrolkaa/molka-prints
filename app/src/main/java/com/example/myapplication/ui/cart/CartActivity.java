package com.example.myapplication.ui.cart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.CartManager;
import com.example.myapplication.Product;
import com.example.myapplication.R;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private LinearLayout cartLayout;
    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartLayout = findViewById(R.id.cartItemList);
        totalTextView = findViewById(R.id.totalTextView);

        displayCartItems();
    }

    private void displayCartItems() {
        cartLayout.removeAllViews(); // Clear old views
        List<Product> cartItems = CartManager.getCartItems();
        double total = 0.0;

        for (Product product : cartItems) {
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setPadding(16, 16, 16, 16);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(product.getImageResId());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 500));

            TextView nameView = new TextView(this);
            nameView.setText(product.getTitle());
            nameView.setTextSize(20);

            TextView priceView = new TextView(this);
            priceView.setText("Prix : " + product.getPrice());

            TextView descView = new TextView(this);
            descView.setText(product.getDescription());

            Button deleteButton = new Button(this);
            deleteButton.setText("Supprimer");
            deleteButton.setOnClickListener(v -> {
                CartManager.removeFromCart(product); // Remove from list
                displayCartItems(); // Refresh layout and total
            });

            itemLayout.addView(imageView);
            itemLayout.addView(nameView);
            itemLayout.addView(priceView);
            itemLayout.addView(descView);
            itemLayout.addView(deleteButton);

            cartLayout.addView(itemLayout);

            total += Double.parseDouble(product.getPrice().replace("dt", "").trim());
        }

        totalTextView.setText("Total: " + total + " dt");
    }
}
