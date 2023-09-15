package com.example.mental.ModuleUI;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.R;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CameraModuleActivity extends AppCompatActivity {
    // 相机显示区域
    private SurfaceView cameraSurfaceView;
    private Camera camera;
    // 相机是否打开
    private boolean isCameraOpen = false;
    // 关闭/打开相机按钮
    private Button btnCloseCamera;
    private Button btnReverseCamera;

    private TextView resultTextView;

    private Handler imgHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            int what = msg.what;
            switch (what){
                case 0:
                    resultTextView.setText("检测到的表情："+msg.obj);
                    break;
                case 1:
                    resultTextView.setText("未检测到人脸！");
                    break;
            }
        }
    };

    private OkHttpClient client = new OkHttpClient();

    private Runnable screenshotRunnable = new Runnable() {
        @Override
        public void run() {
            camera.takePicture(null, null, new Camera.PictureCallback() {
                @Override
                public void onPictureTaken(byte[] data, Camera camera) {
//                    camera.startFaceDetection();
                    camera.startPreview();
                    String imageData = Base64.encodeToString(data, Base64.DEFAULT);
                    RequestBody body = RequestBody.create(MediaType.parse("text/plain"), imageData);
                    Request request = new Request.Builder().url("http://192.168.1.106:5001/emoji").post(body).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            System.out.println("拍照失败："+e.getMessage());
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String result = response.body().string();
                            System.out.println("返回的结果："+ result);
                            if("no face".equals(result)){
                                imgHandler.sendEmptyMessage(0x1);
                                return;
                            }
                            Message message = imgHandler.obtainMessage(0x0, result);
                            imgHandler.sendMessage(message);
                        }
                    });

                }
            });
            imgHandler.postDelayed(screenshotRunnable,3000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameramodule);

        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.CameraHeaderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "照一照";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);

        // 初始化界面组件声明
        cameraSurfaceView = findViewById(R.id.cameraSurfaceView);
        // 结果文本绘制
        resultTextView = findViewById(R.id.resultTextView);
        btnCloseCamera = findViewById(R.id.btnCloseCamera);
        btnReverseCamera = findViewById(R.id.btnReverseCamera);
        // 请求相机权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 如果没有相机权限，则请求权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            // 如果已经有相机权限，则直接打开相机
            openCamera();
            isCameraOpen = true;
        }

        // 翻转相机按钮
        btnReverseCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCameraOpen) {
                    //相机打开的情况下才能翻转

                }
            }
        });

        // 设置关闭/打开相机按钮的点击事件监听器
        btnCloseCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCameraOpen) {
                    closeCamera();
                    isCameraOpen = false;
                } else {
                    btnCloseCamera.setText("关闭相机");
                    openCamera();
                    isCameraOpen = true;
                }
            }
        });

        // 设置预览组件框的点击事件监听器，触发自动对焦
        cameraSurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoFocusCamera();
            }
        });

        // 设置默认的示例文本
        resultTextView.setText("");
    }

    // 打开相机方法
    private void openCamera() {
        // 如果相机已经打开，则不需要再次打开相机
        if (isCameraOpen) {
            return;
        }

        try {
            camera = Camera.open();
            camera.setDisplayOrientation(90);
            camera.setFaceDetectionListener(new Camera.FaceDetectionListener() {
                @Override
                public void onFaceDetection(Camera.Face[] faces, Camera camera) {
                    System.out.println("识别到人脸："+faces.length);
//                    if (faces.length > 0){
//                    }
                }
            });
            Camera.Parameters parameters = camera.getParameters();
            // 设置自动对焦模式
            List<String> supportedFocusModes = parameters.getSupportedFocusModes();
            if (supportedFocusModes != null && supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            }
            camera.setParameters(parameters);

            // 设置预览的 SurfaceHolder
            SurfaceHolder holder = cameraSurfaceView.getHolder();
            holder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {
                        camera.setPreviewDisplay(holder);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(CameraModuleActivity.this, "设置预览失败", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    // 停止预览
                    if (camera != null) {
                        camera.stopPreview();
                    }

                    // 启动预览
//                    camera.startFaceDetection();
                    camera.startPreview();
                    imgHandler.post(screenshotRunnable);

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    // 释放摄像头资源
                    if (camera != null) {
                        camera.stopPreview();
                        camera.release();
                        camera = null;
                        imgHandler.removeCallbacks(screenshotRunnable);

                    }
                }
            });

            isCameraOpen = true; // 设置相机状态为打开
            btnCloseCamera.setText("关闭相机"); // 更新按钮文本
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "相机打开失败", Toast.LENGTH_SHORT).show();
        }
    }

    // 关闭相机方法
    private void closeCamera() {
        // 关闭相机预览
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        isCameraOpen = false; // 设置相机状态为关闭
        btnCloseCamera.setText("打开相机"); // 更新按钮文本
    }

    // 自动对焦方法
    private void autoFocusCamera() {
        if (camera != null) {
            try {
                camera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        // 对焦完成后的处理，可以根据需要进行操作
                        Log.d("CameraModuleActivity", "AutoFocus completed. Success: " + success);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 权限请求回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 用户授予了相机权限，打开相机
                openCamera();
            } else {
                // 用户拒绝了相机权限，可以给出一个提示或者其他处理
                Toast.makeText(this, "没有相机权限，无法打开相机", Toast.LENGTH_SHORT).show();
            }
        }
    }
}