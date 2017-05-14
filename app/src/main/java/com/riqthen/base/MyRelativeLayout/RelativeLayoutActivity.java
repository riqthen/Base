package com.riqthen.base.MyRelativeLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.riqthen.base.R;

public class RelativeLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);
        MyRelativeLayout rl = (MyRelativeLayout) findViewById(R.id.activity_relative_layout);
        rl.onClickListener = new MyRelativeLayout.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 自己的回调监听
            }
        };
    }
}
