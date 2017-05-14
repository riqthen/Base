package com.riq.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * 用于接收广播的组件，用于组件与组件之间进行通信，可以跨程序通信
 * 使用条件：
 * 1. 发送频率要低
 * 2. 数据量小可以使用。Intent携带的数据不能太大
 * 3. 操作一定要在5s内完成(超过10s会ANR)，耗时长要开启服务。所以可以用sleep，但是不能大于10s
 * 注册：
 * 静态注册(本程序)：Manifest，action的name即new intent的"action"
 * 动态注册(程序BroadcastReceiver2)：代码
 * 有序广播
 * 普通广播
 */
//静态注册广播(在Manifest中注册)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent("aaa");  //此为在Manifest中注册时给定的action
        intent.putExtra("message", "hello");
        // 发送有序广播
        sendOrderedBroadcast(intent, null);
//        sendBroadcast(intent);

    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO: BroadcastReceiver接收时调用此方法
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String msg = intent.getStringExtra("message");
            Log.e("========", "程序1接收到消息" + msg);     //输出 程序1接收到消息hello
            abortBroadcast();   //拦截广播
        }
    }
}
