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
/**
 * Adapter class for a Recycler View
 * @author George Cortes
 */
// Adapter for RecyclerView to display crafts under a specific category
public class CategoryToCraftsRecyclerAdapter extends RecyclerView.Adapter<CategoryToCraftsRecyclerAdapter.ViewHolder> {
    ArrayList<Craft> crafts = new ArrayList<>(); // List of crafts
    String userName; // User name
    String userID; // User ID
    Context context; // Context of the adapter

    /**
     *  * Instantiates an adapter for the CategoryToCraftsRecycler adapter
     * @param context context where line items will be displayed
     * @param userID The current user's ID
     * @param userName The current user's username
     */
    public CategoryToCraftsRecyclerAdapter(Context context,String userID, String userName) {
        this.context = context;
        this.userID = userID;
        this.userName = userName;
    }

    // Set crafts

    /**
     * Sets crafts to crafts
     * @param crafts Arraylist of type Craft
     */
    public void setCrafts(ArrayList<Craft> crafts) {
        this.crafts = crafts;
    }

    // Create ViewHolder

    /**
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return ViewHolder class object to manipulate in OnBindViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for the item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.craft_previews_line_item, parent,false);
        return new ViewHolder(view);
    }

    // Bind data to ViewHolder

    /**
     * Tracks current focused location and sets text to line items
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
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

    /**
     * Returns crafts Size
     * Is used to determine if adapter should populate
     * @return craftsSize
     */
    @Override
    public int getItemCount() {
        return crafts.size();
    }

    /**
     * gets the craft list
     * @return crafts an arraylist of type Craft
     */
    public ArrayList<Craft> getCrafts() {
        return crafts;
    }

    /**
     * Holds Views for given line items
     * instantiates line items from a given layout
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title; // TextView for craft title
        private TextView subTitle; // TextView for craft description
        Button goToBtn; // Button to navigate to craft posts
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views
            title = itemView.findViewById(R.id.craftTitleTxt);
            subTitle =  itemView.findViewById(R.id.craftDescTxt);
            goToBtn = itemView.findViewById(R.id.btnCraftPrevToPosts);
        }
    }
}
