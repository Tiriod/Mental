package com.example.mental.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.R;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder> {

    private Context context;
    private String headerText;


    public HeaderAdapter(Context context, String headerText) {
        this.context = context;
        this.headerText = headerText;
    }

    // 内部 ViewHolder 类
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconImageView;
        private TextView headerTextView;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.iconImageView);
            headerTextView = itemView.findViewById(R.id.headerTextView);
        }
    }

    @NonNull
    @Override
    public HeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeaderViewHolder holder, int position) {
        // 设置头部文字
        holder.headerTextView.setText(headerText);
        // 绑定数据到视图
        holder.iconImageView.setImageResource(R.drawable.icon_back); // 这里替换成你的返回图标资源
        holder.iconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 当点击图标按钮时，调用Activity的finish()方法来关闭当前Activity，实现返回到上一级Activity
                Activity activity = (Activity) v.getContext();
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1; // 只有一个头部，因此返回 1
    }
}
