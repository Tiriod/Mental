package com.example.mental.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.ActionItem;
import com.example.mental.R;

import java.util.List;

public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.ActionViewHolder> {
    private final List<ActionItem> actionItems;
    private final Context context;

    public ActionAdapter(List<ActionItem> actionItems, Context context) {
        this.actionItems = actionItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ActionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_action, parent, false);
        return new ActionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionViewHolder holder, int position) {
        ActionItem actionItem = actionItems.get(position);
        holder.actionImageView.setImageResource(actionItem.getIconResId());
        holder.actionTextView.setText(actionItem.getItemName());

        if (actionItem.isSelected()) {
            holder.actionImageView.setBackgroundResource(R.drawable.shape_banner);
            holder.actionImageView.setColorFilter(ContextCompat.getColor(context, R.color.white));
        } else {
            holder.actionImageView.setBackgroundResource(R.drawable.shape_card_view);
            holder.actionImageView.setColorFilter(ContextCompat.getColor(context, R.color.light_green));
        }
    }

    @Override
    public int getItemCount() {
        return actionItems.size();
    }

    class ActionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView actionImageView;
        TextView actionTextView;

        ActionViewHolder(@NonNull View itemView) {
            super(itemView);
            actionImageView = itemView.findViewById(R.id.actionItemView);
            actionTextView = itemView.findViewById(R.id.dateTextView);
            itemView.setOnClickListener(this);
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                for (int i = 0; i < actionItems.size(); i++) {
                    actionItems.get(i).setSelected(i == adapterPosition);
                }
                notifyDataSetChanged();
            }
        }
    }
}
