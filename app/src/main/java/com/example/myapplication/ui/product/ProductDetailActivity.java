package com.example.myapplication.ui.product;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Product;
import com.example.myapplication.R;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_details);
        // Get the product object passed from the previous activity
        Product product = (Product) getIntent().getSerializableExtra("product");

        // Find views to display the product details
        ImageView productImageView = findViewById(R.id.productImage);
        TextView priceTextView = findViewById(R.id.productPrice);
        TextView descriptionTextView = findViewById(R.id.productDescription);
        TextView nameTextView = findViewById(R.id.productName);
        TextView categoryTextView = findViewById(R.id.productCategory);
        TextView ratingTextView = findViewById(R.id.productRating);
        TextView availabilityTextView = findViewById(R.id.productAvailability);

        // Set the product details to the views
        if (product != null) {
            productImageView.setImageResource(product.getImageResId());
            priceTextView.setText(product.getPrice());
            descriptionTextView.setText(product.getDescription());
            nameTextView.setText(product.getTitle());
            categoryTextView.setText(product.getCategory());
            ratingTextView.setText(String.format("Rating: %.1f", product.getRating()));
            availabilityTextView.setText(product.isAvailable() ? "Available" : "Out of Stock");
        }



    }
}