package com.example.mental.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.AudioCardItem;
import com.example.mental.FunctionUI.MeditationAudioActivity;
import com.example.mental.R;

import java.util.List;

public class AudioCardAdapter extends RecyclerView.Adapter<AudioCardAdapter.AudioCardViewHolder> {

    private List<AudioCardItem> audioCardItems;
    private Context context;

    public AudioCardAdapter(List<AudioCardItem> audioCardItems, Context context) {
        this.audioCardItems = audioCardItems;
        this.context = context;
    }

    @NonNull
    @Override
    public AudioCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_audiocard, parent, false);
        return new AudioCardViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioCardViewHolder holder, int position) {
        AudioCardItem audioCardItem = audioCardItems.get(position);
        holder.audioNameTextView.setText(audioCardItem.getAudioName());
        holder.playCountTextView.setText("播放量：" + audioCardItem.getPlayCount());

        // 设置音频卡片的图像
        int audioImageResId = audioCardItem.getAudioImage();
        holder.audioImageView.setImageResource(audioImageResId);
    }

    @Override
    public int getItemCount() {
        return audioCardItems.size();
    }

    static class AudioCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView audioNameTextView;
        TextView playCountTextView;
        ImageView audioImageView; // 添加 ImageView 成员变量
        Context context;

        AudioCardViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            audioNameTextView = itemView.findViewById(R.id.card_audioCard_audioName);
            playCountTextView = itemView.findViewById(R.id.card_audioCard_playCount);
            audioImageView = itemView.findViewById(R.id.card_audioCard_audioImage); // 获取 ImageView
            this.context = context;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, MeditationAudioActivity.class);
            context.startActivity(intent);
        }
    }
}
