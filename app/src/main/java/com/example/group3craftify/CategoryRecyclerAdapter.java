package com.example.group3craftify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder>{
    private ArrayList<Category> categories = new ArrayList<>();
    private Context catContext;

    public CategoryRecyclerAdapter(Context catContext) {
        this.catContext = catContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_line_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lineItem.setText(categories.get(position).getName());
        holder.lineItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(catContext, "Category : " + categories.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categories = categories;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView lineItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lineItem = itemView.findViewById(R.id.category_line_item);


        }
    }
}
