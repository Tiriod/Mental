package com.example.mental.MainUI;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.mental.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import pl.droidsonroids.gif.GifImageView;

public class AIConsultFragment extends Fragment {
    private OkHttpClient okHttpClient;
    private MediaRecorder mRecorder;
    private Vibrator vibrator;
    private String mAudioFile;
    private TextView result_textview;
    private WebView sceneView;
    private // 用于跟踪长按的开始时间
    long pressStartTime = 0;
    private boolean isRecording = false; // 用于跟踪录音状态

    private Handler rippleHandler;
    private boolean isLongPressing = false;
    private ImageView image_effect; // 您的涟漪效果 ImageView


    // 处理消息的 Handler
    @SuppressLint("HandlerLeak")
    private Handler msgHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            int what = msg.what;
            switch (what) {
                case 0:
                    result_textview.setText(msg.obj.toString());
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setDataSource("https://api.vvhan.com/api/song?txt=" + msg.obj.toString());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        sceneView.evaluateJavascript("javascript:start_talk()", null);
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                sceneView.evaluateJavascript("javascript:stop_talk()", null);
                                mp.release();
                            }
                        });
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 0x3:
                    sendMessageChat((String) msg.obj);
                    break;
            }
        }
    };

    private void sendMessageChat(String message) {
        String content = null;
        if (message != null) {
            content = message;
        } else {
            Log.d(TAG, "sendMessageChat: Sorry");
        }
        if ("".equals(content)) {
            return;
        }
        RequestBody requestBody = new FormBody.Builder()
                .add("words", content)
                .build();

        Request request = new Request.Builder()
                .url("http://10.0.2.145:5000/chat")
                .post(requestBody)
                .build();
        Call newCall = okHttpClient.newCall(request);
        newCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Message message = msgHandler.obtainMessage();
                message.what = 0;
                message.obj = response.body().string().toString();
                msgHandler.sendMessage(message);
            }
        });

    }


    // 录制音频设置
    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mRecorder.setAudioSamplingRate(16000);
        mRecorder.setAudioChannels(1);
        mRecorder.setOutputFile(mAudioFile);
        try {
            mRecorder.prepare();
            mRecorder.start(); // 在prepare之后启动录音
        } catch (IOException e) {
            Log.e("recorder", "prepare() failed");
        }
    }

    private String voice2text(String audioFile) {
        try {
            File file = new File(audioFile);
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[(int) file.length()];
            fis.read(b);
            fis.close();
            RequestBody fileBody = RequestBody.create(MediaType.parse("audio/wav"), b);
            //设置代理服务器账号密码

            MultipartBody body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("audio", "voice_file", fileBody)
                    .build();
            Request request = new Request.Builder()
                    .url("http://10.0.16.15:5001/voice")
                    .method("POST", body)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String result = response.body().string();
                    System.out.println();
                    //得到语音转文字的结果，发送给大模型
                    Message message = msgHandler.obtainMessage();
                    message.what = 0x3;
                    message.obj = result;
                    msgHandler.sendMessage(message);
                }
            });
        } catch (Exception e) {
        }
        return audioFile;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAudioFile = getActivity().getExternalFilesDir(null).getAbsolutePath() + "/record.wav";

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }
    }

    private View view;

    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 网络请求初始化
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //代理服务器的IP和端口号
        builder.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("175.24.153.185", 43213)));
        //代理的鉴权账号密码
        final String userName = "hongdeyan";
        final String password = "H378759617!";
        builder.proxyAuthenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                //设置代理服务器账号密码
                String credential = Credentials.basic(userName, password);
                return response.request().newBuilder()
                        .header("Proxy-Authorization", credential)
                        .build();
            }
        });
        okHttpClient = builder.build();



//        okHttpClient = new OkHttpClient();
        // 视图渲染
        view = inflater.inflate(R.layout.fragment_aiconsult, container, false);
        // WebView视图使用
        sceneView = view.findViewById(R.id.sceneView);
        sceneView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                handler.proceed("hongdeyan","H378759617!");
            }
        });
        WebSettings webSettings = sceneView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        // 启用 JavaScript 支持
        webSettings.setJavaScriptEnabled(true);
        sceneView.loadUrl("http://10.0.16.15:5500/untitled folder/examples/three.js/examples/webgl_animation_skinning_additive_blending copy.html");
        // 用于处理长按操作
        Handler longPressHandler = new Handler();
        ImageView image_voice = view.findViewById(R.id.image_voice);
        // 初始化 Vibrator 对象
        CardView cardView_voice_frame = view.findViewById(R.id.cardView_voice_frame);
        GifImageView image_voice_gift = view.findViewById(R.id.image_voice_gift);
        vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        rippleHandler = new Handler();
        image_effect = view.findViewById(R.id.image_effect);
        // 设置按住监听器
        image_voice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 记录长按的开始时间
                    pressStartTime = System.currentTimeMillis();
                    // 延迟0.5秒后执行长按操作
                    longPressHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 触发震动效果，持续 100 毫秒
                            vibrator.vibrate(100);
                            // 启动长按检查
                            isLongPressing = true;
                            startRecording();
                            // 在录音阶段时显示 image_voice_gift
                            setCardViewMargin(cardView_voice_frame, 2);
                            image_voice_gift.setVisibility(View.VISIBLE);
                        }
                    }, 500);
                } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    // 取消长按操作
                    longPressHandler.removeCallbacksAndMessages(null);
                    // 停止连续触发涟漪效果的操作
                    rippleHandler.removeCallbacksAndMessages(null);

                    // 检查长按时间是否小于1秒，如果小于0.5秒则不执行录音
                    long pressDuration = System.currentTimeMillis() - pressStartTime;
                    if (pressDuration >= 500) {
                        // 长按超过0.5秒，停止录音并处理录音文件
                        stopRecording();
                        isRecording = false; // 停止录音后更新状态
                        // 在非录音阶段时隐藏 image_voice_gift
                        image_voice_gift.setVisibility(View.GONE);
                        resetCardViewMargin(cardView_voice_frame);
                    } else {
                        // 在非录音阶段时隐藏 image_voice_gift
                        image_voice_gift.setVisibility(View.GONE);
                        resetCardViewMargin(cardView_voice_frame);
                    }
                }
                return false;
            }
        });
        image_voice.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // 长按操作
                // 在这里触发 image_effect 的点击事件
                image_effect.performClick();
                return true;
            }
        });
        result_textview = view.findViewById(R.id.text_AIConsult_result);
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        // 在切换Fragment时停止录音
        if (isRecording) {
            stopRecording();
            isRecording = false;
        }
    }

    private void stopRecording() {
        try {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;

            // 读取录音文件并转文字
            voice2text(mAudioFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 设置 cardView_voice_frame 的 margin
    private void setCardViewMargin(CardView cardView, int marginInDp) {
        int marginInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, marginInDp, getResources().getDisplayMetrics());
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) cardView.getLayoutParams();
        params.setMargins(marginInPx, marginInPx, marginInPx, marginInPx);
        cardView.requestLayout();
    }

    // 重置 cardView_voice_frame 的 margin
    private void resetCardViewMargin(CardView cardView) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) cardView.getLayoutParams();
        params.setMargins(0, 0, 0, 0);
        cardView.requestLayout();
    }
}
