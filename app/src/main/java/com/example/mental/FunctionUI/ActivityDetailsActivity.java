package com.example.mental.FunctionUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.R;

public class ActivityDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitydetails);

        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.ActivityDetailsRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "活动信息";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);

        // 获取传递的数据
        Intent intent = getIntent();
        int activityImage = intent.getIntExtra("activityImage", 0); // 0 是默认值
        String activityName = intent.getStringExtra("activityName");
        String activityIntroduce = intent.getStringExtra("activityIntroduce");

        // 在页面上显示数据
        ImageView activityImageView = findViewById(R.id.details_activity_image);
        TextView activityNameTextView = findViewById(R.id.details_activity_title);
        TextView activityIntroduceTextView = findViewById(R.id.details_activity_context);

        activityNameTextView.setText(activityName);
        activityIntroduceTextView.setText(activityIntroduce);
        activityImageView.setImageResource(activityImage);
    }
}