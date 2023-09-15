package com.example.mental.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.R;

import java.util.List;

public class MeditationIntroduceAdapter extends RecyclerView.Adapter<MeditationIntroduceAdapter.MeditationClassIntroduceViewHolder> {

    private List<String> meditationIntroduces;
    private List<String> meditationContents;
    private Context context;

    public MeditationIntroduceAdapter(List<String> meditationIntroduces, List<String> meditationContents, Context context) {
        this.meditationIntroduces = meditationIntroduces;
        this.meditationContents = meditationContents;
        this.context = context;
    }

    @NonNull
    @Override
    public MeditationClassIntroduceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meditationintroduce, parent, false);
        return new MeditationClassIntroduceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeditationClassIntroduceViewHolder holder, int position) {
        String meditationIntroduce = meditationIntroduces.get(position);
        holder.titleTextView.setText(meditationIntroduce);

        if (position < meditationContents.size()) {
            String meditationContent = meditationContents.get(position);
            holder.contentTextView.setText(meditationContent);
        } else {
            holder.contentTextView.setText(""); // 没有对应内容时，置空文本
        }
    }

    @Override
    public int getItemCount() {
        return meditationIntroduces.size();
    }

    // 用于更新冥想内容介绍列表的方法
    public void updateMeditationContents(List<String> contents) {
        this.meditationContents = contents;
        notifyDataSetChanged();
    }

    static class MeditationClassIntroduceViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView contentTextView;

        MeditationClassIntroduceViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.meditationIntroduceTitle);
            contentTextView = itemView.findViewById(R.id.meditationIntroduceTag);
        }

    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


}