package com.riqthen.viewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用方式：
 * 在布局文件汇总添加ViewPager组件，在该标签中嵌入PagerTabStrip，添加标签栏
 * 与几个滑动页就创建几个局视布局文件
 */

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    PagerTabStrip pagerTabStrip;
    String[] titles = {"a", "b", "c", "d", "e"};
    List<View> views = new ArrayList<>();   //视图都需要用集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.vp);
        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pts);
        //将每个页面的布局添加到视图集合中，
        views.add(getLayoutInflater().inflate(R.layout.layout_1, null));
        views.add(getLayoutInflater().inflate(R.layout.layout_2, null));
        views.add(getLayoutInflater().inflate(R.layout.layout_3, null));
        views.add(getLayoutInflater().inflate(R.layout.layout_4, null));
        views.add(getLayoutInflater().inflate(R.layout.layout_5, null));

        //标签栏的设置(标签栏是可以点击的)
        pagerTabStrip.setTabIndicatorColor(Color.BLUE); //选项指示器颜色
        pagerTabStrip.setTextColor(getResources().getColor(android.R.color.holo_blue_bright));  //标签字体颜色
        pagerTabStrip.setBackgroundColor(Color.argb(0x11, 0x22, 0x33, 0x44));   //背景颜色

        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.addOnPageChangeListener(listener);    //ViewPager的滑动监听
        viewPager.setCurrentItem(2);    //设置默认显示的画面，此处为第3个
    }


    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            Toast.makeText(MainActivity.this, "当前界面" + (position + 1), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };


    //PagerAdapter适配器
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        //判断当前视图是否为返回对象
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /*
         * 以下是自己添加的方法
         */
        //添加选项视图
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
            return view;
        }

        //删除选项视图
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);   //必须去掉，否则报错UnsupportedOperationException
            container.removeView(views.get(position));
        }

        //获取标题，使显示出PagerTabStrip某一页的title
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
