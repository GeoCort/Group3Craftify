package com.example.group3craftify;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class categoriesActivity extends AppCompatActivity {
    ArrayList<Category> categoryListDB = new ArrayList<>();
    RecyclerView categoryRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categories);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        categoryRecView = findViewById(R.id.categoryRecyclerView); // the xml container for the list
        CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(this); // adapter that tells recycler view how to hold data

        categoryListDB.add(new Category("Home Development"));
        categoryListDB.add(new Category("Cooking and Baking"));
        categoryListDB.add(new Category("Arts and Crafts"));
        categoryListDB.add(new Category("Health and Fitness"));
        categoryListDB.add(new Category("Fashion"));
        categoryListDB.add(new Category("History"));
        categoryListDB.add(new Category("Electronics"));
        categoryListDB.add(new Category("Technology and Innovation"));
        categoryListDB.add(new Category("Gaming"));
        categoryListDB.add(new Category("Sports"));
        categoryListDB.add(new Category("Travel and Adventure"));
        categoryListDB.add(new Category("Academics and Education"));
        categoryListDB.add(new Category("Politics and Current Events"));
        categoryRecView.setAdapter(adapter);
        categoryRecView.setLayoutManager(new LinearLayoutManager(this));


    }
}