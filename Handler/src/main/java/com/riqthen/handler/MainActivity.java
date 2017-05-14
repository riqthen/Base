package com.riqthen.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * 1.Handler的介绍
 * 2.Handler常用API
 * 3.Handler内部实现原理
 * 4.Handler内存泄露问题分析
 * 5.使用Handler实现App的闪屏页
 */
public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x1:
                    textView.setText("nnn");
                    break;
            }

        }
    };

    public void downloadClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //休眠1秒之后执行下面的操作
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                //在该方法也可以改变UI
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.setText("下载完成");
//                    }
//                });

//                handler.sendEmptyMessage(0x1);
//                handler.sendEmptyMessageAtTime(0x1, SystemClock.uptimeMillis() + 1000);    //在指定时间发送消息，相对于当前开机时间后1秒
                handler.sendEmptyMessageDelayed(0x1, 1000); //延迟1秒发送。与Thread.sleep(1000)等效
                /*
                等价于sendEmptyMessage(0x1)
                Message msg = handler.obtainMessage();  //获取一个消息对象
                msg.what = 0x1;
                msg.obj = "abc";    //要存储的任意类型的信息
                handler.sendMessage(msg);
                */
            }
        }
        ).start();
    }
}
