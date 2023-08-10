package com.example.mental.MainUI;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mental.Adapter.ActivityAdapter;
import com.example.mental.Adapter.BannerAdapter;
import com.example.mental.Adapter.FunctionAdapter;
import com.example.mental.Adapter.ModuleAdapter;
import com.example.mental.Definition.ActivityItem;
import com.example.mental.Definition.FunctionModule;
import com.example.mental.FunctionUI.AnalyzeActivity;
import com.example.mental.FunctionUI.FoodActivity;
import com.example.mental.FunctionUI.GameActivity;
import com.example.mental.FunctionUI.MeditationActivity;
import com.example.mental.FunctionUI.NoteActivity;
import com.example.mental.FunctionUI.ReadActivity;
import com.example.mental.ModuleUI.CameraModuleActivity;
import com.example.mental.ModuleUI.TalkModuleActivity;
import com.example.mental.ModuleUI.TestModuleActivity;
import com.example.mental.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ModuleAdapter.OnModuleClickListener, FunctionAdapter.OnFunctionClickListener {
    //
    private ViewPager2 viewPager;
    private List<Integer> imageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // RecyclerView 注册
        RecyclerView horizontalRecyclerView = view.findViewById(R.id.horizontalRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalRecyclerView.setLayoutManager(layoutManager);

        // 1.主题模块:初始化
        List<String> moduleNameList = new ArrayList<>();
        List<String> moduleIntroduceList = new ArrayList<>();
        // 1.主题模块:添加功能名称
        moduleNameList.add("测一测");
        moduleNameList.add("照一照");
        moduleNameList.add("说一说");
        // 1.主题模块:添加功能介绍
        moduleIntroduceList.add("简单问题测出你的心理状态");
        moduleIntroduceList.add("通过你的面部微表情看看你的心情");
        moduleIntroduceList.add("通过你的语言语气来识别情绪");
        ModuleAdapter adapter = new ModuleAdapter(moduleNameList, moduleIntroduceList, this);
        horizontalRecyclerView.setAdapter(adapter);

        // 2.轮播图模块:初始化
        imageList = new ArrayList<>();
        imageList.add(R.drawable.image_banner_1);
        imageList.add(R.drawable.image_banner_2);
        imageList.add(R.drawable.image_banner_3);
        // 2.轮播图模块:ViewPager2
        viewPager = view.findViewById(R.id.viewPager);
        BannerAdapter bannerAdapter = new BannerAdapter(imageList);
        viewPager.setAdapter(bannerAdapter);
        // 2.轮播图模块:设置自动轮播
        if (!imageList.isEmpty()) {
            // 在轮播图图片数据不为空时执行
            final Handler handler = new Handler();
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    int currentItem = viewPager.getCurrentItem();
                    viewPager.setCurrentItem((currentItem + 1) % imageList.size(), true);
                    handler.postDelayed(this, 5000);
                }
            };
            handler.postDelayed(runnable, 5000);
        }

        // 3.子功能模块:初始化
        List<FunctionModule> functionModules = new ArrayList<>();
        functionModules.add(new FunctionModule(R.drawable.icon_function_meditation, "心灵冥想"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_food, "心身滋养"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_note, "心绪记录"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_analyze, "心情解析"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_read, "心理探索"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_game, "心境迷航"));

        // 3.子功能模块:初始化功能模块 RecyclerView
        RecyclerView functionRecyclerView = view.findViewById(R.id.functionRecyclerView);
        GridLayoutManager functionLayoutManager = new GridLayoutManager(requireContext(), 2);
        functionRecyclerView.setLayoutManager(functionLayoutManager);

        // 将点击监听器传递给 FunctionAdapter
        FunctionAdapter functionModuleAdapter = new FunctionAdapter(functionModules, this);
        functionRecyclerView.setAdapter(functionModuleAdapter);


        // 4.活动栏模块:初始化
        // 初始化活动数据列表
        List<ActivityItem> activityList = new ArrayList<>();
        activityList.add(new ActivityItem(R.drawable.image_activity_1));

        // 初始化活动栏 RecyclerView
        RecyclerView activityRecyclerView = view.findViewById(R.id.activityRecyclerView);
        LinearLayoutManager activityLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        activityRecyclerView.setLayoutManager(activityLayoutManager);

        ActivityAdapter activityAdapter = new ActivityAdapter(activityList);
        activityRecyclerView.setAdapter(activityAdapter);
        return view;
    }


    // 1.主题模块:点击跳转页面事件
    @Override
    public void onModuleClick(int position) {
        // 根据模块位置处理点击事件
        switch (position) {
            case 0:
                // "测一测"模块: 页面跳转
                startActivity(new Intent(requireContext(), TestModuleActivity.class));
                break;
            case 1:
                // "照一照"模块: 页面跳转
                startActivity(new Intent(requireContext(), CameraModuleActivity.class));
                break;
            case 2:
                // "说一说"模块: 页面跳转
                startActivity(new Intent(requireContext(), TalkModuleActivity.class));
                break;
            // Add more cases if needed for other modules
        }
    }
    @Override
    public void onFunctionClick(int position) {
        // 根据子功能模块位置处理点击事件
        switch (position) {
            case 0:
                // "心灵冥想"模块: 页面跳转
                startActivity(new Intent(requireContext(), MeditationActivity.class));
                break;
            case 1:
                // "心身滋养"模块: 页面跳转
                startActivity(new Intent(requireContext(), FoodActivity.class));
                break;
            case 2:
                // "心绪记录"模块: 页面跳转
                startActivity(new Intent(requireContext(), NoteActivity.class));
                break;
            case 3:
                // "心情解析"模块: 页面跳转
                startActivity(new Intent(requireContext(), AnalyzeActivity.class));
                break;
            case 4:
                // "心理探究"模块: 页面跳转
                startActivity(new Intent(requireContext(), ReadActivity.class));
                break;
            case 5:
                // "心境迷航"模块: 页面跳转
                startActivity(new Intent(requireContext(), GameActivity.class));
                break;
            // Add more cases if needed for other function modules
        }
    }
}
