package com.example.mental.FunctionUI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.R;

public class ShareLoopEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareloopedit);
        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.ShareLoopEditHeaderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "分享圈编辑";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);
        // 页面跳转功能实现

    }
}