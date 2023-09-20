package com.example.mental.ModuleUI;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.R;
import com.panoramagl.PLImage;
import com.panoramagl.PLManager;
import com.panoramagl.PLSphericalPanorama;
import com.panoramagl.utils.PLUtils;

public class LookModuleActivity extends AppCompatActivity {
    private PLManager plManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lookmodule);
        // 头部标签的创建:初始化 RecyclerView
        RecyclerView headerRecyclerView = findViewById(R.id.TalkHeaderRecyclerView);

        // 设置布局管理器，垂直方向
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);

        // 初始化头部标签的数据，设置 Adapter
        String headerText = "看一看"; // 根据需要设置头部标签的文字内容
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);


        plManager = new PLManager(this);
        View view = findViewById(R.id.look_look);
        plManager.setContentView((ViewGroup) view);
        plManager.onCreate();
        PLSphericalPanorama panorama = new PLSphericalPanorama();
        panorama.setImage(new PLImage(PLUtils.getBitmap(this, R.drawable.image_vr_yzf), false));
        plManager.setPanorama(panorama);
        plManager.setAccelerometerEnabled(true);
        plManager.setAccelerometerSensitivity(3f);
        plManager.setAccelerometerUpDownEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        plManager.onResume();
    }

    @Override
    protected void onPause() {
        plManager.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        plManager.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return plManager.onTouchEvent(event);
    }
}