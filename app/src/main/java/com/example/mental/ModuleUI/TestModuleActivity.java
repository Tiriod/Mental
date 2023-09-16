package com.example.mental.ModuleUI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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
        cardItemList.add(new TestCardItem(R.drawable.image_test_card, "1.针对上面的图片, 你觉得舒服还是不舒服?", "A.舒适", "B.不舒适", "C.无感"));
        cardItemList.add(new TestCardItem(R.drawable.image_test_card, "2.针对上面的图片, 你觉得舒服还是不舒服?", "A.舒适", "B.不舒适", "C.无感"));
        cardItemList.add(new TestCardItem(R.drawable.image_test_card, "3.针对上面的图片, 你觉得舒服还是不舒服?", "A.舒适", "B.不舒适", "C.无感"));
        cardItemList.add(new TestCardItem(R.drawable.image_test_card, "4.针对上面的图片, 你觉得舒服还是不舒服?", "A.舒适", "B.不舒适", "C.无感"));
        TestCardItemAdapter adapter = new TestCardItemAdapter(cardItemList);

        // 将 Adapter 设置给 RecyclerView
        testRecyclerView.setAdapter(adapter);

        // 设置 RecyclerView 的布局管理器，例如 LinearLayoutManager
        LinearLayoutManager testCardLayoutManager = new LinearLayoutManager(this);
        testRecyclerView.setLayoutManager(testCardLayoutManager);

    }
}