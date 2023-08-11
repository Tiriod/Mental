package com.example.mental.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.UserCardItem;
import com.example.mental.R;

import java.util.List;

public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.UserCardViewHolder> {
    private List<UserCardItem> userCardItems;
    private Context context;

    public UserCardAdapter(List<UserCardItem> userCardItems, Context context) {
        this.userCardItems = userCardItems;
        this.context = context;
    }

    @NonNull
    @Override
    public UserCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usercard, parent, false);
        return new UserCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCardViewHolder holder, int position) {
        UserCardItem userCardItem = userCardItems.get(position);
        holder.userAvatarImageView.setImageResource(userCardItem.getAvatarResId());
        holder.userNameTextView.setText(userCardItem.getUserName());
        holder.userIdTextView.setText(userCardItem.getUserId());
    }

    @Override
    public int getItemCount() {
        return userCardItems.size();
    }

    static class UserCardViewHolder extends RecyclerView.ViewHolder {
        ImageView userAvatarImageView;
        TextView userNameTextView;
        TextView userIdTextView;

        public UserCardViewHolder(@NonNull View itemView) {
            super(itemView);
            userAvatarImageView = itemView.findViewById(R.id.userAvatarImageView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            userIdTextView = itemView.findViewById(R.id.userIdTextView);
        }
    }
}

