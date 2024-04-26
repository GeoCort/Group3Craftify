package com.example.group3craftify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Adapter for RecyclerView to display categories
public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> {
    private ArrayList<Category> categories = new ArrayList<>(); // List of categories
    private Context catContext; // Context of the adapter
    String userID; // User ID
    String userName; // User name

    // Constructor
    public CategoryRecyclerAdapter(Context catContext, String userID, String userName) {
        this.catContext = catContext;
        // If userID is null, set it as "admin"
        if (userID == null) {
            this.userID = "admin";
        } else {
            this.userID = userID;
        }
        this.userName = userName;
    }

    // Create ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for the item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_line_item, parent, false);
        return new ViewHolder(view);
    }

    // Get categories
    public ArrayList<Category> getCategories() {
        return categories;
    }

    // Bind data to ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Set category name
        holder.lineItem.setText(categories.get(position).getName());
        // Set click listener for category item
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get category name and start CategoryToCraftsActivity
                String categoryName = categories.get(position).getName();
                Intent intent = new Intent(catContext, CategoryToCraftsActivity.class);
                intent.putExtra("keyCategory", categoryName);
                intent.putExtra("userID", userID);
                intent.putExtra("userName", userName);
                catContext.startActivity(intent);
            }
        });
    }

    // Get item count
    @Override
    public int getItemCount() {
        return categories.size();
    }

    // Set categories
    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    // ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView lineItem; // TextView for category name
        private RelativeLayout parent; // Parent layout

        // Constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lineItem = itemView.findViewById(R.id.category_line_item); // Initialize lineItem
            parent = itemView.findViewById(R.id.line_item_relative_layout); // Initialize parent
        }
    }
}
