package com.example.group3craftify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {
    ArrayList<Category> categoryListDB = new ArrayList<>();
    DatabaseReference categoryRef;
    ImageButton addCraft;
    FirebaseDatabase db;
    RecyclerView categoryRecView;
    CategoryRecyclerAdapter adapter;
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
        db = FirebaseDatabase.getInstance(); // initialize database from json
        categoryRef = db.getReference("Categories");
        categoryRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                categoryListDB.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Category c = ds.getValue(Category.class);
                    categoryListDB.add(new Category(c.getName()) );
                }
                adapter.notifyDataSetChanged();
            }
        });

        categoryRecView = findViewById(R.id.categoryRecyclerView); // the xml container for the list
        adapter = new CategoryRecyclerAdapter(this); // adapter that tells recycler view how to hold data
        adapter.setCategories(categoryListDB);
        categoryRecView.setAdapter(adapter);
        categoryRecView.setLayoutManager(new LinearLayoutManager(this));


    }
    public void populate(DatabaseReference ref, ArrayList<Category>cat){;
        categoryRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
               for(DataSnapshot ds : dataSnapshot.getChildren()){
                   Category c = ds.getValue(Category.class);
                   cat.add(new Category(c.getName()) );
               }
            }
        });
    }
}