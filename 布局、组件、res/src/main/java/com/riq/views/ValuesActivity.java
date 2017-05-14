package com.riq.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * 获取values文件夹中文件的内容
 */
public class ValuesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("===", getResources().getString(R.string.app_name));
        String[] car = getResources().getStringArray(R.array.car);
        Log.e("===car===", car.toString());
    }
}
