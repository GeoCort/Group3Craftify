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

    /**
     * Instantiates a Recycler View adapter
     * @param catContext Context that will hold this list item
     * @param userID User Id of a given user to utilize data
     * @param userName Username of a current user
     */
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

    /**
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for the item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_line_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Retrieves an ArrayList of type Category
     * @return ArrayList of categories
     */
    public ArrayList<Category> getCategories() {
        return categories;
    }

    /**
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
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

    /**
     * returns size of categories list
     * @return categories size
     */
    @Override
    public int getItemCount() {
        return categories.size();
    }

    /**
     * Sets the adapters list to categories
     * @param categories
     */
    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    /**
     * Viewholder holds views of the UI elements
     */
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
