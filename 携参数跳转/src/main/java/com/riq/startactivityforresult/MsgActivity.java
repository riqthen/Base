package com.riq.startactivityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * getStringExtra没有默认值，参数只能是A中传递来的name
 */
public class MsgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        TextView tv = (TextView) findViewById(R.id.tv);
        Intent intent = getIntent();
        String name = intent.getStringExtra("msg");
        char sex = intent.getCharExtra("sex", '男');
        int weight = intent.getIntExtra("weight", 100);
        int age = intent.getIntExtra("age", 18);
        tv.setText(name + "  " + sex + "  " + age + "  " + weight);

    }
}
