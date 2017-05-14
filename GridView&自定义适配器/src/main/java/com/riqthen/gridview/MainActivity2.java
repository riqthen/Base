package com.riqthen.gridview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 展示图文（即构建每个项目的布局）
 */

public class MainActivity2 extends AppCompatActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gridView = (GridView) findViewById(R.id.gv2);
        gridView.setAdapter(new MyAdapter(this));
//        gridView.setOnItemClickListener();        //设置组件的点击事件
//        gridView.setOnItemLongClickListener();    //设置组件的长按点击事件
    }


    //设置适配器
    //static静态类相当于外部类，优先选择使用静态内部类
    private static class MyAdapter extends BaseAdapter {
        private Context context;

       MyAdapter(Context context) {
            this.context = context;
        }   //当需要上下文而不能用this指定的时候，用该步骤构建context

        int[] images = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e, R.mipmap.z1, R.mipmap.z2, R.mipmap.butterfly, R.mipmap.file};
        String[] imageNames = {"a", "b", "c", "d", "e", "z1", "z2", "butterfly", "file"};

        @Override
        public int getCount() { //数组元素数目，即数组长度
            return imageNames.length;//images.length都行，都是9
        }

        @Override
        public Object getItem(int position) {   //数组元素
            return images[position];    //用imageNames[position]也可
        }

        @Override
        public long getItemId(int position) {
            return position;    //此处不变
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) { //此处需要创建一个布局文件
            //将item.xml加载
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.item, null);
            //找到item.xml中的组件
            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
            TextView textView = (TextView) view.findViewById(R.id.tv);
            //给找到的组件设置内容
            imageView.setImageResource(images[position]);
            textView.setText(imageNames[position]);
            return view;
        }
    }
}
