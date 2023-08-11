package com.example.mental.MainUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.UserCardAdapter;
import com.example.mental.Adapter.UserModeAdapter;
import com.example.mental.Definition.UserCardItem;
import com.example.mental.Definition.UserModeItem;
import com.example.mental.R;

import java.util.ArrayList;
import java.util.List;


public class MineFragment extends Fragment {

    private RecyclerView mineUserCardRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        // 初始化RecyclerView
        mineUserCardRecyclerView = view.findViewById(R.id.mineUserCard);
        LinearLayoutManager userCardLayoutManager = new LinearLayoutManager(getContext());
        mineUserCardRecyclerView.setLayoutManager(userCardLayoutManager);
        // 创建用户卡片数据列表
        List<UserCardItem> userCardItems = new ArrayList<>();
        userCardItems.add(new UserCardItem(R.drawable.image_activity_1, "用户名1", "用户ID1"));
        // ... 添加更多用户卡片数据项
        // 初始化适配器并设置
        UserCardAdapter userCardAdapter = new UserCardAdapter(userCardItems, getContext());
        mineUserCardRecyclerView.setAdapter(userCardAdapter);


        // 创建用户功能模式数据列表
        List<UserModeItem> userModeItems = new ArrayList<>();
        userModeItems.add(new UserModeItem(R.drawable.icon_emotion_happy, "个人"));
        userModeItems.add(new UserModeItem(R.drawable.icon_emotion_happy, "信息"));
        userModeItems.add(new UserModeItem(R.drawable.icon_emotion_happy, "位置"));

        // 初始化用户功能模式的 RecyclerView
        RecyclerView userModeRecyclerView = view.findViewById(R.id.mineUserMode);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        userModeRecyclerView.setLayoutManager(layoutManager);

        // 初始化用户功能模式的适配器并设置
        UserModeAdapter userModeAdapter = new UserModeAdapter(userModeItems, getContext());
        userModeRecyclerView.setAdapter(userModeAdapter);


        return view;
    }
}
