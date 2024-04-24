package com.example.group3craftify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class CurrentPostActivity extends AppCompatActivity {
    TextView userNameView;
    TextView titleView;
    TextView descriptionView;
    EditText addComment;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_current_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.currentPost), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // receive intent
        Intent intent = getIntent();
        if(intent != null){
            String userName = intent.getStringExtra("creator");
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("desc");
            String currentPostID = intent.getStringExtra("postID");
            String userID = intent.getStringExtra("userID");
            userNameView = findViewById(R.id.userNamePost);
            titleView = findViewById(R.id.currentPostTitle);
            descriptionView = findViewById(R.id.currentPostDesc);
            userNameView.setText(userName);
            titleView.setText(title);
            descriptionView.setText(description);
        // add a comment ui logic
            addComment = findViewById(R.id.addComment);
            if(addComment.isFocused()){
                findViewById(R.id.addCommentBtn).setVisibility(View.VISIBLE);
            }

        }else{
            finish();
        }
    }
    public void loadUser(String userID){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference("Users").child(userID).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                user.setUserID(user.getUsername());
                user.setEmail(u.getEmail());
                user.setUserID(userID);
            }
        });
    }
}