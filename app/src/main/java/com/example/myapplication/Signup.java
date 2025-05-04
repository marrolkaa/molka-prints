package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ui.login.Login;
import com.example.myapplication.ui.product.Ourproduct;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    private EditText nomEditText, emailEditText, adresseEditText, phoneEditText, passwordEditText;
    private Button registerButton;
    private ProgressBar loadingProgressBar;
    private TextView lienConnexion;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialisation des vues
        nomEditText = findViewById(R.id.nameInput);
        emailEditText = findViewById(R.id.emailInput);
        phoneEditText = findViewById(R.id.phoneInput);
        adresseEditText = findViewById(R.id.addressInput);
        passwordEditText = findViewById(R.id.passwordInput);
        registerButton = findViewById(R.id.btnSignup);
        lienConnexion = findViewById(R.id.lienConnexion);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);

        // Aller à la page de connexion
        lienConnexion.setOnClickListener(view -> {
            Intent i = new Intent(Signup.this, Login.class);
            startActivity(i);
        });


        // Action lors du clic sur le bouton S'inscrire
        registerButton.setOnClickListener(v -> {
            Intent i = new Intent(Signup.this, Ourproduct.class);
            startActivity(i);
        });
    }
}
