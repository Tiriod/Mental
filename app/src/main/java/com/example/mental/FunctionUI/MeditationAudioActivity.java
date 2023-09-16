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
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.R;

import java.util.Timer;
import java.util.TimerTask;

public class MeditationAudioActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button playButton;
    private boolean isPlaying = false;
    private ImageView audioImageView;
    private boolean shouldRotate = false;
    private SeekBar seekBar;
    private Timer timer;

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
        int audioUrl = intent.getIntExtra("audioUrl", 0); // 获取音频资源ID

        // 渲染数据
        TextView audioNameTextView = findViewById(R.id.meditationAudio_audioName);
        TextView playCountTextView = findViewById(R.id.meditationAudio_count);
        audioImageView = findViewById(R.id.meditationAudio_image);

        audioNameTextView.setText(audioName);
        playCountTextView.setText("播放量：" + playCount);
        audioImageView.setImageResource(audioImage);


        // 绑定音频播放按钮
        playButton = findViewById(R.id.meditationAudio_begin);
        mediaPlayer = MediaPlayer.create(this, audioUrl); // 替换为你的音频文件

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

        // 音频进度条
        // 找到SeekBar视图
        seekBar = findViewById(R.id.meditationAudio_seekBar);

        // 设置SeekBar的最大值（音频总时长）
        seekBar.setMax(mediaPlayer.getDuration());

        // 设置SeekBar的拖动事件监听器
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    // 用户手动拖动SeekBar，设置音频播放位置
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 用户开始拖动SeekBar时的回调（可以暂停计时器等）
                // 在这里添加你的逻辑
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 用户停止拖动SeekBar时的回调（可以恢复计时器等）
                // 在这里添加你的逻辑
            }
        });
        // 初始化计时器，定期更新SeekBar的进度
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (mediaPlayer != null && isPlaying) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
            }
        }, 0, 1000); // 每秒更新一次SeekBar
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
        // 取消计时器
        if (timer != null) {
            timer.cancel();
            timer.purge(); // 清除计时器的任务队列
            timer = null; // 清空计时器引用
        }
    }
}