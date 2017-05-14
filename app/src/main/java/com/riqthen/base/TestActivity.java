package com.riqthen.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView textView = (TextView) findViewById(R.id.tv);
//        new Thread(runnable).start();

//        try {
//            while (true) {
//                Thread.sleep(1000);
//                textView.setText(sb.append("hello"));
//                Log.e("=====", "hello");
                StringBuffer sb = new StringBuffer(textView.getText().toString()+"1");
                Log.e("=====", String.valueOf(sb));
                textView.setText(sb.append(123));
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000);
                    Log.e("=============", "*****************************");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    };

}
