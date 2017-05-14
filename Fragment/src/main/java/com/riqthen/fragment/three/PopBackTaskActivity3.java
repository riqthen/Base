package com.riqthen.fragment.three;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.riqthen.fragment.R;

/**
 * fragment出入栈操作
 * 实现点击按钮变换界面
 */

public class PopBackTaskActivity3 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_back_task3);
    }


    public void fragmentA(View view) {
        /*PopBackTaskFragment3 pop = new PopBackTaskFragment3("a");*/
        PopBackTaskFragment3 pop = new PopBackTaskFragment3().getInstance("a");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content, pop);
        //把当前Fragment添加到Activity栈
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void fragmentB(View view) {
        /*PopBackTaskFragment3 pop = new PopBackTaskFragment3("b");*/
        PopBackTaskFragment3 pop = new PopBackTaskFragment3().getInstance("b");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content, pop);
        //把当前Fragment添加到Activity栈
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //按下返回键之后，退出的是上一步操作
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                finish();
            } else {
                getSupportFragmentManager().popBackStack(); //出栈
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
