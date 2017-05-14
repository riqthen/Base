package com.riq.fragmentpageradapter;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    // 会随ViewPager改变的tab栏，design包的，在adapter中在getPageTitle方法设置文本
    private TabLayout tabLayout;
    private List<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initData() {
        viewPager = (ViewPager) findViewById(R.id.vp);
        tabLayout = (TabLayout) findViewById(R.id.tl);
        list.add(new AFragment());
        list.add(new BFragment());
    }

    String[] titles = {"A", "B"};

    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        // TabLayout栏的文本
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
