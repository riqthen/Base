package com.riqthen.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 效果：滑动页面的时候，指示点跟着变化
 * 应用：简单的程序启动页
 */
public class MainActivity2 extends AppCompatActivity {
    ViewPager viewPager;
    List<View> views = new ArrayList<>();   //5个页面
    ImageView[] ivPoints;  //5个点
    int currentIndex = 0;   //当前显示的页的点

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        viewPager = (ViewPager) findViewById(R.id.vp2);
        initView();  //添加5个页面到页面集合
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener(listener);    //设置ViewPager的滑动监听
        initPoint();    //设置指示点到滑动页上
    }

    private void initPoint() {
        LinearLayout llPoint = (LinearLayout) findViewById(R.id.ll_point);
        ivPoints = new ImageView[views.size()]; //创建5个点的
        //获取容器中的组件的方式和方法：
        for (int i = 0; i < ivPoints.length; i++) {
            ivPoints[i] = (ImageView) llPoint.getChildAt(i);
        }
        currentIndex = 0;
        ivPoints[currentIndex].setImageResource(R.mipmap.bullet_white); //将当前的页面的点设置为白色
    }


    private void initView() {
        //添加5个页面到页面集合
        views.add(LayoutInflater.from(this).inflate(R.layout.layout_1, null));
        views.add(LayoutInflater.from(this).inflate(R.layout.layout_2, null));
        views.add(LayoutInflater.from(this).inflate(R.layout.layout_3, null));
        views.add(LayoutInflater.from(this).inflate(R.layout.layout_4, null));
        views.add(getLayoutInflater().inflate(R.layout.layout_5, null));    //两种填充方式都可以

    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            //滑动选择之后
            if (currentIndex < 0 /*|| currentIndex == position 前一页面序号和当前序号相同时。不加上也可以*/
                    || currentIndex > ivPoints.length - 1) {
                return; //页码值只能是[0, 4]，在该范围外重新执行该方法
            }
            ivPoints[currentIndex].setImageResource(R.mipmap.bullet_gray);  //滑动选择之后，将前一个页面的点设置为灰色
            ivPoints[position].setImageResource(R.mipmap.bullet_white);     //将当前的点设置为白色
            currentIndex = position;    //将currentIndex的值改为当前的i

        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };


    //适配器
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() { //页面的数目
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;  //永远是这样写
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {  //添加当前项
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) { //移除当前项
            container.removeView(views.get(position));

        }
    }
}
