package com.example.mental.FunctionUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mental.Adapter.AudioCardAdapter;
import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.Adapter.MeditationClassAdapter;
import com.example.mental.Adapter.MeditationIntroduceAdapter;
import com.example.mental.Definition.AudioCardItem;
import com.example.mental.R;

import java.util.ArrayList;
import java.util.List;

public class MeditationActivity extends AppCompatActivity implements MeditationClassAdapter.OnMeditationClassClickListener {

    private List<String> meditationIntroduces;
    private List<String> meditationClasses;
    private List<String> meditationContents;
    private RecyclerView meditationIntroduceRecyclerView;
    private MeditationIntroduceAdapter meditationIntroduceAdapter;
    private MeditationClassAdapter meditationClassAdapter;
    private RecyclerView audioCardRecyclerView;
    private AudioCardAdapter audioCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.MeditationHeaderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "心灵冥想";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);

        // 初始化冥想类别列表
        meditationClasses = new ArrayList<>();
        meditationClasses.add("脑波");
        meditationClasses.add("自然");
        meditationClasses.add("灵感");
        meditationClasses.add("生活");
        meditationClasses.add("动物");
        // 初始化冥想类别RecyclerView
        RecyclerView meditationClassRecyclerView = findViewById(R.id.MeditationChoice);
        LinearLayoutManager meditationClassLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        meditationClassRecyclerView.setLayoutManager(meditationClassLayoutManager);
        MeditationClassAdapter meditationClassAdapter = new MeditationClassAdapter(meditationClasses, this);
        meditationClassRecyclerView.setAdapter(meditationClassAdapter);
        // 设置冥想类别列表的点击监听器
        meditationClassAdapter.setOnMeditationClassClickListener(this);

        // 初始化冥想内容介绍列表
        meditationIntroduces = new ArrayList<>();
        meditationIntroduces.addAll(meditationClasses);
        // 添加更多冥想内容介绍数据
        meditationContents = new ArrayList<>();
        meditationContents.add("脑波冥想音频是通过特定频率和节奏的声音来激活大脑的不同频率，以帮助人们实现特定的冥想状态");
        // 初始化冥想内容介绍RecyclerView
        meditationIntroduceRecyclerView = findViewById(R.id.MeditationIntroduce);
        LinearLayoutManager meditationIntroduceLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        meditationIntroduceRecyclerView.setLayoutManager(meditationIntroduceLayoutManager);
        meditationIntroduceAdapter = new MeditationIntroduceAdapter(meditationIntroduces, meditationContents, this);
        meditationIntroduceRecyclerView.setAdapter(meditationIntroduceAdapter);

        // 设置冥想类别列表的点击监听器
        meditationClassAdapter.setOnMeditationClassClickListener(this);
        updateMeditationContents("脑波");

        // 初始化音频卡片列表
        List<AudioCardItem> audioCardItems = new ArrayList<>();
        audioCardItems.add(new AudioCardItem("音频1", 100));
        audioCardItems.add(new AudioCardItem("音频2", 200));
        audioCardItems.add(new AudioCardItem("音频3", 300));
        audioCardItems.add(new AudioCardItem("音频3", 300));
        audioCardItems.add(new AudioCardItem("音频3", 300));
        audioCardItems.add(new AudioCardItem("音频3", 300));

        // 初始化音频卡片RecyclerView
        audioCardRecyclerView = findViewById(R.id.MeditationItem);
        LinearLayoutManager audioCardLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        audioCardRecyclerView.setLayoutManager(audioCardLayoutManager);
        audioCardAdapter = new AudioCardAdapter(audioCardItems, this);
        audioCardRecyclerView.setAdapter(audioCardAdapter);
    }

    // 类别点击渲染
    @Override
    public void onMeditationClassClick(int position) {
        String selectedClass = meditationClasses.get(position);
        updateMeditationContents(selectedClass);
    }

    // 介绍卡片点击渲染
    private void updateMeditationContents(String selectedClass) {
        meditationContents.clear();
        meditationIntroduces.clear();
        // 根据点击的冥想类别，将对应的介绍内容加入到列表中
        switch (selectedClass) {
            case "脑波":
                meditationIntroduces.add("脑波");
                meditationContents.add("脑波冥想音频是通过特定频率和节奏的声音来激活大脑的不同频率，以帮助人们实现特定的冥想状态");
                break;
            case "自然":
                meditationIntroduces.add("自然");
                meditationContents.add("自然冥想音频通常包括自然界的声音，如海浪、鸟鸣、风声、森林等。这些音频可以创造出轻松、平静的环境，有助于减轻压力、焦虑和紧张情绪。自然冥想音频也可以帮助人们进入冥想状态，促进内心的平静和放松。");
                break;
            case "灵感":
                meditationIntroduces.add("灵感");
                meditationContents.add("灵感冥想音频通常包括激励和启发性的声音，如正面的宣言、名人演讲或哲理引用。这些音频可以激发积极的心态，增强动力和决心，帮助人们更加专注于目标，并找到内在的灵感和创造力。");
                break;
            case "生活":
                meditationIntroduces.add("生活");
                meditationContents.add("生活冥想音频是指与日常生活相关的冥想内容，例如情绪调节、应对挑战、自我接纳等。这些音频可以帮助人们更好地应对压力和情绪波动，培养积极的心态，并提高情绪智力。");
                break;
            case "动物":
                meditationIntroduces.add("动物");
                meditationContents.add("动物冥想音频通常包括与动物有关的声音或冥想内容，如动物叫声、动物形象的冥想指导等。这些音频可以帮助人们与自然界和动物建立联系，感受自然的力量和平衡，促进内心的宁静和愉悦感。");
                break;
        }
        // 通知介绍内容适配器刷新数据
        meditationIntroduceAdapter.updateMeditationContents(meditationContents);
    }
}

