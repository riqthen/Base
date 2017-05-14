package com.riq.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * 从splash点击注册跳转到注册界面，然后在注册界面点击注册完成，跳转到主界面，然后在主界面按返回时是直接退出程序的
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void main(View view) {
        startActivity(new Intent(this, MainActivity.class));

    }
}
