package com.example.myapplication.ui.cart;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CartManager;
import com.example.myapplication.Produit;
import com.example.myapplication.R;

import java.util.List;

public class CartActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private TextView totalTextView, panierVideText;
    private CartAdapter adapter;

    private LinearLayout cartLayout;
    private TextView totalTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerViewCart);
        totalTextView = findViewById(R.id.cartTotal);
        panierVideText = findViewById(R.id.panierVideText);

        List<Produit> cartItems = CartManager.getCartItems();

        panierVideText.setVisibility(cartItems.isEmpty() ? View.VISIBLE : View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(cartItems);
        recyclerView.setAdapter(adapter);

        updateTotal();
    }

    private void updateTotal() {
        cartLayout = findViewById(R.id.cartItemList);
        totalTextView = findViewById(R.id.totalTextView);

        displayCartItems();
    }

    private void displayCartItems() {
        cartLayout.removeAllViews(); // Clear old views
        List<Product> cartItems = CartManager.getCartItems();
        double total = 0.0;
        for (Produit p : adapter.cartItems) {
            try {
                double prixUnitaire = Double.parseDouble(p.getPrice().replace("dt", "").trim());
                total += prixUnitaire * p.getQuantity();
            } catch (NumberFormatException ignored) {}
        }
        totalTextView.setText("Total: " + String.format("%.2f", total) + " dt");
    }

    private class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

        private final List<Produit> cartItems;

        public CartAdapter(List<Produit> cartItems) {
            this.cartItems = cartItems;
        }

        class CartViewHolder extends RecyclerView.ViewHolder {
            ImageView itemImage;
            TextView itemNom, itemDesc, itemPrix, quantiteText;
            ImageButton btnDelete;
            Button btnMoins, btnPlus;

            public CartViewHolder(View itemView) {
                super(itemView);
                itemImage = itemView.findViewById(R.id.itemImage);
                itemNom = itemView.findViewById(R.id.itemNom);
                itemDesc = itemView.findViewById(R.id.itemDesc);
                itemPrix = itemView.findViewById(R.id.itemPrix);
                quantiteText = itemView.findViewById(R.id.quantiteText);
                btnDelete = itemView.findViewById(R.id.btnDelete);
                btnMoins = itemView.findViewById(R.id.btnMoins);
                btnPlus = itemView.findViewById(R.id.btnPlus);
            }
        }

        @Override
        public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cart_manager, parent, false);
            return new CartViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CartViewHolder holder, int position) {
            Produit product = cartItems.get(position);

            // Affichage des informations produit dans les vues
            holder.itemImage.setImageResource(product.getImageResId());
            holder.itemNom.setText(product.getTitle());
            holder.itemDesc.setText(product.getDescription());
            holder.quantiteText.setText(String.valueOf(product.getQuantity()));

            // Calcul du prix total en fonction de la quantité
            double prixUnitaire = Double.parseDouble(product.getPrice().replace("dt", "").trim());
            double prixTotal = prixUnitaire * product.getQuantity();
            holder.itemPrix.setText(String.format("%.2f dt", prixTotal));

            // Suppression du produit du panier
            holder.btnDelete.setOnClickListener(v -> {
                cartItems.remove(position);  // Supprime l'article du panier
                notifyItemRemoved(position);  // Notifie l'adaptateur de la suppression
                notifyItemRangeChanged(position, cartItems.size());  // Met à jour les éléments restants
                CartManager.setCartItems(cartItems);  // Mets à jour le panier dans CartManager
                panierVideText.setVisibility(cartItems.isEmpty() ? View.VISIBLE : View.GONE);  // Affiche un message si le panier est vide
                updateTotal();  // Mets à jour le total
            });

            // Gestion du bouton "Moins" pour diminuer la quantité
            holder.btnMoins.setOnClickListener(v -> {
                if (product.getQuantity() > 1) {
                    product.setQuantity(product.getQuantity() - 1);  // Réduit la quantité du produit
                    notifyItemChanged(position);  // Notifie l'adaptateur pour mettre à jour l'élément
                    updateTotal();  // Mets à jour le total
                }
            });

            // Gestion du bouton "Plus" pour augmenter la quantité
            holder.btnPlus.setOnClickListener(v -> {
                product.setQuantity(product.getQuantity() + 1);  // Augmente la quantité du produit
                notifyItemChanged(position);  // Notifie l'adaptateur pour mettre à jour l'élément
                updateTotal();  // Mets à jour le total
            });
        }

        @Override
        public int getItemCount() {
            return cartItems.size();
        }
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

