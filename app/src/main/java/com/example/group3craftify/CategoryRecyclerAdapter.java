package com.example.group3craftify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder>{
    private ArrayList<Category> categories = new ArrayList<>();
    private Context catContext;
    String userID;
    String userName;

    public CategoryRecyclerAdapter(Context catContext, String userID, String userName) {
        this.catContext = catContext;
        if(userID == null){
            this.userID = "admin";
        }else{
            this.userID = userID;
        }
        this.userName = userName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_line_item,parent,false);
        return new CategoryRecyclerAdapter.ViewHolder(view);
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.lineItem.setText(categories.get(position).getName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String categoryName = categories.get(position).getName();
                Intent intent = new Intent(catContext, CategoryToCraftsActivity.class);
                intent.putExtra("keyCategory",categoryName);
                intent.putExtra("userID",userID);
                intent.putExtra("userName",userName);
                catContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView lineItem;
        private RelativeLayout parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lineItem = itemView.findViewById(R.id.category_line_item);
            parent = itemView.findViewById(R.id.line_item_relative_layout);
        }
    }
}
