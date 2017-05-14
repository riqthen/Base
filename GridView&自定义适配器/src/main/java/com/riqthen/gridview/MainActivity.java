package com.riqthen.gridview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
//需要适配器的组件：ListView、ViewPager、PopupWindow、GridView
/*
 * 1.展示图片
 * 2.复杂的展示，展示图文，利用自定义适配器
 */

/**
 * 作用：用于展示图片，可以用作图库。
 * 效果：可以滑动，类似ScrollView
 */
public class MainActivity extends AppCompatActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gv);
        gridView.setAdapter(new MyAdapter(this));
    }

    //设置适配器
    static class MyAdapter extends BaseAdapter {
        private Context context;

        MyAdapter(Context context) {
            this.context = context;
        }   //当需要上下文而不能用this指定的时候，用该步骤构建context

        int[] images = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e, R.mipmap.z1, R.mipmap.z2, R.mipmap.butterfly, R.mipmap.file};

        @Override
        public int getCount() { //数组元素数目，即数组长度
            return images.length;
        }

        @Override
        public Object getItem(int position) {   //数组元素
            return images[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);   //当需要上下文的时候，构造context
            imageView.setImageResource(images[position]);
            return imageView;
        }
    }
}
