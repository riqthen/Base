package com.riqthen.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/*
 * Handler 的内存泄露问题：
 * 定义一个内部类Handler的时候，会默认拥有外部类对象的引用，所以使用内部类时，最好定义为静态的(这样相当于外部类)
 * 引用的强弱：强引用——软引用——弱引用——虚引用
 * 强引用：只要引用存在，垃圾回收器永远不会回收。
        Object obj = new Object();
   软引用：非必须引用，内存溢出之前进行回收。
        Object obj = new Object();
        SoftReference<Object> sf = new SoftReference<Object>(obj);
        obj = null;
        sf.get();//有时候会返回null
    弱引用：第二次垃圾回收时回收。
        Object obj = new Object();
        WeakReference<Object> wf = new WeakReference<Object>(obj);
        obj = null;
        wf.get();//有时候会返回null
        wf.isEnQueued();//返回是否被垃圾回收器标记为即将回收的垃圾
    虚引用：垃圾回收时回收，无法通过引用取到对象值。
        Object obj = new Object();
        PhantomReference<Object> pf = new PhantomReference<Object>(obj);
        obj=null;
        pf.get();//永远返回null
        pf.isEnQueued();//返回是否从内存中已经删除
 */
//防止内存泄露的标准写法
public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //此处，程序一打开就会关闭，但是handler并没有结束，所以造成内存泄露
        //使用handler延迟3s执行一个操作
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity2.this, "hello", Toast.LENGTH_SHORT).show();
                Log.e("--------", "hello");
            }
        }, 3000);
        finish();
    }

//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//        }
//    };


    //正确的写法：
    private final Handler handler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private final WeakReference<MainActivity2> weakReference;

        MyHandler(MainActivity2 activity2) {
            weakReference = new WeakReference<>(activity2);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity2 activity2 = weakReference.get();
            if (activity2 != null) {

            }
        }
    }
}
