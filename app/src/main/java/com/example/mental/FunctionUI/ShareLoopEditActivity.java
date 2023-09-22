package com.example.mental.FunctionUI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.Adapter.PhotoAdapter;
import com.example.mental.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ShareLoopEditActivity extends AppCompatActivity {
    private Bitmap selectedImageBitmap;
    private static final int PICK_IMAGE_REQUEST = 1;
    private PhotoAdapter photoAdapter; // 声明一个类级别的成员变量来引用 PhotoAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareloopedit);
        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.ShareLoopEditHeaderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "分享圈编辑";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);

        // 选框渲染
        Spinner shareLoopEmotionSpinner = findViewById(R.id.edit_shareLoop_emotionSelect);
        String[] emotions = {"狂 喜", "开 ⼼", "还 ⾏", "不 爽", "糟 糕"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, emotions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shareLoopEmotionSpinner.setAdapter(adapter);
        // 在 ShareLoopEditActivity 的 onCreate 方法中为 RecyclerView 设置布局管理器
        RecyclerView photoRecyclerView = findViewById(R.id.edit_shareLoop_photoList);
        photoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 然后初始化 photoAdapter，确保在 onCreate 方法中正确初始化 photoAdapter
        photoAdapter = new PhotoAdapter(this, new ArrayList<Bitmap>());
        photoRecyclerView.setAdapter(photoAdapter);


        // 选择相册添加
        ImageView photoInsertImageView = findViewById(R.id.edit_shareLoop_photoInsert);
        photoInsertImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开相册的代码
                openGallery();
            }
        });


        // 渲染照片选项

    }
    private static final int REQUEST_IMAGE_GALLERY = 1;

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK && data != null) {
            // 获取选定的图片 URI
            Uri imageUri = data.getData();

            // 使用 URI 加载图片
            try {
                Bitmap originalBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                // 裁剪图片为1:1的大小
                Bitmap croppedBitmap = cropToSquare(originalBitmap);

                // 在活动或片段中调用 addPhoto 方法以添加新的照片
                byte[] croppedImageData = bitmapToByteArray(croppedBitmap);
                photoAdapter.addPhoto(croppedImageData);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 辅助方法：将图片裁剪为1:1的大小
    private Bitmap cropToSquare(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int size = Math.min(width, height);

        int x = (width - size) / 2;
        int y = (height - size) / 2;

        return Bitmap.createBitmap(bitmap, x, y, size, size);
    }

    // 辅助方法：将 Bitmap 转换为字节数组
    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }


}