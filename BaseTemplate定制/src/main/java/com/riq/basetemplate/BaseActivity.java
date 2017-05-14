package com.riq.basetemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
//    private SparseArray<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
//        views = new SparseArray<>();
//        initListener();
        initView();
        initData();
    }


    //通过id找到View
//    public <E extends View> E findView(int id) {
//        E view = (E) views.get(id);
//        if (view == null) {
//            view = (E) findViewById(id);
//            views.put(id, view);
//        }
//        return view;
//    }

    //View的点击事件
//    public <E extends View> void setOnClick(E view) {
//        view.setOnClickListener(this);
//    }

//    @Override
//    public void onClick(View v) {
//        processClick(v);
//    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

//    protected abstract void initListener();

//    protected abstract void processClick(View v);
}
