package com.example.mental.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.R;

import java.util.List;

public class MeditationClassAdapter extends RecyclerView.Adapter<MeditationClassAdapter.MeditationClassViewHolder> {

    private List<String> meditationClasses;
    private Context context;
    private OnMeditationClassClickListener clickListener;
    private int selectedPosition = 0; // 保存选中的位置，默认为第一个位置（0）

    public interface OnMeditationClassClickListener {
        void onMeditationClassClick(int position);
    }

    public MeditationClassAdapter(List<String> meditationClasses, Context context) {
        this.meditationClasses = meditationClasses;
        this.context = context;
    }

    public void setOnMeditationClassClickListener(OnMeditationClassClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MeditationClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meditationclass, parent, false);
        return new MeditationClassViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeditationClassViewHolder holder, int position) {
        String meditationClass = meditationClasses.get(position);
        holder.textView.setText(meditationClass);

        // 根据选中的位置设置组件颜色
        if (selectedPosition == position) {
            holder.textView.setTextColor(Color.parseColor("#00AA66")); // 选中的颜色为#00AA66
        } else {
            holder.textView.setTextColor(Color.parseColor("#3D3D3D")); // 默认颜色为#3D3D3D
        }
    }

    @Override
    public int getItemCount() {
        return meditationClasses.size();
    }

    class MeditationClassViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        MeditationClassViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvMeditationClass);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        int clickedPosition = getAdapterPosition();
                        if (clickedPosition != RecyclerView.NO_POSITION) {
                            selectedPosition = clickedPosition; // 更新选中的位置
                            notifyDataSetChanged(); // 通知适配器数据已更改
                            clickListener.onMeditationClassClick(clickedPosition);
                        }
                    }
                }
            });
        }
    }
}

