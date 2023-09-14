package com.example.mental.MainUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.ShareLoopAdapter;
import com.example.mental.Definition.ShareLoopItem;
import com.example.mental.R;

import java.util.ArrayList;
import java.util.List;

public class ShareLoopFragment extends Fragment {
    private RecyclerView recyclerView;
    private ShareLoopAdapter shareLoopAdapter;
    private List<ShareLoopItem> shareLoopItems; // 你的数据列表


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 初始化布局
        View rootView = inflater.inflate(R.layout.fragment_shareloop, container, false);
        // 初始化 RecyclerView
        recyclerView = rootView.findViewById(R.id.shareLoop_recycler_card);
        // 初始化数据
        shareLoopItems = new ArrayList<>();
        // 添加示例 ShareLoopItem 数据到 shareLoopItems 列表
        shareLoopItems.add(new ShareLoopItem(R.drawable.image_activity_1, "时间间", R.drawable.icon_emotion_happy, "文本内容", R.drawable.image_activity_1,"2023-09-14"));
        shareLoopItems.add(new ShareLoopItem(R.drawable.image_activity_1, "时间间", R.drawable.icon_emotion_unknown, "文本内容", R.drawable.image_activity_1,"2023-09-14"));
        shareLoopItems.add(new ShareLoopItem(R.drawable.image_activity_1, "时间间", R.drawable.icon_emotion_bad, "文本内容", R.drawable.image_activity_1,"2023-09-14"));
        shareLoopItems.add(new ShareLoopItem(R.drawable.image_activity_1, "时间间", R.drawable.icon_emotion_ecstasy, "文本内容", R.drawable.image_activity_1,"2023-09-14"));
        // 初始化适配器
        shareLoopAdapter = new ShareLoopAdapter(getActivity(), shareLoopItems);
        // 设置 RecyclerView 的布局管理器，你可以选择 LinearLayoutManager 或其他适当的布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        // 设置适配器
        recyclerView.setAdapter(shareLoopAdapter);



        // 返回根视图
        return rootView;
    }
}