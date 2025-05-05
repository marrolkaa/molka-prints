package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OurProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ourproduct);

        ImageView cartImageView = findViewById(R.id.cartImageView);

        if (cartImageView != null) {
            cartImageView.setOnClickListener(v -> {
                Intent intent = new Intent(OurProductActivity.this, CartManager.class);
                startActivity(intent);
                Toast.makeText(this, " CartManager lancé", Toast.LENGTH_SHORT).show();
            });
        } else {
            Toast.makeText(this, " cartImageView introuvable", Toast.LENGTH_LONG).show();
        }
    }
}
