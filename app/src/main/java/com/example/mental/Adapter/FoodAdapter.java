package com.example.mental.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.FoodItem;
import com.example.mental.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<FoodItem> foodItems;

    public FoodAdapter(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);

        holder.foodImageView.setImageResource(foodItem.getImageResId());
        holder.nameTextView.setText(foodItem.getName());
        holder.weightTextView.setText(foodItem.getWeight());
        holder.caloriesTextView.setText(foodItem.getCalories());
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImageView;
        TextView nameTextView;
        TextView weightTextView;
        TextView caloriesTextView;

        FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImageView = itemView.findViewById(R.id.foodImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            weightTextView = itemView.findViewById(R.id.weightTextView);
            caloriesTextView = itemView.findViewById(R.id.caloriesTextView);
        }
    }

    public void updateFoodItems(List<FoodItem> newFoodItems) {
        // 清除原有数据
        foodItems.clear();
        // 添加新数据
        foodItems.addAll(newFoodItems);
        // 通知适配器数据已更改
        notifyDataSetChanged();
    }
}

