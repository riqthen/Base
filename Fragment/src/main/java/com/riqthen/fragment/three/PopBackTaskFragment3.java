package com.riqthen.fragment.three;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.riqthen.fragment.R;

/**
 * fragment出入栈操作
 * 实现点击按钮变换界面
 * 点击返回键，会返回为上一步操作
 */

public class PopBackTaskFragment3 extends Fragment {
    public PopBackTaskFragment3() {
    }

    /*
    用这种方法会在屏幕旋转的时候出现问题
        String title;

        public PopBackTaskFragment3(String title) {
            this.title = title;
        }
    */

    //fragment的传参方法(推荐)
    static PopBackTaskFragment3 getInstance(String title) {
        PopBackTaskFragment3 pop = new PopBackTaskFragment3();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        pop.setArguments(bundle);
        return pop;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pop_back_task3, null);
        TextView textView = (TextView) view.findViewById(R.id.tv);
        /*textView.setText(title);*/
        textView.setText(getArguments().getString("title"));
        return view;

    }
}
