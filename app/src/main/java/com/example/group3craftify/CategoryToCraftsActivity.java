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

public class CategoryToCraftsActivity extends AppCompatActivity {
    public TextView categoryTabName;
    private RecyclerView craftsRecyclerView;
    private CategoryToCraftsRecyclerAdapter adapter;
    ImageButton addCraftBtn;
    ArrayList<Craft> crafts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_to_crafts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.currentPost), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        if(intent != null){
            String name = intent.getStringExtra("keyCategory");
            String userID = intent.getStringExtra("userID");
            String userName = intent.getStringExtra("userName");
            Toast.makeText(this, "We have loaded in "+ userID , Toast.LENGTH_SHORT).show();
            categoryTabName= findViewById(R.id.categoryTitle);
            categoryTabName.setText(name);
            addCraftBtn = findViewById(R.id.btnToAddCraft);
            addCraftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CategoryToCraftsActivity.this, AddCraftActivity.class);
                    intent.putExtra("keyCategory",name);
                    intent.putExtra("userID",userID);
                    intent.putExtra("userName",userName);
                    startActivity(intent);
                }
            });
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            craftsRecyclerView = findViewById(R.id.recyclerCategoryToCrafts);
            adapter = new CategoryToCraftsRecyclerAdapter(this,userID, userName);
            adapter.setCrafts(crafts);
            craftsRecyclerView.setAdapter(adapter);
            craftsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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

                }
            });

        }else{
            // should only be on this page from categories
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}