package com.example.group3craftify;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
String prevDesc;
String prevTitle;
TextView title;
EditText desc;
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
        prevDesc = getIntent().getStringExtra("desc");
        prevTitle = getIntent().getStringExtra("craft");
        title = findViewById(R.id.addPostTitle);
        desc = findViewById(R.id.addPostDesc);
        addPost = findViewById(R.id.addPostBtn);
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dataVerify()){
                    //setData(craftName,key,v.getContext(), category,craftName);
                    Intent intent = new Intent(AddPostsActivity.this, PostsActivity.class);
                    finish();
//                  LOOK HERE
                    intent.putExtra("craftID",key);
                    intent.putExtra("craft",craftName);
                    intent.putExtra("category",category);
                    intent.putExtra("desc",prevDesc);
                    startActivity(intent);
                }
            }
        });
    }
    public void setData(String craftRefKey,String id, Context context, String category, String craftName){
        // get user info somehow
        Post post = new Post(title.getText().toString(),desc.getText().toString(),craftRefKey,craftName,"admin");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(category).child(id).child("posts").child(post.getId());
        ref.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "This is being added into the db", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean dataVerify(){
        if(title.getText().toString().length() <= 4){
            title.setError("Title too small");
            return false;
        }
        if(desc.getText().toString().length() <= 10){
            desc.setError("Too short of a description add more");
            return false;
        }
        return true;
    }
}