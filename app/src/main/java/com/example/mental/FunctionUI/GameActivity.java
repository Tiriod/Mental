package com.example.mental.FunctionUI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.R;


public class GameActivity extends Activity {
    private WebView webView;
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
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
//                != PackageManager.PERMISSION_GRANTED) {
//            // 权限尚未授予，需要向用户请求权限。
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
//        } else {
//            // 权限已被授予，可以进行录音操作。
//
//        }


        // 配置 WebView 设置
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 启用 JavaScript 支持

//        webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//            }
//
//            @Override
//            public void onPermissionRequest(PermissionRequest request) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    //直接同意即可     deny是拒绝
//                    request.grant(request.getResources());
//                }
//            }
//        });
//        WebView.loadUrl(webUrl);
//        String url = "https://xiangyuecn.github.io/Recorder/";

        // 加载指定的网址
        String url = "https://egdw.gitee.io/escape-from-a-predicament/";
//        String url = "https://www.jianshu.com/p/b07d7567bfcd";
        webView.loadUrl(url);
    }



}