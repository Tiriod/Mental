package com.example.mental.FunctionUI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.BookAdapter;
import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.Definition.BookItem;
import com.example.mental.R;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.ReadHeaderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "心里探索";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);


        // 在 Activity_read 中

        RecyclerView mentalHealthRecyclerView = findViewById(R.id.readMentalHealthCard);
        LinearLayoutManager mentalHealthLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mentalHealthRecyclerView.setLayoutManager(mentalHealthLayoutManager);
        List<BookItem> mentalHealthBookItems = new ArrayList<>();
        // 为心理健康书目添加数据
        mentalHealthBookItems.add(new BookItem(R.drawable.image_book, "书名1"));
        mentalHealthBookItems.add(new BookItem(R.drawable.image_book, "书名2"));
        mentalHealthBookItems.add(new BookItem(R.drawable.image_book, "书名3"));

        // 初始化心理健康 RecyclerView 的布局管理器，例如 LinearLayoutManager 或 GridLayoutManager
        BookAdapter mentalHealthAdapter = new BookAdapter(this, mentalHealthBookItems);
        mentalHealthRecyclerView.setAdapter(mentalHealthAdapter);


        // 同样地，为其他两个 RecyclerView 重复以上步骤
        RecyclerView aiAnalyzeRecyclerView = findViewById(R.id.readAIAnalyzeCard);
        LinearLayoutManager aiAnalyzeLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        aiAnalyzeRecyclerView.setLayoutManager(aiAnalyzeLayoutManager);
        List<BookItem> aiAnalyzeBookItems = new ArrayList<>();
        // 为AI分析书目添加数据
        aiAnalyzeBookItems.add(new BookItem(R.drawable.image_book, "书名4"));
        aiAnalyzeBookItems.add(new BookItem(R.drawable.image_book, "书名5"));
        aiAnalyzeBookItems.add(new BookItem(R.drawable.image_book, "书名6"));

        BookAdapter aiAnalyzeAdapter = new BookAdapter(this, aiAnalyzeBookItems);
        aiAnalyzeRecyclerView.setAdapter(aiAnalyzeAdapter);

        // 初始化 AI 分析 RecyclerView 的布局管理器

        RecyclerView thirdRecyclerView = findViewById(R.id.readThirdCard);
        // 创建布局管理器并设置给RecyclerView
        LinearLayoutManager thirdLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        thirdRecyclerView.setLayoutManager(thirdLayoutManager);
        List<BookItem> thirdBookItems = new ArrayList<>();
        // 为第三类书目添加数据
        thirdBookItems.add(new BookItem(R.drawable.image_book, "书名7"));
        thirdBookItems.add(new BookItem(R.drawable.image_book, "书名8"));
        thirdBookItems.add(new BookItem(R.drawable.image_book, "书名9"));

        BookAdapter thirdAdapter = new BookAdapter(this, thirdBookItems);
        thirdRecyclerView.setAdapter(thirdAdapter);

// 初始化第三类 RecyclerView 的布局管理器

    }
}