package com.example.mental.FunctionUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.R;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.Toast;


public class GameActivity extends Activity implements SensorEventListener {
    private SurfaceView mazeSurfaceView;
    private ImageView ballImageView;

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;

    private float ballX, ballY; // 小球的坐标
    private float ballSpeedX, ballSpeedY; // 小球在x和y方向上的速度
    private final float BALL_MAX_SPEED = 5.0f; // 小球的最大速度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.GameHeaderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "心境迷航";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);

        mazeSurfaceView = findViewById(R.id.mazeSurfaceView);
        ballImageView = findViewById(R.id.ballImageView);

        // 初始化传感器
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // 将小球的初始位置设置为屏幕的中心
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        int ballWidth = ballImageView.getWidth();
        int ballHeight = ballImageView.getHeight();
        ballX = (screenWidth - ballWidth) / 2;
        ballY = (screenHeight - ballHeight) / 2;
        updateBallPosition();
    }
    @Override
    protected void onResume() {
        super.onResume();
        // 注册传感器监听
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
    }


    @Override
    protected void onPause() {
        super.onPause();
        // 取消传感器监听
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        // 获取手机在x和y方向上的加速度值
        float ax = event.values[0];
        float ay = event.values[1];

        // 根据加速度值更新小球的速度
        ballSpeedX += ax * 0.1f;
        ballSpeedY += ay * 0.1f;

        // 限制小球速度在最大值范围内
        ballSpeedX = Math.max(-BALL_MAX_SPEED, Math.min(ballSpeedX, BALL_MAX_SPEED));
        ballSpeedY = Math.max(-BALL_MAX_SPEED, Math.min(ballSpeedY, BALL_MAX_SPEED));

        // 根据速度更新小球的位置
        ballX += ballSpeedX ;
        ballY += ballSpeedY;

        // 更新小球的位置
        updateBallPosition();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // 不处理
    }


    // 更新小球的位置，确保小球在屏幕范围内
    private void updateBallPosition() {
        // 获取屏幕的宽度和高度
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        // 获取小球的宽度和高度
        int ballWidth = ballImageView.getWidth();
        int ballHeight = ballImageView.getHeight();

        // 确保小球在屏幕范围内
        ballX = Math.max(0, Math.min(screenWidth - ballWidth, ballX));
        ballY = Math.max(0, Math.min(screenHeight - ballHeight, ballY));

        // 更新小球的位置
        ballImageView.setX(ballX);
        ballImageView.setY(ballY);
    }

}