package com.example.mental.LoginUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mental.MainActivity;
import com.example.mental.R;

public class LoginActivity extends AppCompatActivity {
    private TextView textView_login;
    private TextView textView_to_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView_login = findViewById(R.id.textView_login);
        textView_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 在这里添加登录逻辑，验证用户名和密码

                // 如果登录成功，跳转到首页
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
        textView_to_register = findViewById(R.id.textView_to_register);
        textView_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 如果登录成功，跳转到首页
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}