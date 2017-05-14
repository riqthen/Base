package com.riq.launchmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * singleTop：A→B→C→D→D  D为singleTop，栈ABCD；如果B也为singleYop，A→B→C→D→B，则栈为ABCDB
 * singleTask：A→B→C→D→B  B为singleTask，栈AB,B以上的实例都被销毁
 *
 */
public class Main1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpTo2(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }
}
