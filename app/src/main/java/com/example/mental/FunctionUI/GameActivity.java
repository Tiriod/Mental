package com.example.mental.FunctionUI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.R;


public class GameActivity extends Activity {
    private WebView webView;
    private MediaPlayer mediaPlayer;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.GameHeaderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "逃出困境";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);
        // 获取 WebView 的引用
        webView = findViewById(R.id.game_escape);

        // 配置 WebView 设置
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 启用 JavaScript 支持

        // 加载指定的网址
        String url = "https://egdw.gitee.io/escape-from-a-predicament/";
        webView.loadUrl(url);

        // 初始化音乐播放器
        mediaPlayer = MediaPlayer.create(this, R.raw.funkytown);
        mediaPlayer.setLooping(true); // 设置循环播放

        // 开始播放音乐
        mediaPlayer.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 停止音乐播放并释放资源
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }



}