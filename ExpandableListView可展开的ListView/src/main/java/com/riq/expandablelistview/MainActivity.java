package com.riq.expandablelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener {
    private ExpandableListView expandableListView;
    private String[] cities = {"北京", "上海", "广州", "深圳", "上海", "广州", "深圳", "上海", "广州", "深圳"};
    private String[][] names = {
            {"朝阳", "怀柔", "东城", "西城"}
            , {"徐汇", "浦东"}
            , {"天河", "越秀", "增城"}
            , {"罗湖", "福田", "南山", "宝安", "龙岗"}
            , {"徐汇", "浦东"}
            , {"天河", "越秀", "增城"}
            , {"罗湖", "福田", "南山", "宝安", "龙岗"}
            , {"徐汇", "浦东"}
            , {"天河", "越秀", "增城"}
            , {"罗湖", "福田", "南山", "宝安", "龙岗"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.elv);
        expandableListView.setAdapter(new MyAdapter());
        Log.e("------", "cities.length" + cities.length);
        Log.e("------", "names.length" + names.length);
        expandableListView.setOnChildClickListener(this);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
        return false;
    }

    //适配器 BaseExpandableListAdapter
    private class MyAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return cities.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return names[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return cities[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return names[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder groupViewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_group, null);
                groupViewHolder = new GroupViewHolder();
                groupViewHolder.tvGroup = (TextView) convertView.findViewById(R.id.tv_group);
                convertView.setTag(groupViewHolder);
            } else {
                groupViewHolder = (GroupViewHolder) convertView.getTag();
            }
            groupViewHolder.tvGroup.setText(cities[groupPosition]);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder childViewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_child, null);
                childViewHolder = new ChildViewHolder();
                childViewHolder.tvChild = (TextView) convertView.findViewById(R.id.tv_child);
                convertView.setTag(childViewHolder);
            } else {
                childViewHolder = (ChildViewHolder) convertView.getTag();
            }
            childViewHolder.tvChild.setText(names[groupPosition][childPosition]);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    //两个ViewHolder
    private class GroupViewHolder {
        TextView tvGroup;
    }

    private class ChildViewHolder {
        TextView tvChild;
    }
}
