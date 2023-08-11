package com.example.mental.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.ActionCardItem;
import com.example.mental.R;

import java.util.List;

public class ActionCardAdapter extends RecyclerView.Adapter<ActionCardAdapter.ActionCardViewHolder> {

    private List<ActionCardItem> actionCardItems;

    public ActionCardAdapter(List<ActionCardItem> actionCardItems, Context context) {
        this.actionCardItems = actionCardItems;
    }

    @NonNull
    @Override
    public ActionCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actioncard, parent, false);
        return new ActionCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionCardViewHolder holder, int position) {
        ActionCardItem actionCardItem = actionCardItems.get(position);
        holder.actionItemView.setImageResource(actionCardItem.getActionImageResId());
        holder.dateTextView.setText(actionCardItem.getActionText());
    }

    @Override
    public int getItemCount() {
        return actionCardItems.size();
    }

    public class ActionCardViewHolder extends RecyclerView.ViewHolder {
        ImageView actionItemView;
        TextView dateTextView;

        public ActionCardViewHolder(@NonNull View itemView) {
            super(itemView);
            actionItemView = itemView.findViewById(R.id.actionItemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
