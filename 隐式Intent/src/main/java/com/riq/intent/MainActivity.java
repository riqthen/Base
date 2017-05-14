package com.riq.intent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

/**
 * 显示Intent：明确指定需要启动或者触发的组件的类名；
 * 隐式Intent：指定需要启动或者触发的组件应满足怎样的条件。
 * 被调用组件可以通过 IntentFilter 来声明自己所满足的条件，也就是声明自己到底能处理哪些隐式Intent。
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void action(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        startActivity(intent);
    }

    //打开某网站
    public void data(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
//        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }

    //拨打电话
    public void data2(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:13911541211"));
//        intent.setData(Uri.parse("tel:13911541211"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);

    }

    //分享该图片
    public void type(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "fbb.jpg")), "image/*");
        intent.setData(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "20161004_142628.jpg")));
        intent.setType("image/*");
        startActivity(intent);
    }
}
