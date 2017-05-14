package com.riq.startactivityforresult.bundle_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.riq.startactivityforresult.R;

public class BActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        editText = (EditText) findViewById(R.id.et);
        /*
         * Bundle获取：
         * Intent intent = getIntent();
         * Bundle bundle = intent.getExtras();
         * int a = bundle.getInt("num");
         */
        Intent intent = getIntent();
        int a = intent.getIntExtra("num", 1);
        editText.setText(a + "");
    }

}
