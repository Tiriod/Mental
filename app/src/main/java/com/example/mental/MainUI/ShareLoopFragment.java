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

import com.example.mental.Adapter.ShareLoopAdapter;
import com.example.mental.Definition.ShareLoopItem;
import com.example.mental.FunctionUI.ShareLoopEditActivity;
import com.example.mental.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShareLoopFragment extends Fragment {
    private RecyclerView recyclerView;
    private ShareLoopAdapter shareLoopAdapter;
    private List<ShareLoopItem> shareLoopItems; // 你的数据列表


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 初始化布局
        View view = inflater.inflate(R.layout.fragment_shareloop, container, false);
        // 初始化 RecyclerView
        recyclerView = view.findViewById(R.id.shareLoop_recycler_card);
        // 初始化数据
        shareLoopItems = new ArrayList<>();
        // 添加示例 ShareLoopItem 数据到 shareLoopItems 列表
        shareLoopItems.add(new ShareLoopItem(R.drawable.image_avatar, "时间间", R.drawable.icon_emotion_terrible, "今天去爬雁荡山, 爬一半下雨了，几个人在亭子里守候了大半辈子，终于是忍不住去买了雨衣，灰溜溜下山了", Arrays.asList(R.drawable.image_shareloop_3_1, R.drawable.image_shareloop_3_2, R.drawable.image_shareloop_3_4, R.drawable.image_shareloop_3_5), "2023-09-11"));
        shareLoopItems.add(new ShareLoopItem(R.drawable.image_avatar, "时间间", R.drawable.icon_emotion_unknown, "闲来无事看看楼盘", Arrays.asList(R.drawable.image_shareloop_2_1, R.drawable.image_shareloop_2_2, R.drawable.image_shareloop_2_3, R.drawable.image_shareloop_2_4, R.drawable.image_shareloop_3_3), "2023-09-10"));
        shareLoopItems.add(new ShareLoopItem(R.drawable.image_avatar, "时间间", R.drawable.icon_emotion_ecstasy, "今天出去爽歪歪，玩水玩起来，爽歪歪！！！", Arrays.asList(R.drawable.image_shareloop_4_1, R.drawable.image_shareloop_4_2, R.drawable.image_shareloop_4_3), "2023-08-12"));
        shareLoopItems.add(new ShareLoopItem(R.drawable.image_avatar_1, "佛怒火莲", R.drawable.icon_emotion_bad, "分享今日“美食”😒", Arrays.asList(R.drawable.image_shareloop_1_1, R.drawable.image_shareloop_1_2), "2023-08-11"));
        // 初始化适配器
        shareLoopAdapter = new ShareLoopAdapter(getActivity(), shareLoopItems);
        // 设置 RecyclerView 的布局管理器，你可以选择 LinearLayoutManager 或其他适当的布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        // 设置适配器
        recyclerView.setAdapter(shareLoopAdapter);

// 在 onCreateView 方法中找到 shareLoop_compile
        CardView compileCardView = view.findViewById(R.id.shareLoop_compile);

// 为 compileCardView 设置点击事件监听器
        compileCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在点击事件中执行跳转操作
                Intent intent = new Intent(getActivity(), ShareLoopEditActivity.class);
                startActivity(intent);
            }
        });


        // 返回根视图
        return view;
    }
}