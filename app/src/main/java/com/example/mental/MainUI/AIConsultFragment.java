package com.example.mental.MainUI;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.load.model.Model;
import com.bumptech.glide.load.model.ModelLoader;
import com.example.mental.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AIConsultFragment extends Fragment {
    private OkHttpClient okHttpClient;
    private MediaRecorder mRecorder;
    private String mAudioFile;
    private EditText talk_editText;
    private TextView result_textview;
    private WebView sceneView;
    private Handler msgHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            int what = msg.what;
            switch (what) {
                case 0:
                    talk_editText.setText("");
                    result_textview.setText(msg.obj.toString());
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setDataSource("https://api.vvhan.com/api/song?txt=" + msg.obj.toString());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        sceneView.evaluateJavascript("javascript:start_talk()",null);
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                sceneView.evaluateJavascript("javascript:stop_talk()",null);
                                mp.release();
                            }
                        });
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        }
    };

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
        } catch (IOException e) {
            Log.e("recorder", "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;

        // 读取录音文件并转文字
        voice2text(mAudioFile);
    }


    private void voice2text(String audioFile){

        try {
            File file = new File(audioFile);
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[(int) file.length()];

            fis.read(b);
            fis.close();


            RequestBody fileBody = RequestBody.create(MediaType.parse("audio/wav"), b);

            MultipartBody body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("audio", "voice_file", fileBody)
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.1.106:5001/voice")
                    .method("POST", body)
//                                .addHeader("Content-Type", "audio/pcm;rate=16000")
//                                .addHeader("Accept", "application/json")
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    System.out.println(response.body().string());
                }
            });

        }catch (Exception e){

        }



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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        okHttpClient = new OkHttpClient();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_aiconsult, container, false);

        sceneView = view.findViewById(R.id.sceneView);


        WebSettings webSettings = sceneView.getSettings();

        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        // 获取缓存目录


        sceneView.loadUrl("https://egdw.gitee.io/mental_model/examples/three.js/examples/webgl_animation_skinning_additive_blending%20copy.html");




        Button recodeBtn = view.findViewById(R.id.recode_btn);
        recodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecording();

                // 录音10秒后停止
                new android.os.Handler().postDelayed(
                        () -> stopRecording(), 5000);
            }
        });

        talk_editText = view.findViewById(R.id.talk_editText);
        result_textview = view.findViewById(R.id.result_textview);
        Button submitBtn = view.findViewById(R.id.send_btn);
        submitBtn.setOnClickListener(view1 -> {
            String content = talk_editText.getText().toString();
            if ("".equals(content)){
                return;
            }
            RequestBody requestBody = new FormBody.Builder()
                    .add("words",content)
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


        });


        return view;
    }
}