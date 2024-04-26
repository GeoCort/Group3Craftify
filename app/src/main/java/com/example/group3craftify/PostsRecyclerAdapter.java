package com.example.group3craftify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @author
 * @since
 */
public class PostsRecyclerAdapter extends RecyclerView.Adapter<PostsRecyclerAdapter.ViewHolder>{
    ArrayList<Post> posts = new ArrayList<>();
    Context context;
    String userID;
    public PostsRecyclerAdapter(Context context, String userID) {
        this.context = context;
        this.userID = userID;
    }

    /**
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_line_item,parent,false);
        return new ViewHolder(view);
    }

    /**
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(posts.get(position).getTitle());
        holder.desc.setText(posts.get(position).getDescription());
        holder.categoryName.setText(posts.get(position).getCraftName());
        holder.clickablePost.setOnClickListener(new View.OnClickListener() {
            /**
             *
              * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CurrentPostActivity.class);
                intent.putExtra("craftName", posts.get(position).getCraftName());
                intent.putExtra("title", posts.get(position).getTitle());
                intent.putExtra("desc", posts.get(position).getDescription());
                intent.putExtra("postID", posts.get(position).getId());
                intent.putExtra("userID",userID);
                intent.putExtra("creator", posts.get(position).getCreatedBy());
                context.startActivity(intent);
            }
        });
    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return posts.size();
    }

    /**
     *
     * @param posts
     */
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    /**
     *
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView desc;
        TextView categoryName;
        LinearLayout clickablePost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.postsTitle);
            desc = itemView.findViewById(R.id.postsDesc);
            categoryName = itemView.findViewById(R.id.postsCategory);
            clickablePost = itemView.findViewById(R.id.clickablePost);

        }
    }
}
