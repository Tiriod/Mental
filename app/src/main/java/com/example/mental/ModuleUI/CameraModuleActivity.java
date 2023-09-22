package com.example.mental.ModuleUI;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
    private SurfaceView cameraSurfaceView;
    private Camera camera;
    private boolean isCameraOpen = false;
    private CardView btnCloseCamera;
    private CardView btnReverseCamera;
    private TextView resultTextView;
    private int currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK; // 默认使用后置摄像头


    @SuppressLint("HandlerLeak")
    private final Handler imgHandler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(@NonNull Message msg) {
            int what = msg.what;
            switch (what) {
                case 0:
                    resultTextView.setText("表情检测结果: " + msg.obj);
                    break;
                case 1:
                    resultTextView.setText("表情检测结果: " + "未检测到人脸！");
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
                    camera.startPreview();
                    String imageData = Base64.encodeToString(data, Base64.DEFAULT);
                    RequestBody body = RequestBody.create(MediaType.parse("text/plain"), imageData);
                    Request request = new Request.Builder().url("http://192.168.1.106:5001/emoji").post(body).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.e("CameraModuleActivity", "拍照失败：" + e.getMessage());
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String result = response.body().string();
                            Log.d("CameraModuleActivity", "返回的结果：" + result);
                            if ("no face".equals(result)) {
                                imgHandler.sendEmptyMessage(0x1);
                                return;
                            }
                            Message message = imgHandler.obtainMessage(0x0, result);
                            imgHandler.sendMessage(message);
                        }
                    });
                }
            });
            imgHandler.postDelayed(screenshotRunnable, 3000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameramodule);

        initHeader();

        cameraSurfaceView = findViewById(R.id.cameraSurfaceView);
        resultTextView = findViewById(R.id.resultTextView);
        btnCloseCamera = findViewById(R.id.btnCloseCamera);
        btnReverseCamera = findViewById(R.id.btnReverseCamera);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            openCamera();
            isCameraOpen = true;
        }

        initCameraButtons();

        resultTextView.setText("表情检测结果: ");
    }

    private void initHeader() {
        RecyclerView headerRecyclerView = findViewById(R.id.CameraHeaderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "照一照";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);
    }

    private void initCameraButtons() {
        btnReverseCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCameraOpen) {
                    // 相机打开的情况下才能翻转
                }
            }
        });

        btnCloseCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCameraOpen) {
                    closeCamera();
                    isCameraOpen = false;
                } else {
                    TextView btnCloseCameraText = btnCloseCamera.findViewById(R.id.textViewBtnCloseCamera);
                    btnCloseCameraText.setText("关闭相机");
                    openCamera();
                    isCameraOpen = true;
                }
            }
        });

        cameraSurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoFocusCamera();
            }
        });
        btnReverseCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCamera();
            }
        });

    }


    private void openCamera() {
        if (isCameraOpen) {
            return;
        }

        try {
            camera = Camera.open();
            camera.setDisplayOrientation(90);
            camera.setFaceDetectionListener(new Camera.FaceDetectionListener() {
                @Override
                public void onFaceDetection(Camera.Face[] faces, Camera camera) {
                    Log.d("CameraModuleActivity", "识别到人脸：" + faces.length);
                }
            });
            Camera.Parameters parameters = camera.getParameters();
            List<String> supportedFocusModes = parameters.getSupportedFocusModes();
            if (supportedFocusModes != null && supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            }
            camera.setParameters(parameters);

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
                    if (camera != null) {
                        camera.stopPreview();
                    }

                    camera.startPreview();
                    imgHandler.post(screenshotRunnable);
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    if (camera != null) {
                        camera.stopPreview();
                        camera.release();
                        camera = null;
                        imgHandler.removeCallbacks(screenshotRunnable);
                    }
                }
            });

            isCameraOpen = true;
            TextView btnCloseCameraText = btnCloseCamera.findViewById(R.id.textViewBtnCloseCamera);
            btnCloseCameraText.setText("关闭相机");
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "相机打开失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void closeCamera() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        isCameraOpen = false;
        TextView btnCloseCameraText = btnCloseCamera.findViewById(R.id.textViewBtnCloseCamera);
        btnCloseCameraText.setText("打开相机");
    }

    private void autoFocusCamera() {
        if (camera != null) {
            try {
                camera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        Log.d("CameraModuleActivity", "AutoFocus completed. Success: " + success);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "没有相机权限，无法打开相机", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void switchCamera() {
        if (isCameraOpen) {
            // 关闭当前摄像头
            closeCamera();

            // 切换摄像头
            if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                currentCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT; // 切换到前置摄像头
            } else {
                currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK; // 切换到后置摄像头
            }

            // 打开新选择的摄像头
            openCamera();
        }
    }

}
