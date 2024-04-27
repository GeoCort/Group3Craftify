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
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// Activity for adding posts to crafts
/**
 * Add a Post to DB Activity
 * @author George Cortes
 * @since 4/22
 */
public class AddPostsActivity extends AppCompatActivity {
    Button addPost; // Button to add post
    String prevDesc; // Previous description
    String prevTitle; // Previous title
    TextView title; // Text view for post title
    EditText desc; // Edit text for post description

    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Set the layout for this activity
        setContentView(R.layout.activity_add_posts);

        // Apply insets listener for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainAddPost), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve intent data
        String key = getIntent().getStringExtra("craftID");
        String craftName = getIntent().getStringExtra("key");
        String category = getIntent().getStringExtra("category");
        prevDesc = getIntent().getStringExtra("desc");
        prevTitle = getIntent().getStringExtra("craft");
        String userName = getIntent().getStringExtra("userName");
        String userID = getIntent().getStringExtra("userID");

        // Initialize text view, edit text, and button
        title = findViewById(R.id.addPostTitle);
        desc = findViewById(R.id.addPostDesc);
        addPost = findViewById(R.id.addPostBtn);

        // Set click listener for the button
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verify data before adding
                if(dataVerify()){
                    // Set post data
                    setData(craftName,key,v.getContext(), category,craftName, userName);
                    // Finish activity
                    finish();
                }
            }
        });
    }

    // Method to set post data in the database

    /**
     * Saves a Post object to the database
     * @param craftRefKey The name of the Post
     * @param id The Post ID
     * @param context The current context this is displayed in
     * @param category The category of the post
     * @param craftName The name of the parent Craft
     * @param createdBy The username of the creator
     */
    public void setData(String craftRefKey,String id, Context context, String category, String craftName,String createdBy){
        // Create post object
        Post post = new Post(title.getText().toString(),desc.getText().toString(),craftRefKey,craftName,createdBy);

        // Get database reference
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(category).child(id).child("posts").child(post.getId());

        // Set post data in the database
        ref.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "This is being added into the db", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Ensures that data is not too small
     * Title larger than 4 characters
     * Description larger than 10 characters
     * @return boolean if minimum specifications met
     */
    public boolean dataVerify(){
        // Check if title is too short
        if(title.getText().toString().length() <= 4){
            title.setError("Title too small");
            return false;
        }
        // Check if description is too short
        if(desc.getText().toString().length() <= 10){
            desc.setError("Too short of a description add more");
            return false;
        }
        // Data is valid
        return true;
    }
}
