package com.example.mental.FunctionUI;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.R;

public class MeditationAudioActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button playButton;
    private boolean isPlaying = false;

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

        playButton = findViewById(R.id.meditation_begin_audio);
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
    }

    private void pauseAudio() {
        mediaPlayer.pause();
        isPlaying = false;
        playButton.setText("播放");
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