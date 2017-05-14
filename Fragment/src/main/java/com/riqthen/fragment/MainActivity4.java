package com.riqthen.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.riqthen.fragment.four.AFragment;
import com.riqthen.fragment.four.BFragment;

/**
 * 效果：
 * 通过点击FragmentA上的按钮，改变FragmentB
 */

/*
 * fragment可以通过getActivity()方法访问Activity
 *  通过View view = getActivity().findViewById(R.id.resId);查找组件
 * Activity通过调用findFragmentById/Tag()获取fragment
 *  AFragment aFragment = (AFragment) getFragmentManager().findFragmentById(R.id.fragment_a);
 */
public class MainActivity4 extends AppCompatActivity implements AFragment.AListener {
    private AFragment aFragment;
    private BFragment bFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        aFragment = (AFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_a);
        bFragment = (BFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_b);
    }

    @Override
    public void changeValue(String value) {
        bFragment.changeText(value);    //在接口中调用BFragment中的改变文本方法
    }
}
