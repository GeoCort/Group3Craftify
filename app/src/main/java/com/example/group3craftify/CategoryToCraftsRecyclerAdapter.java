package com.example.group3craftify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryToCraftsRecyclerAdapter extends RecyclerView.Adapter<CategoryToCraftsRecyclerAdapter.ViewHolder> {
    ArrayList<Craft> crafts = new ArrayList<>();
    Context context;

    public CategoryToCraftsRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setCrafts(ArrayList<Craft> crafts) {
        this.crafts = crafts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.craft_previews_line_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = crafts.get(position).getCraftTitle();
        String subTitle = crafts.get(position).getCraftDesc();
        holder.title.setText(title);
        holder.subTitle.setText(subTitle);
    }

    @Override
    public int getItemCount() {
        return crafts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView subTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.craftTitleTxt);
            subTitle =  itemView.findViewById(R.id.craftDescTxt);
        }
    }
}
