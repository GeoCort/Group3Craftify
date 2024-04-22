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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String title = crafts.get(position).getCraftTitle();
        String subTitle = crafts.get(position).getCraftDesc();
        String Id = crafts.get(position).getCraftID();
        holder.title.setText(title);
        holder.subTitle.setText(subTitle);
        holder.goToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(context, PostsActivity.class);
                intent.putExtra("craft", title);
                intent.putExtra("craftID", crafts.get(position).getCraftID());
                intent.putExtra("category", crafts.get(position).getCategory());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return crafts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView subTitle;
        Button goToBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.craftTitleTxt);
            subTitle =  itemView.findViewById(R.id.craftDescTxt);
            goToBtn = itemView.findViewById(R.id.btnCraftPrevToPosts);

        }
    }
}
