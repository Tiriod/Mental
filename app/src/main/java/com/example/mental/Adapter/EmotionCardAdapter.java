package com.example.mental.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.EmotionCardItem;
import com.example.mental.R;

import java.util.List;

public class EmotionCardAdapter extends RecyclerView.Adapter<EmotionCardAdapter.EmotionCardViewHolder> {

    private List<EmotionCardItem> emotionCardItems;
    private Context context;

    public EmotionCardAdapter(List<EmotionCardItem> emotionCardItems, Context context) {
        this.emotionCardItems = emotionCardItems;
        this.context = context;
    }

    @NonNull
    @Override
    public EmotionCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emotioncard, parent, false);
        return new EmotionCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmotionCardViewHolder holder, int position) {
        EmotionCardItem emotionCardItem = emotionCardItems.get(position);
        holder.emotionCardCalendarText.setText(emotionCardItem.getEmotionDate());
        holder.emotionCardImage.setImageResource(emotionCardItem.getEmotionImageResId());
        holder.emotionCardText.setText(emotionCardItem.getEmotionText());

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.emotionCardActionView.setLayoutManager(layoutManager);

        ActionCardAdapter actionCardAdapter = new ActionCardAdapter(emotionCardItem.getActionList(), context);
        holder.emotionCardActionView.setAdapter(actionCardAdapter);
    }



    @Override
    public int getItemCount() {
        return emotionCardItems.size();
    }

    public class EmotionCardViewHolder extends RecyclerView.ViewHolder {
        TextView emotionCardCalendarText;
        ImageView emotionCardImage;
        TextView emotionCardText;
        RecyclerView emotionCardActionView;

        public EmotionCardViewHolder(@NonNull View itemView) {
            super(itemView);
            emotionCardCalendarText = itemView.findViewById(R.id.emotionCardCalendarText);
            emotionCardImage = itemView.findViewById(R.id.emotionCardImage);
            emotionCardText = itemView.findViewById(R.id.emotionCardText);
            emotionCardActionView = itemView.findViewById(R.id.emotionCardActionView);
        }
    }
}

