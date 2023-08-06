package com.example.mental.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.FunctionModule;
import com.example.mental.R;

import java.util.List;

public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.FunctionViewHolder> {

    private List<FunctionModule> functionModules;
    private OnFunctionClickListener functionClickListener;

    public FunctionAdapter(List<FunctionModule> functionModules, OnFunctionClickListener functionClickListener) {
        this.functionModules = functionModules;
        this.functionClickListener = functionClickListener;
    }

    // 声明 OnFunctionClickListener 接口
    public interface OnFunctionClickListener {
        void onFunctionClick(int position);
    }

    @NonNull
    @Override
    public FunctionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_function, parent, false);
        return new FunctionViewHolder(itemView);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull FunctionViewHolder holder, final int position) {
        FunctionModule functionModule = functionModules.get(position);
        holder.moduleIcon.setImageResource(functionModule.getIconResId());
        holder.moduleName.setText(functionModule.getModuleName());

        // 设置点击监听器
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 调用接口的方法，在点击事件中处理点击事件
                if (functionClickListener != null) {
                    functionClickListener.onFunctionClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return functionModules.size();
    }

    static class FunctionViewHolder extends RecyclerView.ViewHolder {

        ImageView moduleIcon;
        TextView moduleName;

        public FunctionViewHolder(@NonNull View itemView) {
            super(itemView);
            moduleIcon = itemView.findViewById(R.id.moduleIcon);
            moduleName = itemView.findViewById(R.id.moduleName);
        }
    }
}
