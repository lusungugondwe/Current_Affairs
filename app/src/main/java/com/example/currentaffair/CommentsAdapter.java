package com.example.currentaffair;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    Context context;
    ArrayList<NewsCommentsModel> nModels;

    public CommentsAdapter(Context context, ArrayList<NewsCommentsModel> nModels) {
        this.context = context;
        this.nModels = nModels;
    }

    @NonNull

    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comments_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  CommentsAdapter.ViewHolder holder, int position) {
//        holder.username.setText(nModels.get(position).getUser());
        holder.comment_id.setText(nModels.get(position).getComment());

    }

    @Override
    public int getItemCount() {
        return nModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView comment_id;
        TextView dateCommented;
        TextView username;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            comment_id = itemView.findViewById(R.id.comment_id);
            dateCommented = itemView.findViewById(R.id.date_commented);
            username = itemView.findViewById(R.id.comment_user);
        }
    }
}
