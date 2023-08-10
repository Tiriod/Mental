package com.example.mental.FunctionUI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.CalendarAdapter;
import com.example.mental.Adapter.FoodAdapter;
import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.Definition.FoodItem;
import com.example.mental.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FoodActivity extends AppCompatActivity {
    private List<String> daysOfWeek;
    private List<String> weekDates;
    private RecyclerView recyclerView;
    // 早中晚餐视图
    private RecyclerView breakfastRecyclerView;
    private RecyclerView lunchRecyclerView;
    private RecyclerView dinnerRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);


        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.FoodHeaderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "心身滋养";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);


        // 初始化日历组件
        recyclerView = findViewById(R.id.calendarRecyclerView);
        GridLayoutManager calendarLayoutManager = new GridLayoutManager(this, 7, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(calendarLayoutManager);

        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd", Locale.getDefault());
        String currentDate = dateFormat.format(calendar.getTime());

        // 计算本周的日期
        daysOfWeek = new ArrayList<>();
        weekDates = new ArrayList<>();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        for (int i = 0; i < 7; i++) {
            String dayOfWeek = getDayOfWeekName(calendar.get(Calendar.DAY_OF_WEEK));
            daysOfWeek.add(dayOfWeek);

            String date = dateFormat.format(calendar.getTime());
            weekDates.add(date);

            calendar.add(Calendar.DATE, 1);
        }

        // 初始化日历适配器并设置
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysOfWeek, weekDates, currentDate);
        recyclerView.setAdapter(calendarAdapter);



        // 营养食谱卡片
        // 初始化早餐RecyclerView
        breakfastRecyclerView = findViewById(R.id.breakfastView);
        LinearLayoutManager breakfastLayoutManager = new LinearLayoutManager(this);
        breakfastRecyclerView.setLayoutManager(breakfastLayoutManager);
        // 初始化早餐食物数据，例如：
        List<FoodItem> breakfastItems = new ArrayList<>();
        breakfastItems.add(new FoodItem(R.drawable.image_rice,"食物1", "150g", "300卡路里"));
        breakfastItems.add(new FoodItem(R.drawable.image_rice,"食物1", "150g", "300卡路里"));
        breakfastItems.add(new FoodItem(R.drawable.image_rice,"食物1", "150g", "300卡路里"));
        // ...
        FoodAdapter breakfastAdapter = new FoodAdapter(breakfastItems);
        breakfastRecyclerView.setAdapter(breakfastAdapter);

        // 初始化午餐RecyclerView
        lunchRecyclerView = findViewById(R.id.lunchView);
        LinearLayoutManager lunchLayoutManager = new LinearLayoutManager(this);
        lunchRecyclerView.setLayoutManager(lunchLayoutManager);
        // 初始化午餐食物数据，例如：
        List<FoodItem> lunchItems = new ArrayList<>();
        lunchItems.add(new FoodItem(R.drawable.image_rice,"食物1", "150g", "300卡路里"));
        lunchItems.add(new FoodItem(R.drawable.image_rice,"食物1", "150g", "300卡路里"));
        // ...
        FoodAdapter lunchAdapter = new FoodAdapter(lunchItems);
        lunchRecyclerView.setAdapter(lunchAdapter);

        // 初始化晚餐RecyclerView
        dinnerRecyclerView = findViewById(R.id.dinnerView);
        LinearLayoutManager dinnerLayoutManager = new LinearLayoutManager(this);
        dinnerRecyclerView.setLayoutManager(dinnerLayoutManager);
        // 初始化晚餐食物数据，例如：
        List<FoodItem> dinnerItems = new ArrayList<>();
        dinnerItems.add(new FoodItem(R.drawable.image_rice,"食物1", "150g", "300卡路里"));
        // ...
        FoodAdapter dinnerAdapter = new FoodAdapter(dinnerItems);
        dinnerRecyclerView.setAdapter(dinnerAdapter);

    }

    private String getDayOfWeekName(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "周日";
            case Calendar.MONDAY:
                return "周一";
            case Calendar.TUESDAY:
                return "周二";
            case Calendar.WEDNESDAY:
                return "周三";
            case Calendar.THURSDAY:
                return "周四";
            case Calendar.FRIDAY:
                return "周五";
            case Calendar.SATURDAY:
                return "周六";
            default:
                return "";
        }
    }
}