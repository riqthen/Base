package com.rqthen.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * 使用自定义适配器实现图文列表
 */
/*
 * ListView的优化
 * 1.重复使用convertView，使每次显示在屏幕上的项是同一个而不是每次显示都是新建的
 *  在getView()方法中，直接判断if(convertView == null){ } 将View view改为convertView
 * 2.使用ViewHolder提高在容器中查找组件的效率
 *  创建ViewHolder内部类，在其中定义item布局中的组件
 */
public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(new MyAdapter(this));
    }


    static class MyAdapter extends BaseAdapter {
        String[] words = {"a", "b", "c", "d", "e"};
        int[] img = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e};

        Context context;

        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return words.length;
        }

        @Override
        public Object getItem(int i) {
            return words[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        //自定义适配器的getView()方法通用的格式
        /*
        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(context);     //创建布局填充类的对象
            View view = inflater.inflate(R.layout.list_item_5, null);    //将项布局填充到上面的对象中
            ImageView imageView = (ImageView) view.findViewById(R.id.iv);    //找到布局中的组件
            TextView textView = (TextView) view.findViewById(R.id.tv);
            imageView.setImageResource(img[i]);
            textView.setText(words[i]);
            Log.e("=========",view+"   "+i);
            return view; //返回填充了项布局的视图
        }
        */

        //如果不优化的话，每个出现在屏幕中的项的view都是新建的，而用convertView则是缓存了之前显示过的项的view，所以id会是一样的，并不是新建
        /*
        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);     //创建布局填充类的对象
                convertView = inflater.inflate(R.layout.list_item_5, null);    //将项布局填充到上面的对象中
            }   //这个if语句即为ListView的优化，将View view改为convertView
            ImageView imageView = (ImageView) convertView.findViewById(R.id.iv);    //找到布局中的组件
            TextView textView = (TextView) convertView.findViewById(R.id.tv);
            imageView.setImageResource(img[i]);
            textView.setText(words[i]);
            Log.e("=========", convertView + "   " + i);
            return convertView; //返回填充了项布局的视图
        }
        */

        //如果不优化的话，每个出现在屏幕中的项的view都是新建的，而用convertView则是缓存了之前显示过的项的view，所以id会是一样的，并不是新建
        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder holder;
            if (convertView == null) {
//              convertView = getLayoutInflater().inflate(R.layout.list_item_5, null);  //那么MyAdapter不允许static
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item_5, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.imageView.setImageResource(img[i]);
            holder.textView.setText(words[i]);
            Log.e("=========", convertView + "   " + i);
            return convertView; //返回填充了项布局的视图
        }
    }

    //用于保存第一次查找的组件，避免下次重复查找，以提高效率
    static class ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View convertView) {
            imageView = (ImageView) convertView.findViewById(R.id.iv);    //找到布局中的组件
            textView = (TextView) convertView.findViewById(R.id.tv);
        }

    }
}
