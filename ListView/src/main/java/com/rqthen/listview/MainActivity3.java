package com.rqthen.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 单选(选择居住城市)和多选模式(选择5个爱好)，可以基于ListView组件创建的方式或者继承ListActivity方式
 * 此处以方式1，构建布局文件为例
 */
public class MainActivity3 extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv);
        String[] cities = getResources().getStringArray(R.array.citys);   //用该方法获取数组资源文件的内容
        //单选模式
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, cities);
//        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //多选模式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, cities);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);  //这句话不加则不会勾选上，只是会是可选的界面，来自于适配器中设置的simple_list_item_multiple_choice
        listView.setAdapter(adapter);

    }
}
