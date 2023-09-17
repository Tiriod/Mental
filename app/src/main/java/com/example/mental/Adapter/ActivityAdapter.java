package com.example.mental.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.ActivityItem;
import com.example.mental.FunctionUI.ActivityDetailsActivity;
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

        // 添加点击事件监听器
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 在点击时触发跳转到ActivityDetailsActivity
                Intent intent = new Intent(view.getContext(), ActivityDetailsActivity.class);
                // 将所需的数据放入Intent中
                intent.putExtra("activityName", activityItem.getActivityName());
                intent.putExtra("activityIntroduce", activityItem.getActivityIntroduce());
                intent.putExtra("activityImage", activityItem.getImageResId()); // 传递图像资源ID
                // 启动ActivityDetailsActivity
                view.getContext().startActivity(intent);
            }
        });
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
