package com.riqthen.fragment;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 显示两个fragment，并实现通过点击TitleFragment里面的按钮，改变ContentFragment的状态
 * 主布局是，把fragment_content改为FrameLayout，在该布局中操作fragment
 * 所有的操作都是，先获取FragmentManager对象，通过该对象开启一个事务，通过该事务操作，最后执行commit方法
 */

/**
 * FragmentTransaction的方法有add，remove，replace，hide
 */
//fragment是commit，SharedPreferences可以是apply()
public class MainActivity2 extends AppCompatActivity {
    ContentFragment contentFragment;
    Content2Fragment content2Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        contentFragment = new ContentFragment();    //实例化Fragment
        content2Fragment = new Content2Fragment();

    }

    public void add(View view) {        //添加contentFragment
        FragmentManager manager = getSupportFragmentManager();  //这个是v4包的方法
        //开启一个事务
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.layout_content, contentFragment);  //将该Fragment实例添加进事务
        transaction.commit();   //提交事务
    }

    public void remove(View view) {     //移除contentFragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(contentFragment);
        transaction.commit();
    }

    public void replace(View view) {    //将contentFragment替换为content2Fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_content, content2Fragment);
        transaction.commit();
    }

    public void hide(View view) {       //隐藏contentFragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(contentFragment);
        transaction.commit();
    }

}
