package com.example.mental.FunctionUI;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.R;

public class MeditationAudioActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button playButton;
    private boolean isPlaying = false;
    private ImageView audioImageView;
    private boolean shouldRotate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditationaudio);

        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.MeditationAudio);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "音频播放";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);

        // 根据获取传递的数据渲染的内容
        Intent intent = getIntent();
        String audioName = intent.getStringExtra("audioName");
        int playCount = intent.getIntExtra("playCount", 0); // 0 是默认值
        int audioImage = intent.getIntExtra("audioImage", 0); // 0 是默认值

        // 渲染数据
        TextView audioNameTextView = findViewById(R.id.meditationAudio_audioName);
        TextView playCountTextView = findViewById(R.id.meditationAudio_count);
        audioImageView = findViewById(R.id.meditationAudio_image);

        audioNameTextView.setText(audioName);
        playCountTextView.setText("播放量：" + playCount);
        audioImageView.setImageResource(audioImage);


        // 绑定音频播放按钮
        playButton = findViewById(R.id.meditationAudio_begin);
        mediaPlayer = MediaPlayer.create(this, R.raw.toxic); // 替换为你的音频文件

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    pauseAudio();
                } else {
                    playAudio();
                }
            }
        });
    }

    private void playAudio() {
        mediaPlayer.start();
        isPlaying = true;
        playButton.setText("暂停");
        shouldRotate = true;
        rotateImage();
    }

    private void pauseAudio() {
        mediaPlayer.pause();
        isPlaying = false;
        playButton.setText("播放");
        shouldRotate = false;
    }

    private void rotateImage() {
        final ObjectAnimator rotation = ObjectAnimator.ofFloat(audioImageView, "rotation", 0f, 360f);
        rotation.setDuration(2000); // 旋转一圈所需的时间（毫秒）
        rotation.setRepeatCount(ObjectAnimator.INFINITE); // 无限次重复
        rotation.setInterpolator(new LinearInterpolator()); // 线性插值器，使旋转平滑

        rotation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (!shouldRotate) {
                    rotation.cancel();
                }
            }
        });

        rotation.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}