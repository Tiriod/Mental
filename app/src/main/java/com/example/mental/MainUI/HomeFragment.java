package com.example.mental.MainUI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
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
import com.example.mental.Utils.ApiRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;

public class HomeFragment extends Fragment implements ModuleAdapter.OnModuleClickListener, FunctionAdapter.OnFunctionClickListener {
    //
    private ViewPager2 viewPager;
    private List<Integer> imageList;
    private OkHttpClient client = new OkHttpClient();

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
        // 2.轮播图模块:
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
        functionModules.add(new FunctionModule(R.drawable.icon_function_game, "逃出困境"));

        // 3.子功能模块:初始化
        RecyclerView functionRecyclerView = view.findViewById(R.id.functionRecyclerView);

        GridLayoutManager functionLayoutManager = new GridLayoutManager(requireContext(), 2);
        functionRecyclerView.setLayoutManager(functionLayoutManager);
        // 将点击监听器传递给 functionModuleAdapter
        FunctionAdapter functionModuleAdapter = new FunctionAdapter(functionModules, this);
        functionRecyclerView.setAdapter(functionModuleAdapter);


        // 4.活动栏模块:初始化
        final List<ActivityItem>[] activityList = new List[]{new ArrayList<>()};
        // 活动栏渲染视图初始化
        RecyclerView activityRecyclerView = view.findViewById(R.id.activityRecyclerView);
        LinearLayoutManager activityLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        activityRecyclerView.setLayoutManager(activityLayoutManager);
        // 活动信息适配器初始化
        ActivityAdapter activityAdapter = new ActivityAdapter(activityList[0]);
        activityRecyclerView.setAdapter(activityAdapter);
        // 活动内容请求地址代码
        String activityURL = "http://jsonplaceholder.typicode.com/post";
        // 使用API请求活动栏信息内容
        ApiRequest.makeApiRequest(activityURL, new ApiRequest.ApiResponseListener() {


            // 活动API地址请求成功处理
            @SuppressLint("NotifyDataSetChanged")
            @Override

            public void onSuccess(String response) {
                // 处理成功的响应
                Log.d("API请求内容", response);
                Gson gson = new Gson();
                ActivityItem[] items = gson.fromJson(response, ActivityItem[].class);
                // 将解析后的数据添加到 activityList 中
                activityList[0].addAll(Arrays.asList(items));
                // 刷新适配器，以便在设置默认数据后更新视图
                activityAdapter.notifyDataSetChanged();

            }

            // 活动API地址请求失败处理
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onError(String error) {
                // 处理错误的响应
                Log.e("API请求内容", error);

                // 在这里设置默认结果
                ActivityItem[] defaultItems = {
                        new ActivityItem(R.drawable.image_test, "暂无活动信息", "暂无活动信息, 去看看其他的地方吧"),
                        new ActivityItem(R.drawable.image_activity_1, "2024心理月", "心理月活动在下北泽114514号开展"),
                        new ActivityItem(R.drawable.image_activity_1, "2025心理月", "心理月活动在下北泽114514号开展"),
                };

                // 将解析后的数据添加到 activityList 中
                activityList[0].addAll(Arrays.asList(defaultItems));
                // 刷新适配器，以便在设置默认数据后更新视图
                activityAdapter.notifyDataSetChanged();
            }
        });



        // 最终结果页面渲染
        return view;
    }


    // 点击跳转主题模块页面
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
        }
    }

    // 点击跳转功能模块页面
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
                // "逃出困境"模块: 页面跳转
                startActivity(new Intent(requireContext(), GameActivity.class));
                break;
        }
    }

}
