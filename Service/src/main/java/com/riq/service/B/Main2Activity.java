package com.riq.service.B;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.riq.service.R;

//bindService和unbindService方式启动关闭Service
//该情况下Service的生命周期：onCreate — onBind — onUnbind — onDestroy
public class Main2Activity extends AppCompatActivity {
    Intent intent;
    BindService.MyBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        intent = new Intent(this, BindService.class);
        intent.setAction("com.riq.service.SECOND_SERVICE");
    }

    //点击绑定Service
    public void bindService(View view) {
        //TODO 绑定Service
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    //点击解绑Service
    public void unbindService(View view) {
        //TODO 解绑Service
        unbindService(conn);
    }

    //点击获取状态
    public void getStatus(View view) {
        if (binder != null) {
            Log.e("----------------", "Service的count值为" + binder.getCount());
        }
    }

    //创建conn对象，作为bindService和unbindService的参数
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("----------------", "Service已连接");
            binder = (BindService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("----------------", "Service已断开");
        }
    };

}
