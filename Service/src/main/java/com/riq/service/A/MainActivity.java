package com.riq.service.A;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.riq.service.R;

//startService和stopService方式启动关闭Service
//Service和访问者之间基本不存在太多关联，因此不能进行通信和数据交换。
//该情况下Service的生命周期：onCreate — onStartCommand — onDestroy
public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO 创建启动Service的Intent
        intent = new Intent(this, StartService.class);    //Android5.0中service的intent一定要显性声明,否则报错Service Intent must be explicit
        //TODO 为Intent设置Action属性
        intent.setAction("com.riq.service.FIRST_SERVICE");
    }


    public void startService(View view) {
        //TODO 开启服务
        startService(intent);
    }

    public void stopService(View view) {
        //TODO 停止服务
        stopService(intent);
    }


}
