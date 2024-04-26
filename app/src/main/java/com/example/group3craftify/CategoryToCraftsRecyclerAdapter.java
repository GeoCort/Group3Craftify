package com.example.group3craftify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Adapter for RecyclerView to display crafts under a specific category
public class CategoryToCraftsRecyclerAdapter extends RecyclerView.Adapter<CategoryToCraftsRecyclerAdapter.ViewHolder> {
    ArrayList<Craft> crafts = new ArrayList<>(); // List of crafts
    String userName; // User name
    String userID; // User ID
    Context context; // Context of the adapter

    // Constructor
    public CategoryToCraftsRecyclerAdapter(Context context,String userID, String userName) {
        this.context = context;
        this.userID = userID;
        this.userName = userName;
    }

    // Set crafts
    public void setCrafts(ArrayList<Craft> crafts) {
        this.crafts = crafts;
    }

    // Create ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for the item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.craft_previews_line_item, parent,false);
        return new ViewHolder(view);
    }

    // Bind data to ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Get craft details
        String title = crafts.get(position).getCraftTitle();
        String subTitle = crafts.get(position).getCraftDesc();
        String Id = crafts.get(position).getCraftID();

        // Set title and subtitle
        holder.title.setText(title);
        holder.subTitle.setText(subTitle);

        // Set click listener for goToBtn
        holder.goToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start PostsActivity
                Intent intent = new Intent(context, PostsActivity.class);
                intent.putExtra("craft", title);
                intent.putExtra("craftID", crafts.get(position).getCraftID());
                intent.putExtra("category", crafts.get(position).getCategory());
                intent.putExtra("desc", crafts.get(position).getCraftDesc());
                intent.putExtra("userID",userID);
                intent.putExtra("userName", userName);
                context.startActivity(intent);
            }
        });
    }

    // Get item count
    @Override
    public int getItemCount() {
        return crafts.size();
    }

    // Get crafts
    public ArrayList<Craft> getCrafts() {
        return crafts;
    }

    // ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title; // TextView for craft title
        private TextView subTitle; // TextView for craft description
        Button goToBtn; // Button to navigate to craft posts

        // Constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views
            title = itemView.findViewById(R.id.craftTitleTxt);
            subTitle =  itemView.findViewById(R.id.craftDescTxt);
            goToBtn = itemView.findViewById(R.id.btnCraftPrevToPosts);
        }
    }
}
