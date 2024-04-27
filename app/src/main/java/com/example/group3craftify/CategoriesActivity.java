package com.example.group3craftify;

import android.os.Bundle;
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

// Activity for displaying categories
/**
 * Activity to display Categories
 * @author George Cortes
 * @since 4/22
 */
public class CategoriesActivity extends AppCompatActivity {
    ArrayList<Category> categoryListDB = new ArrayList<>(); // List to store categories from the database
    DatabaseReference categoryRef; // Reference to the 'Categories' node in the database
    ImageButton addCraft; // Button for adding a craft
    FirebaseDatabase db; // Firebase database instance
    RecyclerView categoryRecView; // RecyclerView for displaying categories
    CategoryRecyclerAdapter adapter; // Adapter for the RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Set the layout for this activity
        setContentView(R.layout.activity_categories);

        // Apply insets listener for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.currentPost), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase database and reference to 'Categories' node
        db = FirebaseDatabase.getInstance();
        categoryRef = db.getReference("Categories");

        // Retrieve categories from the database
        categoryRef.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                // Clear the list before adding new categories
                categoryListDB.clear();
                // Iterate through the data snapshot to retrieve categories
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Category c = ds.getValue(Category.class);
                    categoryListDB.add(new Category(c.getName()));
                }
                // Notify the adapter of data changes
                adapter.notifyDataSetChanged();
            }
        });

        // Retrieve user ID and user name from intent
        String userID = getIntent().getStringExtra("userID");
        String userName = getIntent().getStringExtra("userName");

        // Initialize RecyclerView and its adapter
        categoryRecView = findViewById(R.id.categoryRecyclerView);
        adapter = new CategoryRecyclerAdapter(this, userID, userName);
        adapter.setCategories(categoryListDB);
        categoryRecView.setAdapter(adapter);
        categoryRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Method to populate category list from the database
    public void populate(DatabaseReference ref, ArrayList<Category> cat) {
        ref.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Category c = ds.getValue(Category.class);
                    cat.add(new Category(c.getName()));
                }
            }
        });
    }
}
