package com.example.mental.ModuleUI;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.Adapter.TestCardItemAdapter;
import com.example.mental.Definition.TestCardItem;
import com.example.mental.R;

import java.util.ArrayList;
import java.util.List;

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

        // 选项卡片创建
        // 找到 RecyclerView
        RecyclerView testRecyclerView = findViewById(R.id.testRecyclerView);

        // 创建 CardItemAdapter，并将数据传递给它
        List<TestCardItem> cardItemList = new ArrayList<>(); // 创建你的卡片项数据
        cardItemList.add(new TestCardItem(R.drawable.image_test_1, "《星月夜》, 你觉得是否舒适?", "A.舒适", "B.不舒适", "C.无感"));
        cardItemList.add(new TestCardItem(R.drawable.image_test_2, "《最后的晚餐》, 你觉得舒服还是不舒服?", "A.舒适", "B.不舒适", "C.无感"));
        cardItemList.add(new TestCardItem(R.drawable.image_test_3, "《永恒的记忆》, 你觉得舒服还是不舒服?", "A.舒适", "B.不舒适", "C.无感"));
        cardItemList.add(new TestCardItem(R.drawable.image_test_4, "《格尔尼卡》, 你觉得舒服还是不舒服?", "A.舒适", "B.不舒适", "C.无感"));
        cardItemList.add(new TestCardItem(R.drawable.image_test_5, "《格尔尼卡》, 你觉得舒服还是不舒服?", "A.舒适", "B.不舒适", "C.无感"));
        cardItemList.add(new TestCardItem(R.drawable.image_test_6, "《戴珍珠耳环的女人》, 你觉得舒服还是不舒服?", "A.舒适", "B.不舒适", "C.无感"));
        TestCardItemAdapter adapter = new TestCardItemAdapter(cardItemList);

        // 将 Adapter 设置给 RecyclerView
        testRecyclerView.setAdapter(adapter);

        // 设置 RecyclerView 的布局管理器，例如 LinearLayoutManager
        LinearLayoutManager testCardLayoutManager = new LinearLayoutManager(this);
        testRecyclerView.setLayoutManager(testCardLayoutManager);
        TextView module_test_result = findViewById(R.id.module_test_result);

        CardView module_test_input = findViewById(R.id.module_test_input);
        module_test_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 使用Handler来延迟执行操作
                module_test_result.setText("加载中...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 在这里放置要延迟执行的代码
                        module_test_result.setText("您..看起来对对一切都不太有感觉");
                    }
                }, 2000); // 2000毫秒（2秒）的延迟
            }
        });


    }
}