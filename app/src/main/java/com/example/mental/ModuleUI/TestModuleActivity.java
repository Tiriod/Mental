package com.example.mental.ModuleUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.R;

public class TestModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testmodule);

        // 头部标签的创建:初始化 RecyclerView
        RecyclerView headerRecyclerView = findViewById(R.id.TestHeaderRecyclerView);
        // 设置布局管理器，垂直方向
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        // 初始化头部标签的数据，设置 Adapter
        String headerText = "测一测"; // 根据需要设置头部标签的文字内容
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);
    }
}