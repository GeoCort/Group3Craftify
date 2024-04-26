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

// Activity to display details of a current post
public class CurrentPostActivity extends AppCompatActivity {
    TextView userNameView; // TextView to display username
    TextView titleView; // TextView to display title
    TextView descriptionView; // TextView to display description
    EditText addComment; // EditText to add a comment
    User user; // User object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Set the layout for this activity
        setContentView(R.layout.activity_current_post);

        // Apply insets listener for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.currentPost), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Receive intent
        Intent intent = getIntent();
        if(intent != null) {
            String userName = intent.getStringExtra("creator"); // Get username from intent
            String title = intent.getStringExtra("title"); // Get title from intent
            String description = intent.getStringExtra("desc"); // Get description from intent
            String currentPostID = intent.getStringExtra("postID"); // Get post ID from intent
            String userID = intent.getStringExtra("userID"); // Get user ID from intent

            // Initialize views
            userNameView = findViewById(R.id.userNamePost);
            titleView = findViewById(R.id.currentPostTitle);
            descriptionView = findViewById(R.id.currentPostDesc);

            // Set username, title, and description
            userNameView.setText(userName);
            titleView.setText(title);
            descriptionView.setText(description);

            // Load user details
            loadUser(userID);
        } else {
            // Finish the activity if intent is null
            finish();
        }
    }

    // Method to load user details
    public void loadUser(String userID) {
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
