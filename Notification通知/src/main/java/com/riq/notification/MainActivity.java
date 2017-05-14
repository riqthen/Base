package com.riq.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //发送普通通知
    public void sendNormalNotification(View v) {
//这是v4包的，用于16以上版本
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher); // 显示在通知上的图标(必须)
        builder.setTicker("显示通知条上的文字");    // 显示通知条上的文字
        builder.setContentTitle("大标题，一般写程序名称就行");
        builder.setContentText("内容");
        builder.setWhen(System.currentTimeMillis());    //发送的时间
        builder.setContentInfo("info"); //显示在右边，时间的下面
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);    //向通知添加声音、闪灯和振动效果

//点击事件
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0x1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
//        notification.flags = NotificationCompat.FLAG_AUTO_CANCEL;

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify((int) (Math.random() * 1000), notification);

    }

    //发送自定义通知
    public void sendDiyNotification(View v) {
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.layout_notification);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("自定义通知 ")
                .setContent(views)
                .build();
        views.setTextColor(R.id.tv, Color.argb(0x88, 0x18, 0x15, 0x91));
        manager.notify(1, notification);    //根据id确定是否为同一条通知，此处多次点击，则是更新通知
    }

    //不能清除别的程序的通知
    public void clearNotification(View v) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancelAll();
    }


}
