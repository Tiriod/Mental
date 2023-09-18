package com.example.mental.Adapter;

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

    public EmotionAdapter(List<EmotionItem> emotionItems, Context context) {
        this.emotionItems = emotionItems;
        this.context = context;
    }

    @NonNull
    @Override
    public EmotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emotion, parent, false);
        return new EmotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmotionViewHolder holder, int position) {
        EmotionItem emotionItem = emotionItems.get(position);
        holder.emotionImageView.setImageResource(emotionItem.getEmotionImageResId());
        holder.emotionTextView.setText(emotionItem.getEmotionDescription());

        // 设置文本颜色
        int textColor = Color.parseColor(emotionItem.getEmotionColor());
        holder.emotionTextView.setTextColor(textColor);

        // 设置透明度
        float alpha = emotionItem.isSelected() ? 1f : 0.5f;
        holder.itemView.setAlpha(alpha);
    }

    @Override
    public int getItemCount() {
        return emotionItems.size();
    }

    class EmotionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView emotionImageView;
        TextView emotionTextView;

        EmotionViewHolder(@NonNull View itemView) {
            super(itemView);
            emotionImageView = itemView.findViewById(R.id.emotionItemView);
            emotionTextView = itemView.findViewById(R.id.dateTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                for (int i = 0; i < emotionItems.size(); i++) {
                    emotionItems.get(i).setSelected(i == adapterPosition);
                }
                notifyDataSetChanged();
            }
        }
    }
}
