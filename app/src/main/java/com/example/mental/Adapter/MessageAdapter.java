package com.example.mental.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.MessageItem;
import com.example.mental.FunctionUI.MessageCardActivity;
import com.example.mental.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<MessageItem> messageItems;
    private Context context;

    public MessageAdapter(List<MessageItem> messageItems, Context context) {
        this.messageItems = messageItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_messagecard, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        MessageItem messageItem = messageItems.get(position);
        holder.messageImage.setImageResource(messageItem.getImageResource());
        holder.messageTitle.setText(messageItem.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建一个 Intent
                Intent intent = new Intent(context, MessageCardActivity.class);

                // 将 MessageItem 对象作为附加数据传递给下一个活动
                intent.putExtra("messageItem", messageItem);

                // 启动活动
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return messageItems.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        ImageView messageImage;
        TextView messageTitle;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageImage = itemView.findViewById(R.id.card_message_image);
            messageTitle = itemView.findViewById(R.id.card_message_title);
        }
    }
}

