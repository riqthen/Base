package com.rqthen.listview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;

/*
 *ListView的分页（即上拉加载）
 */
public class MainActivity6 extends AppCompatActivity implements AbsListView.OnScrollListener {
    Vector<News> news = new Vector<>();
    private static final int DATA_UPDATE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.lv);
        //用getLayoutInflater().inflate()来获取布局视图
        View footerView = getLayoutInflater().inflate(R.layout.loading6, null);
        listView.addFooterView(footerView); //在ListView的地步添加视图
        initData();
        listView.setAdapter(new MyAdapter());
        listView.setOnScrollListener(this);
    }

    private void initData() {
        int index = 0;
        for (int i = 0; i < 10; i++) {
            News n = new News();
            n.title = "title" + index;
            n.content = "content" + index;
            index++;
            news.add(n);
        }
    }

    int lastIndex;

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (new MyAdapter().getCount() == lastIndex && i == SCROLL_STATE_IDLE) {  //触摸着停下状态
            (new LoadingThread()).start();  //此处如果线程是实现Runnable，则没有start方法
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        lastIndex = i + i1 - 1;
    }

    //线程之间通信
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DATA_UPDATE:
                    new MyAdapter().notifyDataSetChanged();
                    break;
            }
        }
    };

    private class LoadingThread extends Thread {
        @Override
        public void run() {
            initData();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //通过handler给主线程发送消息标记
            handler.sendEmptyMessage(DATA_UPDATE);
        }
    }


    class MyAdapter extends BaseAdapter {
//        Context context;
//
//        public MyAdapter(Context context) {
//            this.context = context;
//        }


        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public Object getItem(int i) {
            return news.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder holder;
            if (convertView == null) {
//                LayoutInflater inflater = LayoutInflater.from(context);
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.list_item_6, null);
                holder = new ViewHolder();
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            News n = news.get(i);
            holder.tvTitle.setText(n.title);
            holder.tvContent.setText(n.content);
            return convertView;
        }
    }

    private class ViewHolder {
        TextView tvTitle;
        TextView tvContent;
    }

    private class News {
        String title;
        String content;
    }
}
