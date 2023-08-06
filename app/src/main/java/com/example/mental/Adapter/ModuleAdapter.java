package com.example.mental.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.R;

import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {
    // 模块存放列表
    private List<String> moduleNameList;
    private List<String> moduleIntroduceList;
    // 模块点击事件监听
    private OnModuleClickListener onModuleClickListener;

    // 首页功能模块适配器
    public ModuleAdapter(List<String> moduleNameList, List<String> moduleIntroduceList, OnModuleClickListener onModuleClickListener) {
        this.moduleNameList = moduleNameList;
        this.moduleIntroduceList = moduleIntroduceList;

        this.onModuleClickListener = onModuleClickListener;
    }

    // 视图Holder创建
    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homemodule, parent, false);
        return new ModuleViewHolder(itemView);
    }

    // 绑定视图holder
    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        String moduleName = moduleNameList.get(position);
        String moduleIntroduce = moduleIntroduceList.get(position);
        holder.moduleName.setText(moduleName);
        holder.moduleIntroduce.setText(moduleIntroduce);
    }

    // 内部模块内容计数
    @Override
    public int getItemCount() {
        return moduleNameList.size();
    }

    class ModuleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView moduleName;
        TextView moduleIntroduce;

        ModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            moduleName = itemView.findViewById(R.id.moduleName);
            moduleIntroduce = itemView.findViewById(R.id.moduleIntroduce);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onModuleClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onModuleClickListener.onModuleClick(position);
                }
            }
        }
    }

    public interface OnModuleClickListener {
        void onModuleClick(int position);

        void onFunctionClick(int position);
    }
}
