package com.example.mental.Adapter;

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

    public FunctionAdapter(List<FunctionModule> functionModules) {
        this.functionModules = functionModules;
    }

    @NonNull
    @Override
    public FunctionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_function, parent, false);
        return new FunctionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FunctionViewHolder holder, int position) {
        FunctionModule functionModule = functionModules.get(position);
        holder.moduleIcon.setImageResource(functionModule.getIconResId());
        holder.moduleName.setText(functionModule.getModuleName());
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
