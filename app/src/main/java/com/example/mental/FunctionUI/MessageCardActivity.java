package com.example.mental.FunctionUI;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mental.Definition.MessageItem;
import com.example.mental.R;

public class MessageCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagecard);

        // 获取传递的附加数据
        Intent intent = getIntent();
        MessageItem messageItem = (MessageItem) intent.getSerializableExtra("messageItem");
        int imageResource = messageItem.getImageResource();
        String title = messageItem.getTitle();
        String description = messageItem.getDescription();
        String time = messageItem.getTime();
        String source = messageItem.getSource();
        System.out.println(imageResource);
        System.out.println(title);
        System.out.println(description);
        System.out.println(time);
        System.out.println(source);

    }
}