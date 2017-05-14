package com.riq.broadcastreceiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * 先打开程序2，则2可以接受广播了，再打开程序1，即发送广播出去。则此时可以获取程序1传来的数据
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("==========", "onStart");
        IntentFilter filter = new IntentFilter("aaa");
        filter.setPriority(-1000);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("==========", "onDestroy");
        unregisterReceiver(receiver);
    }

    //动态注册广播，需要手动绑定广播，完成之后需要手动解绑。
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("==========", "程序2接收到消息" + intent.getStringExtra("message"));  //输出 程序2接收到消息hello
        }
    };
}
