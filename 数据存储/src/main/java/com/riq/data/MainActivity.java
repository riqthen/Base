package com.riq.data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//保存的文件可以被共享
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpToFile(View view) {
        startActivity(new Intent(this, FileActivity.class));
    }

    public void jumpToSp(View view) {
        startActivity(new Intent(this, SPActivity.class));
    }

    public void jumpToSqLite(View view) {
        startActivity(new Intent(this, SQLiteActivity.class));
    }
}
