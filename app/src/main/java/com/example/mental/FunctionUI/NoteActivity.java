package com.example.mental.FunctionUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.ActionAdapter;
import com.example.mental.Adapter.EmotionAdapter;
import com.example.mental.Adapter.EmotionCardAdapter;
import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.Definition.ActionCardItem;
import com.example.mental.Definition.ActionItem;
import com.example.mental.Definition.EmotionCardItem;
import com.example.mental.Definition.EmotionItem;
import com.example.mental.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NoteActivity extends AppCompatActivity {
    private EmotionAdapter emotionAdapter;
    private ActionAdapter actionAdapter;

    private List<EmotionItem> emotionItems; // 添加此行
    private List<ActionItem> actionItems;   // 添加此行
    private Handler handler;
    private Runnable updateTimeRunnable;

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


        // 日期时钟渲染
        TextView dateTextView = findViewById(R.id.emotionCalendarText);
        handler = new Handler();
        updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                // 获取当前日期时间
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss", Locale.getDefault());
                String currentDate = dateFormat.format(calendar.getTime());

                // 更新 TextView 上的时间
                dateTextView.setText(currentDate);

                // 定期更新时间，例如每秒更新一次
                handler.postDelayed(this, 1000); // 延迟 1 秒后再次运行该 Runnable
            }
        };
        // 启动定时更新
        handler.post(updateTimeRunnable);


        // 设置展开状态的图标
        ImageView image_emotion_point = findViewById(R.id.image_emotion_point);
        // 设置展开
        TextView button_emotion_card_expand = findViewById(R.id.button_emotion_card_expand);
        final CardView[] card_emotion_card_choice = {findViewById(R.id.card_emotion_card_choice)};
        final boolean[] isExpanded = {false};
        button_emotion_card_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExpanded[0]) {
                    // 如果当前是展开状态，则折叠它
                    card_emotion_card_choice[0].getLayoutParams().height = 100;  // 设置高度为0，即隐藏
                    // 如果当前是展开状态，则设置图标为展开时的图标
                    image_emotion_point.setImageResource(R.drawable.icon_down);
                } else {
                    // 如果当前是折叠状态，则展开它
                    card_emotion_card_choice[0].getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;  // 设置为wrap_content，即展开
                    image_emotion_point.setImageResource(R.drawable.icon_up);
                }
                card_emotion_card_choice[0].requestLayout();  // 通知布局发生更改
                isExpanded[0] = !isExpanded[0];  // 切换状态
            }
        });

        image_emotion_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExpanded[0]) {
                    // 如果当前是展开状态，则折叠它
                    card_emotion_card_choice[0].getLayoutParams().height = 100;  // 设置高度为0，即隐藏
                    // 如果当前是展开状态，则设置图标为展开时的图标
                    image_emotion_point.setImageResource(R.drawable.icon_down);
                } else {
                    // 如果当前是折叠状态，则展开它
                    card_emotion_card_choice[0].getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;  // 设置为wrap_content，即展开
                    image_emotion_point.setImageResource(R.drawable.icon_up);
                }
                card_emotion_card_choice[0].requestLayout();  // 通知布局发生更改
                isExpanded[0] = !isExpanded[0];  // 切换状态
            }
        });


        // 初始化 emotionItems 和 actionItems
        emotionItems = new ArrayList<>();
        actionItems = new ArrayList<>();
        // 创建情绪项列表
        List<EmotionItem> emotionItems = new ArrayList<>();
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_ecstasy, "狂喜", "#77CA40", false));
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_happy, "开心", "#0EBF7B", false));
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_unknown, "未知", "#1095C1", true));
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_bad, "不爽", "#ED9A0C", false));
        emotionItems.add(new EmotionItem(R.drawable.icon_emotion_terrible, "糟糕", "#CB4F4F", false));

        // 初始化情绪记录的 RecyclerView
        RecyclerView emotionRecyclerView = findViewById(R.id.emotionView);
        GridLayoutManager emotionLayoutManager = new GridLayoutManager(this, emotionItems.size(), GridLayoutManager.VERTICAL, false);
        emotionRecyclerView.setLayoutManager(emotionLayoutManager);
        // 初始化情绪记录的适配器并设置
        boolean isClickable = true; // 在本Activity中禁用点击事件
        emotionAdapter = new EmotionAdapter(emotionItems, this, isClickable);
        emotionRecyclerView.setAdapter(emotionAdapter);


        // 创建活动项列表
        List<ActionItem> actionItems = new ArrayList<>();
        // 卡片内容: 工作 学习 思考 出游 游戏 干饭 睡觉 阅读 咖啡 运动 电视 发呆
        actionItems.add(new ActionItem(R.drawable.icon_action_work, "工作"));
        actionItems.add(new ActionItem(R.drawable.icon_action_study, "学习"));
        actionItems.add(new ActionItem(R.drawable.icon_action_think, "思考"));
        actionItems.add(new ActionItem(R.drawable.icon_action_bike, "出游"));
        actionItems.add(new ActionItem(R.drawable.icon_action_game, "游戏"));
        actionItems.add(new ActionItem(R.drawable.icon_action_eat, "干饭"));
        actionItems.add(new ActionItem(R.drawable.icon_action_sleep, "睡觉"));
        actionItems.add(new ActionItem(R.drawable.icon_action_read, "阅读"));
        actionItems.add(new ActionItem(R.drawable.icon_action_coffee, "咖啡"));
        actionItems.add(new ActionItem(R.drawable.icon_action_sport, "运动"));
        actionItems.add(new ActionItem(R.drawable.icon_action_tv, "电视"));
        actionItems.add(new ActionItem(R.drawable.icon_action_daze, "发呆"));
        // 初始化活动项的 RecyclerView
        RecyclerView actionRecyclerView = findViewById(R.id.actionView);
        int spanCount = 5; // 一行显示的最大列数
        GridLayoutManager actionLayoutManager = new GridLayoutManager(this, spanCount);
        actionRecyclerView.setLayoutManager(actionLayoutManager);

        // 获取 EmotionAdapter 和 ActionAdapter 的引用
        actionAdapter = new ActionAdapter(actionItems, this);
        actionRecyclerView.setAdapter(actionAdapter);


        // 创建虚拟情绪卡片数据
        List<EmotionCardItem> emotionCardItems = new ArrayList<>();
        List<ActionCardItem> actionList1 = new ArrayList<>();
        actionList1.add(new ActionCardItem(R.drawable.icon_action_work, "工作"));
        actionList1.add(new ActionCardItem(R.drawable.icon_action_sport, "运动"));
        EmotionCardItem emotionCard1 = new EmotionCardItem("2023年9月18日 13:55:26", R.drawable.icon_emotion_happy, "开心", actionList1);
        emotionCardItems.add(emotionCard1); // 添加到情绪卡片列表

        List<ActionCardItem> actionList2 = new ArrayList<>();
        actionList2.add(new ActionCardItem(R.drawable.icon_action_sleep, "睡觉"));
        actionList2.add(new ActionCardItem(R.drawable.icon_action_read, "阅读"));
        EmotionCardItem emotionCard2 = new EmotionCardItem("2023年9月18日 13:55:26", R.drawable.icon_emotion_terrible, "难过", actionList2);
        emotionCardItems.add(emotionCard2); // 添加到情绪卡片列表

        List<ActionCardItem> actionList3 = new ArrayList<>();
        actionList3.add(new ActionCardItem(R.drawable.icon_action_study, "学习"));
        actionList3.add(new ActionCardItem(R.drawable.icon_action_bike, "出游"));
        actionList3.add(new ActionCardItem(R.drawable.icon_action_eat, "干饭"));
        actionList3.add(new ActionCardItem(R.drawable.icon_action_coffee, "咖啡"));
        EmotionCardItem emotionCard3 = new EmotionCardItem("2023年9月18日 13:55:26", R.drawable.icon_emotion_terrible, "难过", actionList3);
        emotionCardItems.add(emotionCard3); // 添加到情绪卡片列表

        List<ActionCardItem> actionList4 = new ArrayList<>();
        actionList4.add(new ActionCardItem(R.drawable.icon_action_sleep, "睡觉"));
        actionList4.add(new ActionCardItem(R.drawable.icon_action_eat, "干饭"));
        actionList4.add(new ActionCardItem(R.drawable.icon_action_tv, "电视"));
        EmotionCardItem emotionCard4 = new EmotionCardItem("2023年9月18日 13:55:26", R.drawable.icon_emotion_terrible, "难过", actionList4);
        emotionCardItems.add(emotionCard4); // 添加到情绪卡片列表

        // 初始化 EmotionCardView 的 RecyclerView
        RecyclerView emotionCardRecyclerView = findViewById(R.id.EmotionCardView);
        LinearLayoutManager emotionCardLayoutManager = new LinearLayoutManager(this);
        emotionCardRecyclerView.setLayoutManager(emotionCardLayoutManager);

        // 初始化情绪卡片适配器并设置
        EmotionCardAdapter emotionCardAdapter = new EmotionCardAdapter(emotionCardItems, this);
        emotionCardLayoutManager.setReverseLayout(true); // 设置为反向布局
        emotionCardRecyclerView.setAdapter(emotionCardAdapter);


        // 保存提交按钮
        CardView saveButton = findViewById(R.id.button_note_makeSure);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {

                // 创建一个Handler
                Handler handler = new Handler();
                // 保存和上传数据的逻辑
                List<ActionCardItem> actionList1 = new ArrayList<>();
                actionList1.add(new ActionCardItem(R.drawable.icon_action_think, "思考"));
                actionList1.add(new ActionCardItem(R.drawable.icon_action_eat, "干饭"));
                actionList1.add(new ActionCardItem(R.drawable.icon_action_daze, "发呆"));
                EmotionCardItem emotionCard1 = new EmotionCardItem((String) dateTextView.getText(), R.drawable.icon_emotion_ecstasy, "狂喜", actionList1);
                // 延迟1秒后执行更新UI的操作
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        emotionCardItems.add(emotionCard1); // 添加到情绪卡片列表
                        // 更新UI
                        emotionCardAdapter.notifyDataSetChanged();
                    }
                }, 2000); // 1000毫秒 = 1秒


            }
        });

    }

}


