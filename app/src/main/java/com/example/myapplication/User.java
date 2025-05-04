package com.example.myapplication;

public class User {
    private String nom;
    private String prenom;
    private String email;
    private String phone;
    private String password;

    public User(String nom, String email, String phone, String password) {
        // Constructeur vide requis pour Firebase
    }

    public User(String nom, String prenom, String email, String phone, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    // Getters et setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
