package com.example.mental.MainUI;

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

import com.example.mental.Adapter.BannerAdapter;
import com.example.mental.Adapter.FunctionAdapter;
import com.example.mental.Adapter.ModuleAdapter;
import com.example.mental.Definition.FunctionModule;
import com.example.mental.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ModuleAdapter.OnModuleClickListener {
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
        functionModules.add(new FunctionModule(R.drawable.icon_function_read, "心理探究"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_game, "心境迷航"));

        // 初始化功能模块 RecyclerView
        RecyclerView functionRecyclerView = view.findViewById(R.id.functionRecyclerView);
        LinearLayoutManager layoutManager_light = new GridLayoutManager(requireContext(), 2);
        functionRecyclerView.setLayoutManager(layoutManager_light);

        FunctionAdapter functionModuleAdapter = new FunctionAdapter(functionModules);
        functionRecyclerView.setAdapter(functionModuleAdapter);

        return view;
    }

    @Override
    public void onModuleClick(int position) {
        // Handle click event based on the module position
        switch (position) {
            case 0:
                // Handle click on the first module
                break;
            case 1:
                // Handle click on the second module
                break;
            case 2:
                // Handle click on the third module
                break;
            // Add more cases if needed for other modules
        }
    }
}
