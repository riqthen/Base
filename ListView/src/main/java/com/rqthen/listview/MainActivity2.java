package com.rqthen.listview;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 继承ListActivity实现ListView（不常用，局限多）
 * 不需要设置布局，使用
 * 点击事件是onListItemClick()
 */
public class MainActivity2 extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.citys, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);    //这两句话即设置内容和布局
    }

    //点击事件
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
    }


}
