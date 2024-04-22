package com.example.group3craftify;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostsActivity extends AppCompatActivity {
String craftRefKey;
String category;
Button addPosts;
FirebaseDatabase db;
DatabaseReference PostRef;
FirebaseAuth user;
User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_posts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.postMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent fromCrafts = getIntent();
        if(fromCrafts != null){
            String craftId = fromCrafts.getStringExtra("craftID");
            System.out.println("This is the current id" + craftId);
            String craftRefKey = fromCrafts.getStringExtra("craft");
            category = fromCrafts.getStringExtra("category");
            addPosts = findViewById(R.id.addContentPost);
            addPosts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addPostEvent(PostsActivity.this,craftRefKey,craftId,category);

                }
            });

        }

    }
    public void addPostEvent(Context context, String key, String craftId,String category){
        Intent intent = new Intent(context, AddPostsActivity.class);
        intent.putExtra("key",key);
        intent.putExtra("craftID",craftId);
        intent.putExtra("category",category);
        startActivity(intent);
    }

}