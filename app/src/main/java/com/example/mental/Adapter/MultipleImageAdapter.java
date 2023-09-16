package com.example.mental.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mental.R;

import java.util.List;

public class MultipleImageAdapter extends RecyclerView.Adapter<MultipleImageAdapter.MultipleImageViewHolder> {

    private Context context;
    private List<Integer> imageResIds; // 图片资源ID列表

    public MultipleImageAdapter(Context context, List<Integer> imageResIds) {
        this.context = context;
        this.imageResIds = imageResIds;
    }

    @NonNull
    @Override
    public MultipleImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_multipleimage, parent, false);
        return new MultipleImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MultipleImageViewHolder holder, int position) {
        int imageResId = imageResIds.get(position);
        holder.imageView.setImageResource(imageResId);
    }

    @Override
    public int getItemCount() {
        return imageResIds.size();
    }

    public static class MultipleImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MultipleImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.multiple_image_view);
        }
    }
}
