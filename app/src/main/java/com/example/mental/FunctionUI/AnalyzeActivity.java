package com.example.mental.FunctionUI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.EmotionAdapter;
import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.Definition.ActionItem;
import com.example.mental.Definition.EmotionItem;
import com.example.mental.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;


public class AnalyzeActivity extends AppCompatActivity {
    private List<EmotionItem> emotionItems; // 添加此行
    private EmotionAdapter emotionAdapter;
    private List<ActionItem> actionItems;   // 添加此行

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.AnalyzeHeaderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "心情解析";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);

        // 初始化表情介绍
        // 初始化 emotionItems 和 actionItems

        emotionItems = new ArrayList<>();
        actionItems = new ArrayList<>();
        // 创建情绪项列表
        List<EmotionItem> emotionItems = new ArrayList<>();
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_ecstasy, "狂喜", "#77CA40", true));
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_happy, "开心", "#0EBF7B", true));
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_unknown, "未知", "#1095C1", true));
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_bad, "不爽", "#ED9A0C", true));
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_terrible, "糟糕", "#CB4F4F", true));

        // 初始化情绪记录的 RecyclerView
        RecyclerView emotionRecyclerView = findViewById(R.id.AnalyzeHEmotionRecyclerView);
        GridLayoutManager emotionLayoutManager = new GridLayoutManager(this, emotionItems.size(), GridLayoutManager.VERTICAL, false);
        emotionRecyclerView.setLayoutManager(emotionLayoutManager);
        // 初始化情绪记录的适配器并设置
        boolean isClickable = false; // 在本Activity中禁用点击事件
        emotionAdapter = new EmotionAdapter(emotionItems, this, isClickable);
        emotionRecyclerView.setAdapter(emotionAdapter);


        // 为柱状图准备数据
        BarChart barChart = findViewById(R.id.barChart);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 5));   // 狂喜
        barEntries.add(new BarEntry(2, 11));   // 开心
        barEntries.add(new BarEntry(3, 5));   // 未知
        barEntries.add(new BarEntry(4, 4));    // 不爽
        barEntries.add(new BarEntry(5, 4));   // 糟糕
        BarDataSet barDataSet = new BarDataSet(barEntries, "心情对应色块");

        // 设置颜色为R.color中定义的颜色资源
        barDataSet.setColors(ContextCompat.getColor(this, R.color.emotion_ecstasy),
                ContextCompat.getColor(this, R.color.emotion_happy),
                ContextCompat.getColor(this, R.color.emotion_unknown),
                ContextCompat.getColor(this, R.color.emotion_bad),
                ContextCompat.getColor(this, R.color.emotion_terrible));

        barChart.getDescription().setEnabled(false);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.invalidate(); // 刷新图表

// 为饼图准备数据
        PieChart pieChart = findViewById(R.id.pieChart);
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(5, "狂喜"));
        pieEntries.add(new PieEntry(11, "开心"));
        pieEntries.add(new PieEntry(5, "未知"));
        pieEntries.add(new PieEntry(4, "不爽"));
        pieEntries.add(new PieEntry(4, "糟糕"));
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "心情对应色块");

        // 设置颜色为R.color中定义的颜色资源
        pieDataSet.setColors(ContextCompat.getColor(this, R.color.emotion_ecstasy),
                ContextCompat.getColor(this, R.color.emotion_happy),
                ContextCompat.getColor(this, R.color.emotion_unknown),
                ContextCompat.getColor(this, R.color.emotion_bad),
                ContextCompat.getColor(this, R.color.emotion_terrible));

        pieChart.getDescription().setEnabled(false);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate(); // 刷新图表


    }
}
