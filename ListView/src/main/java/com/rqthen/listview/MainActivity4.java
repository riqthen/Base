package com.rqthen.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * ListView
 * 实现图文列表,使用SimpleAdapter
 * *SimpleAdapter没有泛型，ArrayAdapter有泛型，通常为String
 * 步骤：
 * 1.在主布局中加入ListView组件
 * 2.创建item布局，给其中组件赋id
 * 3.在MainActivity.java中，创建需要填充到ListView中的数据数组
 * 4.创建ArrayList集合，泛型为Map<String, Object>
 * 5.用for循环将数据添加到Map<String, Object>中，再把map添加到list
 * 6.创建SimpleAdapter。设置adapter
 */

/**
 * SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
 * context：SimpleAdapter关联的View的运行环境
 * data：一个Map组成的List，在列表中的每个条目对应列表中的一行  List<Map<String, Object>> list = new ArrayList<>();
 * resource：自定义每项的布局    R.layout.item
 * from：HashMap中键的数组    new String[]{"img", "words"}
 * to：item布局中的对应于HashMap键的item布局中的组件id  new int[]{R.id.iv, R.id.tv}
 */
public class MainActivity4 extends AppCompatActivity {
    ListView listView;
    int[] img = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e};
    String[] words = {"a", "b", "c", "d", "e"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv);
        List<Map<String, Object>> list = new ArrayList<>();
//        item.put("img", R.mipmap.a);
//        item.put("words", "a");
//        list.add(item);
//
//        item = new HashMap<>();   //每一个item添加数据都需要新建，所以for循环的时候，也要循环创建Map
//        item.put("img", R.mipmap.b);
//        item.put("words", "b");
//        list.add(item);
        for (int i = 0; i < img.length; i++) {
            Map<String, Object> item = new HashMap<>();  //每个HashMap都是一行数据
            item.put("img", img[i]);
            item.put("words", words[i]);
            list.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this
                , list  //一个Map组成的List，在列表中的每个条目对应列表中的一行
                , R.layout.list_item_4  //自定义项的布局
                , new String[]{"img", "words"}      //HashMap中键的数组，必须与键的名字"img","words"对应
                , new int[]{R.id.iv, R.id.tv});     //item布局中的对应于HashMap键的组件id
        listView.setAdapter(adapter);
    }
}
