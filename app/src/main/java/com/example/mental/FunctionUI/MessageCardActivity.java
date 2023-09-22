package com.example.mental.FunctionUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.Definition.MessageItem;
import com.example.mental.R;

public class MessageCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagecard);

        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.MessageCardRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "AI资讯";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);


        // 获取传递的附加数据
        Intent intent = getIntent();
        MessageItem messageItem = (MessageItem) intent.getSerializableExtra("messageItem");
        TextView details_message_card_title = findViewById(R.id.details_message_card_title);
        ImageView details_message_card_image = findViewById(R.id.details_message_card_image);
        TextView details_activity_context = findViewById(R.id.details_activity_context);
        TextView details_message_card_coming = findViewById(R.id.details_message_card_coming);
        TextView details_message_card_time = findViewById(R.id.details_message_card_time);
        details_message_card_title.setText(messageItem.getTitle());
        details_message_card_image.setImageResource(messageItem.getImageResource());
        details_activity_context.setText(messageItem.getDescription());
        details_message_card_coming.setText(messageItem.getTime());
        details_message_card_time.setText(messageItem.getSource());


    }
}