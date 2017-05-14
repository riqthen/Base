package com.riq.javaview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 创建布局
        LinearLayout linearLayout = new LinearLayout(this);
        //TODO 将view设置到界面
        setContentView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL); //设置LinearLayout方向
        //TODO 创建组件
        TextView textView = new TextView(this);
        textView.setText(R.string.app_name);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        //TODO 给组件设置宽高 ViewGroup.LayoutParams
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //TODO 将组件添加到布局中
        linearLayout.addView(textView);

    }
}
