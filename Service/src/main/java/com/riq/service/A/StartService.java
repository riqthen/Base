package com.riq.service.A;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 1. 创建一个MyService extends Service
 * 2. 注册该Service，需要给定action，这个自定
 * 3. 在Activity中，获取Intent对象，并设定action为以上的action
 * //创建启动Service的Intent
 * Intent intent = new Intent(this, StartService.class);    //Android5.0中service的intent一定要显性声明,否则报错Service Intent must be explicit
 * //为Intent设置Action属性
 * intent.setAction("com.riq.service.START_SERVICE");
 */

/**
 * Service只会create一次，可以多次start，destroy只会可以再次create
 */
public class StartService extends Service {
    public StartService() {
    }

    //必须实现。返回一个IBinder对象，应用程序通过该对象与Service组件通信
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //创建时回调该方法
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("-----------", "create");
    }

    //启动时回调该方法
    //每次点击都会调用一次
    //onStart方法过期
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("-----------", "start");
        return START_STICKY;
    }

    //关闭之前回调该方法
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("-----------", "destroy");
    }

    //当该Service上绑定的所有客户端都断开连接时将会回调该方法
//    @Override
//    public boolean onUnbind(Intent intent) {
//        return super.onUnbind(intent);
//    }
}
