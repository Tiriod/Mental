package com.example.mental.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.UserModeItem;
import com.example.mental.R;

import java.util.List;

public class UserModeAdapter extends RecyclerView.Adapter<UserModeAdapter.UserModeViewHolder> {

    private List<UserModeItem> userModeItems;
    private Context context;

    public UserModeAdapter(List<UserModeItem> userModeItems, Context context) {
        this.userModeItems = userModeItems;
        this.context = context;
    }

    @NonNull
    @Override
    public UserModeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_usermode, parent, false);
        return new UserModeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserModeViewHolder holder, int position) {
        UserModeItem userModeItem = userModeItems.get(position);
        holder.userModeIcon.setImageResource(userModeItem.getIconResId());
        holder.userModeName.setText(userModeItem.getModeName());
    }

    @Override
    public int getItemCount() {
        return userModeItems.size();
    }

    public class UserModeViewHolder extends RecyclerView.ViewHolder {
        ImageView userModeIcon;
        TextView userModeName;

        public UserModeViewHolder(@NonNull View itemView) {
            super(itemView);
            userModeIcon = itemView.findViewById(R.id.userModeIcon);
            userModeName = itemView.findViewById(R.id.userModeName);
        }
    }
}

