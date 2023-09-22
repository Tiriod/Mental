package com.example.mental.LoginUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mental.MainActivity;
import com.example.mental.R;

public class RegisterActivity extends AppCompatActivity {
    private TextView textView_to_login;
    private TextView textView_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textView_to_login = findViewById(R.id.textView_to_login);
        textView_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 返回到登陆页面
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        textView_register = findViewById(R.id.textView_register);
        textView_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 返回到注册页面
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}