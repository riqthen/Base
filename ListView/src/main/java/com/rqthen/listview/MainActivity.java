package com.rqthen.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 新知识：
 * ArrayAdapter、SimpleAdapter、BaseAdapter长按事件的true/false
 * ListView单选多选要用setChoiceMode方法确定模式
 * 在arrays文件里设置数组，用 getResources().getStringArray(R.array.citys)获取数据数组
 * SimpleAdapter需要Map集合，Map最好使用for循环put数据，将map添加进list集合
 * getLayoutInflater().inflate(R.layout.list_item_5, null);返回一个View对象
 * convertView布局重用，及ViewHolder的使用，即ListView的优化
 */

/*
 * ListView
 * 1.组件方式设置ListView
 * 2.继承于ListActivity方式设置ListView
 * 3.每项单选和多选的ListView
 * 4.实现图文列表
 * 5.使用自定义适配器实现图文列表，及ListView的优化
 * 6.ListView的分页（刷新）
 */

/**
 * arrays文件，在Spinner、ListView中会用到，entries属性指定
 * item项目内容超过屏幕显示之后，会自动换行
 * View强转TextView之后可以getText获取文本内容
 * 长按事件全部返回true，使只响应长按事件
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setOnItemClickListener(this);      //单击事件
        listView.setOnItemLongClickListener(this);  //长按事件
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { //此处view为每个item的视图
        Toast.makeText(this, ((TextView) view).getText(), Toast.LENGTH_SHORT).show();   //此处Toast每个项目的值（广州、郑州...）
        Log.e("===============", "点击了");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.e("===============", "长按了");
        return true;   //false 长按和点击事件都会相应(松开时响应点击事件)     //true 只响应长按事件
    }
}
