package com.example.group3craftify;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Activity to display posts for a specific craft.
 * @author George Cortes, Juan Sanchez
 * @since 4/22
 */
public class PostsActivity extends AppCompatActivity {
    String craftRefKey;
    TextView postTitle;
    TextView description;
    String category;
    RecyclerView recyclerView;
    ArrayList<Post> posts = new ArrayList<>();
    Button addPosts;
    FirebaseDatabase db;
    PostsRecyclerAdapter adapter;
    User currentUser;

    /**
     * Called when the activity is starting.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down, this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}. Otherwise, it is null.
     */
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
        if (fromCrafts != null) {
            String craftId = fromCrafts.getStringExtra("craftID");
            String craftRefKey = fromCrafts.getStringExtra("craft");
            String desc = fromCrafts.getStringExtra("desc");
            String userId = fromCrafts.getStringExtra("userID");
            String userName = fromCrafts.getStringExtra("userName");
            category = fromCrafts.getStringExtra("category");
            adapter = new PostsRecyclerAdapter(this,userId);
            recyclerView = findViewById(R.id.postsRecyclerView);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setPosts(posts);
            addPosts = findViewById(R.id.addContentPost);
            postTitle = findViewById(R.id.postActivityTitle);
            postTitle.setText(craftRefKey);
            description = findViewById(R.id.postDescTitle);
            description.setText(desc);
            db = FirebaseDatabase.getInstance();
            addPosts.setOnClickListener(new View.OnClickListener() {
                /**
                 * Called when a view has been clicked.
                 * @param v The view that was clicked.
                 */
                @Override
                public void onClick(View v) {
                    addPostEvent(PostsActivity.this, craftRefKey, craftId, category, desc,userId,userName);
                }
            });
            db.getReference(category).child(craftId).child("posts").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    posts.clear();
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        Post p = snap.getValue(Post.class);
                        posts.add(new Post(p.getId(), p.getTitle(), p.getDescription(), p.getCraftName(), p.getCategory(), p.getCreatedBy()));
                    }
                    adapter.notifyDataSetChanged();
                }

                /**
                 * This method will be invoked if the request is cancelled.
                 * @param error A description of the error that occurred.
                 */
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(PostsActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }

            });
        }
    }


    /**
     * Method to initiate adding a new post.
     * @param context The context from which the method is called.
     * @param key The key associated with the craft.
     * @param craftId The ID of the craft.
     * @param category The category of the craft.
     * @param desc The description of the craft.
     * @param userID The ID of the user.
     * @param userName The name of the user.
     */
    public void addPostEvent(Context context, String key, String craftId, String category, String desc,String userID, String userName) {
        Intent intent = new Intent(context, AddPostsActivity.class);
        intent.putExtra("key", key);
        intent.putExtra("category", category);
        intent.putExtra("craftID", craftId);
        intent.putExtra("desc", desc);
        intent.putExtra("craft", craftRefKey);
        // keep track of who created this post and the user can access their created posts
        intent.putExtra("userID",userID);
        intent.putExtra("userName",userName);
        startActivity(intent);
    }
}
