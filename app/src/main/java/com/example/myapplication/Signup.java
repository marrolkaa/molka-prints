package com.example.myapplication;

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

    private EditText nomEditText, prenomEditText, emailEditText, adresseEditText,phoneEditText, passwordEditText;
    private Button registerButton;
    private ProgressBar loadingProgressBar;
    private TextView lienConnexion;

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

        // Lien vers la page de connexion
        lienConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signup.this, Login.class);
                startActivity(i);
            }
        });

        // Action lors du clic sur le bouton d'inscription
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nomEditText.getText().toString().trim();
                String prenom = prenomEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String location=adresseEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Signup.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                loadingProgressBar.setVisibility(View.VISIBLE);
                registerButton.setEnabled(false);

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
                String userId = databaseReference.push().getKey();

                User newUser = new User(nom, prenom, email, phone, password);

                if (userId != null) {
                    databaseReference.child(userId).setValue(newUser)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(getApplicationContext(), "Compte créé avec succès", Toast.LENGTH_SHORT).show();
                                loadingProgressBar.setVisibility(View.GONE);
                                registerButton.setEnabled(true);

                                SharedPreferences sharedPref = getSharedPreferences("UserInfo", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("name", nom);
                                editor.putString("email", email);
                                editor.putString("phone", phone);
                                editor.apply();

                                startActivity(new Intent(Signup.this, Ourproduct.class));
                                finish();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(getApplicationContext(), "Erreur : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                loadingProgressBar.setVisibility(View.GONE);
                                registerButton.setEnabled(true);
                            });
                } else {
                    Toast.makeText(getApplicationContext(), "Erreur lors de la génération de l'ID utilisateur", Toast.LENGTH_SHORT).show();
                    loadingProgressBar.setVisibility(View.GONE);
                    registerButton.setEnabled(true);
                }
            }
        });
    }
}
