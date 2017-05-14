package com.riq.service.B;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

//绑定本地Service
public class BindService extends Service {
    int count;
    boolean quit;

    //绑定Service时回调该方法
    //多次点击不会多次调用，即只绑定一次
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("-------------", "Service被绑定");
        return new MyBinder();
    }

    //Service创建时
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("-------------", "Service被创建");
        //创建线程来动态修改count状态值
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!quit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }

            }
        }).start();
    }

    //Service被断开连接时
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("-------------", "Service被断开");
        return true;
    }

    //Service被关闭时


    @Override
    public void onDestroy() {
        super.onDestroy();
        quit = true;
        Log.e("-------------", "Service被关闭");
    }

    //通常以继承的方式实现自己的IBinder对象
    class MyBinder extends Binder {
        int getCount() {
            //获取Service的运行状态count
            return count;
        }
    }
}
