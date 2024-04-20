package com.example.group3craftify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    Button allCategoriesButton;
    Button favoritesButton;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseAuth dbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        allCategoriesButton = findViewById(R.id.home_AllCategories);
        favoritesButton = findViewById(R.id.home_Favorites);

        dbAuth = FirebaseAuth.getInstance();

        allCategoriesButton.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, CategoriesActivity.class));
        });

        favoritesButton.setOnClickListener(view -> {
        });
    }
}