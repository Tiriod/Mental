package com.example.mental.FunctionUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.JsonReader;
import android.widget.TextView;

import com.example.mental.Adapter.ActionAdapter;
import com.example.mental.Adapter.EmotionAdapter;
import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.Definition.ActionItem;
import com.example.mental.Definition.EmotionItem;
import com.example.mental.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.NoteHeaderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "心绪记录";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);


        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
        String currentDate = dateFormat.format(calendar.getTime());
        // 将当前日期设置到对应的 TextView
        TextView dateTextView = findViewById(R.id.emotionCalendarText);
        dateTextView.setText(currentDate);


        // 创建情绪项列表
        List<EmotionItem> emotionItems = new ArrayList<>();
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_ecstasy, "狂喜", "#77CA40"));
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_happy, "开心", "#0EBF7B"));
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_unknown, "未知", "#1095C1"));
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_bad, "不爽", "#ED9A0C"));
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_terrible, "糟糕", "#CB4F4F"));
        // 初始化情绪记录的 RecyclerView
        RecyclerView emotionRecyclerView = findViewById(R.id.emotionView);
        GridLayoutManager emotionLayoutManager = new GridLayoutManager(this, emotionItems.size(), GridLayoutManager.VERTICAL, false);
        emotionRecyclerView.setLayoutManager(emotionLayoutManager);
        // 初始化情绪记录的适配器并设置
        EmotionAdapter emotionAdapter = new EmotionAdapter(emotionItems, this);
        emotionRecyclerView.setAdapter(emotionAdapter);


        // 创建活动项列表
        List<ActionItem> actionItems = new ArrayList<>();
        actionItems.add(new ActionItem(R.drawable.icon_action_work, "工作"));
        actionItems.add(new ActionItem(R.drawable.icon_action_work, "工作"));
        actionItems.add(new ActionItem(R.drawable.icon_action_work, "工作"));
        actionItems.add(new ActionItem(R.drawable.icon_action_work, "工作"));
        actionItems.add(new ActionItem(R.drawable.icon_action_work, "工作"));
        actionItems.add(new ActionItem(R.drawable.icon_action_work, "工作"));
        actionItems.add(new ActionItem(R.drawable.icon_action_work, "工作"));
        // 初始化活动项的 RecyclerView
        RecyclerView actionRecyclerView = findViewById(R.id.actionView);
        int spanCount = 5; // 一行显示的最大列数
        GridLayoutManager actionLayoutManager = new GridLayoutManager(this, spanCount);
        actionRecyclerView.setLayoutManager(actionLayoutManager);
        // 初始化活动项的适配器并设置
        ActionAdapter actionAdapter = new ActionAdapter(actionItems, this);
        actionRecyclerView.setAdapter(actionAdapter);
    }
}