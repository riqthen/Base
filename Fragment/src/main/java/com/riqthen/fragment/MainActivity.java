package com.riqthen.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * 显示两个fragment
 * 静态添加：在布局中加入fragment组件
 * 动态添加：在布局中添加FrameLayout容器组件
 */
public class MainActivity extends AppCompatActivity {
    Fragment titleFragment;
    Fragment contentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过FragmentManager获取Fragment实例
        FragmentManager fm = getSupportFragmentManager();
        titleFragment = fm.findFragmentById(R.id.frag_title);
        contentFragment = fm.findFragmentById(R.id.frag_content);
    }
}
