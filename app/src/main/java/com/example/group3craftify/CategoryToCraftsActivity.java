package com.example.group3craftify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

// Activity for displaying crafts under a specific category
public class CategoryToCraftsActivity extends AppCompatActivity {
    public TextView categoryTabName; // TextView for category name
    private RecyclerView craftsRecyclerView; // RecyclerView for displaying crafts
    private CategoryToCraftsRecyclerAdapter adapter; // Adapter for the RecyclerView
    ImageButton addCraftBtn; // Button for adding a craft
    ArrayList<Craft> crafts = new ArrayList<>(); // List to store crafts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Set the layout for this activity
        setContentView(R.layout.activity_category_to_crafts);

        // Apply insets listener for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.currentPost), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve intent data
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("keyCategory");
            String userID = intent.getStringExtra("userID");
            String userName = intent.getStringExtra("userName");

            // Display a toast message
            Toast.makeText(this, "We have loaded in " + userID, Toast.LENGTH_SHORT).show();

            // Set category name in TextView
            categoryTabName = findViewById(R.id.categoryTitle);
            categoryTabName.setText(name);

            // Initialize and set click listener for the addCraftBtn
            addCraftBtn = findViewById(R.id.btnToAddCraft);
            addCraftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start AddCraftActivity
                    Intent intent = new Intent(CategoryToCraftsActivity.this, AddCraftActivity.class);
                    intent.putExtra("keyCategory", name);
                    intent.putExtra("userID", userID);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                }
            });

            // Initialize Firebase database instance
            FirebaseDatabase db = FirebaseDatabase.getInstance();

            // Initialize RecyclerView and its adapter
            craftsRecyclerView = findViewById(R.id.recyclerCategoryToCrafts);
            adapter = new CategoryToCraftsRecyclerAdapter(this, userID, userName);
            adapter.setCrafts(crafts);
            craftsRecyclerView.setAdapter(adapter);
            craftsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Retrieve crafts from the database
            db.getReference(name).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    crafts.clear();
                    for(DataSnapshot snap: snapshot.getChildren() ){
                        Craft c = snap.getValue(Craft.class);
                        crafts.add(new Craft(c.getCraftID(),c.getCreatedBy(),c.getCraftTitle(),c.getCraftDesc(),c.getCategory()));
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle database error
                }
            });
        } else {
            // Redirect to MainActivity if intent is null (should only happen when coming from categories)
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
