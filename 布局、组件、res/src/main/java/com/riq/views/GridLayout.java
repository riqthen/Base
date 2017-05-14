package com.riq.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/**
 * GridLayout网格布局：
 * 1.在X、Y轴方向上进行控件对齐
 * 2.支持一些支持自由编辑布局的工具
 * 3.可以多层布局嵌套，而是用其他布局可能有性能问题
 */

public class GridLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);
    }
}
