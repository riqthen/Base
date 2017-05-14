package com.riq.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * String类的方法
 * indexOf()、lastIndexOf()、substring、trim(去空格)、concat(同"+")、split(拆分)
 * StringBuffer类
 * toString()、append()、insert()
 */
//序号都是算头不算尾
public class StringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        setContentView(textView);
        String s = " 十年生死两茫茫，十生两茫。 ";
        //---------"12345 6 7 8 9 101112131415161718"

        //TODO substring 查找字符
        Log.e("==========", s.substring(2, 5));          // 年生死

        //TODO indexOf 查找序号
        Log.e("************", s.indexOf("生") + "");     // 3
        Log.e("************", s.indexOf("生", 4) + "");  // 3 fromIndex<=3 :3  / >3 :10
        Log.e("------------", s.indexOf("好") + "");     // -1 查找不到返回-1
        //TODO trim 去首尾空格
        Log.e("+++++++++++", s.trim());
        //TODO split 将字符串分割为数组
        String[] a = s.split("茫");
        for (int i = 0; i < a.length; i++) {
            Log.e("+++++++++++", a[i]);
        }
        Log.e("+++++++++++", s.concat("hhh"));

        //TODO StringBuffer     相比String效率更高
        StringBuffer sb = new StringBuffer("切切切切切切");
        Log.e("=============", sb.append(true) + "");
        Log.e("=============", sb.insert(2, "p") + "");
    }
}
