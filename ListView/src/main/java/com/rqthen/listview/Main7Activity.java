package com.rqthen.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rqthen.listview.R;

import java.util.ArrayList;
import java.util.List;

public class Main7Activity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    ListView listView;
    MyAdapter adapter;
    List<UserBean> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        listView = (ListView) findViewById(R.id.lv);
        users = new ArrayList<>();
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        initDate();
    }

    private void initDate() {
        for (int i = 0; i < 30; i++) {
            users.add(new UserBean("张三" + i, i));
        }
    }

    //增删集合会改变ListView显示，需要在主线程

    //点击添加
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        users.add(new UserBean("李四" + id, (int) id));
        adapter.notifyDataSetChanged();
    }

    //长按删除该项
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        users.remove(position);
        adapter.notifyDataSetChanged();
        return true;    //为false则会响应点击事件和长按事件，true则只响应长按事件
    }


    //适配器
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_7, null);
                holder = new ViewHolder();
                holder.textViewName = (TextView) convertView.findViewById(R.id.tv_name);
                holder.textViewAge = (TextView) convertView.findViewById(R.id.tv_age);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textViewName.setText(users.get(position).getName());
            holder.textViewAge.setText("   " + users.get(position).getAge());
            return convertView;
        }
    }

    class ViewHolder {
        TextView textViewName;
        TextView textViewAge;
    }


    //数据Bean
    class UserBean {
        String name;
        int age;

        public UserBean(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "UserBean{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
