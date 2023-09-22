package com.example.mental.MainUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.UserCardAdapter;
import com.example.mental.Adapter.UserModeAdapter;
import com.example.mental.Definition.UserCardItem;
import com.example.mental.Definition.UserModeItem;
import com.example.mental.LoginUI.LoginActivity;
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
        userCardItems.add(new UserCardItem(R.drawable.image_avatar, "时间间", "18989431998"));
        // ... 添加更多用户卡片数据项
        // 初始化适配器并设置
        UserCardAdapter userCardAdapter = new UserCardAdapter(userCardItems, getContext());
        mineUserCardRecyclerView.setAdapter(userCardAdapter);


        // 创建用户功能模式数据列表
        List<UserModeItem> userModeItems = new ArrayList<>();
        userModeItems.add(new UserModeItem(R.drawable.icon_mine_personal, "个人"));
        userModeItems.add(new UserModeItem(R.drawable.icon_mine_information, "信息"));
        userModeItems.add(new UserModeItem(R.drawable.icon_mine_location, "位置"));

        // 初始化用户功能模式的 RecyclerView
        RecyclerView userModeRecyclerView = view.findViewById(R.id.mineUserMode);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        userModeRecyclerView.setLayoutManager(layoutManager);

        // 初始化用户功能模式的适配器并设置
        UserModeAdapter userModeAdapter = new UserModeAdapter(userModeItems, getContext());
        userModeRecyclerView.setAdapter(userModeAdapter);

        CardView button_logOut = view.findViewById(R.id.button_logOut);
        button_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), LoginActivity.class));
            }
        });


        return view;
    }
}
