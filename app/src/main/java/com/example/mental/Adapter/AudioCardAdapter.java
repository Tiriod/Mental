package com.example.mental.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_audiocard, parent, false);
        return new AudioCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioCardViewHolder holder, int position) {
        AudioCardItem audioCardItem = audioCardItems.get(position);
        holder.audioNameTextView.setText(audioCardItem.getAudioName());
        holder.playCountTextView.setText("播放量：" + audioCardItem.getPlayCount());
    }

    @Override
    public int getItemCount() {
        return audioCardItems.size();
    }

    static class AudioCardViewHolder extends RecyclerView.ViewHolder {

        TextView audioNameTextView;
        TextView playCountTextView;

        AudioCardViewHolder(@NonNull View itemView) {
            super(itemView);
            audioNameTextView = itemView.findViewById(R.id.audioNameTextView);
            playCountTextView = itemView.findViewById(R.id.playCountTextView);
        }
    }
}

