package com.example.mental.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.ShareLoopItem;
import com.example.mental.R;

import java.util.List;

public class ShareLoopAdapter extends RecyclerView.Adapter<ShareLoopAdapter.ShareLoopViewHolder> {

    private Context context;
    private List<ShareLoopItem> shareLoopItems;

    public ShareLoopAdapter(Context context, List<ShareLoopItem> shareLoopItems) {
        this.context = context;
        this.shareLoopItems = shareLoopItems;
    }

    @NonNull
    @Override
    public ShareLoopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shareloopcard, parent, false);
        return new ShareLoopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShareLoopViewHolder holder, int position) {
        ShareLoopItem item = shareLoopItems.get(position);

        // 设置用户头像
        holder.userAvatarCardView.setImageResource(item.getUserAvatarResId());

        // 设置用户名
        holder.usernameTextView.setText(item.getUsername());

        // 设置用户心情图标
        holder.userEmotionImageView.setImageResource(item.getUserEmotionResId());

        // 设置富文本内容
        holder.textContentTextView.setText(item.getText());

        // 设置发布时间
        holder.releaseTimeTextView.setText(item.getReleaseTime());

        // 设置多张图片
        MultipleImageAdapter imageAdapter = new MultipleImageAdapter(context, item.getImageResIds());
        holder.contentImageView.setAdapter(imageAdapter);

        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.contentImageView.setLayoutManager(layoutManager);


    }

    @Override
    public int getItemCount() {
        return shareLoopItems.size();
    }

    public static class ShareLoopViewHolder extends RecyclerView.ViewHolder {
        ImageView userAvatarCardView;
        TextView usernameTextView;
        ImageView userEmotionImageView;
        TextView textContentTextView;
        RecyclerView contentImageView; // 这里是一个 RecyclerView 用于显示多张图片
        TextView releaseTimeTextView;

        public ShareLoopViewHolder(@NonNull View itemView) {
            super(itemView);
            userAvatarCardView = itemView.findViewById(R.id.card_shareLoop_userAvatar);
            usernameTextView = itemView.findViewById(R.id.card_shareLoop_username);
            userEmotionImageView = itemView.findViewById(R.id.card_shareLoop_userEmotion);
            textContentTextView = itemView.findViewById(R.id.card_shareLoop_text);
            contentImageView = itemView.findViewById(R.id.card_shareLoop_imageList);
            releaseTimeTextView = itemView.findViewById(R.id.card_shareLoop_releaseTime);
        }
    }
}
