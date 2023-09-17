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
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mental.Adapter.ActivityAdapter;
import com.example.mental.Adapter.BannerAdapter;
import com.example.mental.Adapter.FunctionAdapter;
import com.example.mental.Adapter.ModuleAdapter;
import com.example.mental.Definition.ActivityItem;
import com.example.mental.Definition.BannerItem;
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

public class HomeFragment extends Fragment implements ModuleAdapter.OnModuleClickListener, FunctionAdapter.OnFunctionClickListener {

    private ViewPager2 viewPager;
    private List<Integer> imageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initHorizontalRecyclerView(view);
        initBanner(view);
        initFunctionModules(view);
        initActivityList(view);
        return view;
    }

    private void initHorizontalRecyclerView(View view) {
        RecyclerView horizontalRecyclerView = view.findViewById(R.id.horizontalRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalRecyclerView.setLayoutManager(layoutManager);

        List<String> moduleNameList = Arrays.asList("测一测", "照一照", "说一说");
        List<String> moduleIntroduceList = Arrays.asList(
                "简单问题测出你的心理状态",
                "通过你的面部微表情看看你的心情",
                "通过你的语言语气来识别情绪"
        );

        // 添加 PagerSnapHelper
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(horizontalRecyclerView);

        ModuleAdapter adapter = new ModuleAdapter(moduleNameList, moduleIntroduceList, this);
        horizontalRecyclerView.setAdapter(adapter);
    }

    private void initBanner(View view) {
        imageList = new ArrayList<>();
        viewPager = view.findViewById(R.id.viewPager);
        BannerAdapter bannerAdapter = new BannerAdapter(imageList);
        viewPager.setAdapter(bannerAdapter);

        String bannerApiUrl = "http://your_api_endpoint_for_banners";
        ApiRequest.makeApiRequest(bannerApiUrl, new ApiRequest.ApiResponseListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(String response) {
                Log.d("API请求内容", response);
                Gson gson = new Gson();
                BannerItem[] bannerItems = gson.fromJson(response, BannerItem[].class);

                imageList.clear();

                for (BannerItem item : bannerItems) {
                    imageList.add(item.getBannerImageResourceId());
                }

                bannerAdapter.notifyDataSetChanged();

                if (!imageList.isEmpty()) {
                    final Handler handler = new Handler();
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            int currentItem = viewPager.getCurrentItem();
                            viewPager.setCurrentItem((currentItem + 1) % imageList.size(), true);
                            handler.postDelayed(this, 3000);
                        }
                    };
                    handler.postDelayed(runnable, 3000);
                }
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onError(String error) {
                Log.e("API请求内容", error);

                imageList.clear();
                imageList.add(R.drawable.image_banner_i);
                imageList.add(R.drawable.image_banner_ii);
                imageList.add(R.drawable.image_banner_iii);
                imageList.add(R.drawable.image_banner_iv);

                bannerAdapter.notifyDataSetChanged();

                if (!imageList.isEmpty()) {
                    final Handler handler = new Handler();
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            int currentItem = viewPager.getCurrentItem();
                            viewPager.setCurrentItem((currentItem + 1) % imageList.size(), true);
                            handler.postDelayed(this, 3000);
                        }
                    };
                    handler.postDelayed(runnable, 3000);
                }
            }
        });
    }

    private void initFunctionModules(View view) {
        List<FunctionModule> functionModules = new ArrayList<>();
        functionModules.add(new FunctionModule(R.drawable.icon_function_meditation, "心灵冥想"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_food, "心身滋养"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_note, "心绪记录"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_analyze, "心情解析"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_read, "心理探索"));
        functionModules.add(new FunctionModule(R.drawable.icon_function_game, "逃出困境"));

        RecyclerView functionRecyclerView = view.findViewById(R.id.functionRecyclerView);
        GridLayoutManager functionLayoutManager = new GridLayoutManager(requireContext(), 2);
        functionRecyclerView.setLayoutManager(functionLayoutManager);

        FunctionAdapter functionModuleAdapter = new FunctionAdapter(functionModules, this);
        functionRecyclerView.setAdapter(functionModuleAdapter);
    }

    private void initActivityList(View view) {
        final List<ActivityItem> activityList = new ArrayList<>();

        RecyclerView activityRecyclerView = view.findViewById(R.id.activityRecyclerView);
        LinearLayoutManager activityLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        activityRecyclerView.setLayoutManager(activityLayoutManager);

        ActivityAdapter activityAdapter = new ActivityAdapter(activityList);
        activityRecyclerView.setAdapter(activityAdapter);

        String activityURL = "http://jsonplaceholder.typicode.com/post";
        ApiRequest.makeApiRequest(activityURL, new ApiRequest.ApiResponseListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(String response) {
                Log.d("API请求内容", response);
                Gson gson = new Gson();
                ActivityItem[] items = gson.fromJson(response, ActivityItem[].class);

                activityList.addAll(Arrays.asList(items));
                activityAdapter.notifyDataSetChanged();
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onError(String error) {
                Log.e("API请求内容", error);

                ActivityItem[] defaultItems = {
                        new ActivityItem(R.drawable.image_test, "暂无活动信息", "暂无活动信息, 去看看其他的地方吧"),
                        new ActivityItem(R.drawable.image_activity_1, "2024心理月", "心理月活动在下北泽114514号开展, 下北泽是野兽先辈的住所，是年轻人的圣地"),
                        new ActivityItem(R.drawable.image_activity_1, "2025心理月", "心理月活动在下北泽114514号开展"),
                };

                activityList.addAll(Arrays.asList(defaultItems));
                activityAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onModuleClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(requireContext(), TestModuleActivity.class));
                break;
            case 1:
                startActivity(new Intent(requireContext(), CameraModuleActivity.class));
                break;
            case 2:
                startActivity(new Intent(requireContext(), TalkModuleActivity.class));
                break;
        }
    }

    @Override
    public void onFunctionClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(requireContext(), MeditationActivity.class));
                break;
            case 1:
                startActivity(new Intent(requireContext(), FoodActivity.class));
                break;
            case 2:
                startActivity(new Intent(requireContext(), NoteActivity.class));
                break;
            case 3:
                startActivity(new Intent(requireContext(), AnalyzeActivity.class));
                break;
            case 4:
                startActivity(new Intent(requireContext(), ReadActivity.class));
                break;
            case 5:
                startActivity(new Intent(requireContext(), GameActivity.class));
                break;
        }
    }
}
