package com.example.mental.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.R;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private Context context;
    private List<Bitmap> photos;

    public PhotoAdapter(Context context, List<Bitmap> photos) {
        this.context = context;
        this.photos = photos;
    }

    // 添加照片的方法
    public void addPhoto(byte[] photoData) {
        // 裁剪照片为1:1的大小
        Bitmap croppedPhoto = cropToSquare(BitmapFactory.decodeByteArray(photoData, 0, photoData.length));

        photos.add(croppedPhoto); // 将新的照片添加到列表
        notifyDataSetChanged(); // 通知适配器数据已更改
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Bitmap photo = photos.get(position);

        // 设置图片作为背景
        holder.photoImageView.setBackgroundResource(R.drawable.image_blank); // 设置默认背景图片

        // 如果有图片，设置为背景
        if (photo != null) {
            holder.photoImageView.setImageBitmap(photo);
        }
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView photoImageView;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.photo_item_image);
        }
    }

    // 辅助方法：将图片裁剪为1:1的大小
    private Bitmap cropToSquare(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int size = Math.min(width, height);

        int x = (width - size) / 2;
        int y = (height - size) / 2;

        return Bitmap.createBitmap(bitmap, x, y, size, size);
    }
}
