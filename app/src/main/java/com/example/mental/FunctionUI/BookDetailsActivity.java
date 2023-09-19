package com.example.mental.FunctionUI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.R;

import java.io.IOException;

public class BookDetailsActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    TextView titleTextView;
    TextView contentTextView;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdetails);

        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.BookDetailsHeaderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "阅读内容";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);

        // 获取传递到的数据信息
        // 获取传递到的数据信息
        Intent intent = getIntent();
        if (intent != null) {
            // 使用 intent.getStringExtra() 获取传递的字符串数据
            String bookTitle = intent.getStringExtra("bookTitle");
            String bookContent = intent.getStringExtra("bookContent");

            // 现在您可以在界面上显示这些数据，例如将它们设置为 TextView 的文本
            titleTextView = findViewById(R.id.bookDetails_title);
            contentTextView = findViewById(R.id.bookDetails_context);

            if (bookTitle != null) {
                titleTextView.setText(bookTitle);
            }

            if (bookContent != null) {
                contentTextView.setText(bookContent);
            }
        }
        CardView button_voice_sound = findViewById(R.id.button_voice_sound);
        TextView text_voice_state = findViewById(R.id.text_voice_state);
        text_voice_state.setTextColor(R.color.grey);
        text_voice_state.setText("未使用");
        button_voice_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 停止正在播放的音频
                text_voice_state.setTextColor(R.color.NS_blue);
                text_voice_state.setText("请求中");

                // 获取 contentTextView 的文本内容
                String content = contentTextView.getText().toString();

                // 寻找第一个换行符 \n 的位置
                int newlineIndex = content.indexOf("\n");

                // 截取文本内容，如果找到了换行符
                String truncatedContent;
                if (newlineIndex != -1) {
                    truncatedContent = content.substring(0, newlineIndex);
                } else {
                    truncatedContent = content; // 如果没有换行符，就使用整个文本内容
                }

                // 构建音频请求的 URL
                String audioUrl = "https://api.vvhan.com/api/song?txt=" + truncatedContent;

                // 创建新的 MediaPlayer
                mediaPlayer = new MediaPlayer();
                try {
                    text_voice_state.setTextColor(R.color.NS_blue);
                    text_voice_state.setText("已请求");
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(audioUrl);
                    mediaPlayer.prepareAsync(); // 异步准备播放

                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            // 准备好后开始播放
                            text_voice_state.setTextColor(R.color.NS_red);
                            text_voice_state.setText("播报中");
                            mp.start();
                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            // 播放完成后的操作
                            text_voice_state.setTextColor(R.color.grey);
                            text_voice_state.setText("未使用");
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // 在失去焦点时停止播放音频并释放相关资源
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


}