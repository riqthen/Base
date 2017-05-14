package com.riqthen.handler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * 设置启动页，即在3s之后从MainActivity3跳转到MainActivity
 * 作用：
 * 1. 欢迎界面
 * 2. 初始化工作
 */
public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        }, 3000);
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //直接创建Handler对象也可以
    private final MyHandler handler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private WeakReference<MainActivity3> weakReference;

        public MyHandler(MainActivity3 activity3) {
            weakReference = new WeakReference<>(activity3);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}

/*
public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        handler.sendEmptyMessageDelayed(0x1, 3000);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x1) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        }
    };
}
*/