package com.example.group3craftify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.UUID;

public class CategoryToCraftsActivity extends AppCompatActivity {
    public TextView categoryTabName;
    private RecyclerView craftsRecyclerView;
    private CategoryToCraftsRecyclerAdapter adapter;
    ArrayList<Craft> crafts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_to_crafts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        if(intent != null){
            String name = intent.getStringExtra("keyCategory");
            Toast.makeText(this, "We have loaded in "+ name , Toast.LENGTH_SHORT).show();
            categoryTabName= findViewById(R.id.categorytitle);
            categoryTabName.setText(name);
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            craftsRecyclerView = findViewById(R.id.recyclerCategoryToCrafts);
            adapter = new CategoryToCraftsRecyclerAdapter(this);
            adapter.setCrafts(crafts);
            craftsRecyclerView.setAdapter(adapter);
            craftsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            db.getReference(name).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                @Override
                public void onSuccess(DataSnapshot dataSnapshot) {
                    crafts.clear();
                    for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                        Craft c = snapshot.getValue(Craft.class);
                        crafts.add(new Craft(c.getCraftID(),c.getCreatedBy(),c.getCraftTitle(),c.getCraftDesc()));
                    }
                    System.out.println("current crafts are:" + crafts.size());
                    adapter.setCrafts(crafts);
                    adapter.notifyDataSetChanged();
                }
            });

        }else{
            // should only be on this page from categories
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}