package com.riq.javaview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView;
    int[] images = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher_round};
    int currImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
        imageView = new ImageView(this);
        linearLayout.addView(imageView);
        imageView.setImageResource(R.mipmap.ic_launcher);
        handler.sendEmptyMessageDelayed(0x1, 500);
        Log.e("====", "发送消息");
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            currImage++;
            imageView.setImageResource(images[currImage % 2]);
            handler.sendEmptyMessageDelayed(0x1, 500);
            Log.e("--------", "执行");
        }
    };
}
