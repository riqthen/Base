package com.riq.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * orientation属性，不影响布局，那么可以去掉？
 * <TableRow>节点中没有属性，所包含的组件不要求宽高
 */
public class TableLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout);
    }
}
