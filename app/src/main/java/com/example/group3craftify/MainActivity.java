package com.example.group3craftify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db;
    FirebaseAuth dbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("Categories");
        ArrayList<Category> categoryListDB = new ArrayList<>();
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
        myRef.setValue(categoryListDB);

        dbAuth = FirebaseAuth.getInstance();

    }

    protected void onStart()
    {
        super.onStart();
        FirebaseUser user = dbAuth.getCurrentUser();
    }

}