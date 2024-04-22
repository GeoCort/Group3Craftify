package com.example.group3craftify;

import android.content.Context;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPostsActivity extends AppCompatActivity {
Button addPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_posts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainAddPost), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String key = getIntent().getStringExtra("craftID");
        String craftName = getIntent().getStringExtra("key");
        String category = getIntent().getStringExtra("category");
        Toast.makeText(this,"The current Key is :" + category, Toast.LENGTH_SHORT).show();
        addPost = findViewById(R.id.addPostBtn);
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(craftName,key,v.getContext(), category);
            }
        });
    }
    public void setData(String craftRefKey,String id, Context context, String category){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(category).child(id).child("posts");
        ref.setValue("New Post lorem ipsum").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "This is being added into the db", Toast.LENGTH_SHORT).show();
            }
        });
    }
}