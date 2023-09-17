package com.example.mental.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.EmotionItem;
import com.example.mental.R;

import java.util.List;

public class EmotionAdapter extends RecyclerView.Adapter<EmotionAdapter.EmotionViewHolder> {

    private List<EmotionItem> emotionItems;
    private Context context;
    private boolean isSelected;


    public EmotionAdapter(List<EmotionItem> emotionItems, Context context) {
        this.emotionItems = emotionItems;
        this.context = context;
    }

    @NonNull
    @Override
    public EmotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emotion, parent, false);
        return new EmotionViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull EmotionViewHolder holder, int position) {
        EmotionItem emotionItem = emotionItems.get(position);
        holder.emotionImageView.setImageResource(emotionItem.getEmotionImageResId());
        holder.emotionTextView.setText(emotionItem.getEmotionDescription());
        isSelected = emotionItem.isSelected();

        // 设置文本颜色
        int textColor = Color.parseColor(emotionItem.getEmotionColor());
        holder.emotionTextView.setTextColor(textColor);

        // 设置透明度
        if (isSelected) {
            holder.itemView.setAlpha(1f); // 选中时不透明度为100%
        } else {
            holder.itemView.setAlpha(0.5f); // 未选中时不透明度为50%
        }
    }


    @Override
    public int getItemCount() {
        return emotionItems.size();
    }

    public static class EmotionViewHolder extends RecyclerView.ViewHolder {
        ImageView emotionImageView;
        TextView emotionTextView;

        public EmotionViewHolder(@NonNull View itemView, final EmotionAdapter adapter) {
            super(itemView);
            emotionImageView = itemView.findViewById(R.id.emotionItemView);
            emotionTextView = itemView.findViewById(R.id.dateTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        EmotionItem emotionItem = adapter.emotionItems.get(position);
                        emotionItem.setSelected(!emotionItem.isSelected()); // 切换选中状态
                        adapter.notifyItemChanged(position);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

        }

    }

}

