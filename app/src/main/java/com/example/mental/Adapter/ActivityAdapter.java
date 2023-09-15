package com.example.mental.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.ActivityItem;
import com.example.mental.R;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {
    private List<ActivityItem> activityList;

    public ActivityAdapter(List<ActivityItem> activityList) {
        this.activityList = activityList;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        ActivityItem activityItem = activityList.get(position);
        holder.activityImageView.setImageResource(activityItem.getImageResId());
        holder.activityNameTextView.setText(activityItem.getActivityName());
        holder.activityIntroduceTextView.setText(activityItem.getActivityIntroduce());

        // 添加以下日志来检查数据
        Log.d("ActivityAdapter", "Position: " + position);
        Log.d("ActivityAdapter", "Activity Name: " + activityItem.getActivityName());
        Log.d("ActivityAdapter", "Activity Introduce: " + activityItem.getActivityIntroduce());
        Log.d("ActivityAdapter", "Activity List Size: " + activityList.size());

    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    static class ActivityViewHolder extends RecyclerView.ViewHolder {
        ImageView activityImageView;
        TextView activityNameTextView;
        TextView activityIntroduceTextView;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            activityImageView = itemView.findViewById(R.id.card_activity_image);
            activityNameTextView = itemView.findViewById(R.id.card_activity_name);
            activityIntroduceTextView = itemView.findViewById(R.id.card_activity_introduce);
        }
    }
}
