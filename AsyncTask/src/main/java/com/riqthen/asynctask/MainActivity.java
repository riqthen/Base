package com.riqthen.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/*
 * 1. AsyncTask的介绍
    与Handler作用相似，使需要与用户界面交互的长时间任务变得更简单。
    适用于简单的异步处理，不需要借助线程和Handler即可实现。
 * 2. AsyncTask的执行步骤
 * 3. 使用AsyncTask实现下载
 * 4. AsyncTask的准则
 */

/*
 AsyncTask<Params, Progress, Result>
 Params 启动任务执行的输入参数，例如Http请求的URL
 Progress 后台任务执行的百分比
 Result 后台执行任务最终返回的结果，如String
 */
public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
    }

    public void btnClick(View view) {
        new MyAsyncTask(this).execute();    //execute方法执行异步任务
    }

    private static class MyAsyncTask extends AsyncTask<String, Integer, String> {
        MainActivity activity;

        MyAsyncTask(MainActivity activity) {
            this.activity = activity;
        }

        // 执行任务之前触发的事件方法，在该方法中做初始化工作（主线程）
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("===========", "onPreExecute");
            activity.textView.setText("开始执行...");
        }

        // 执行后台任务，该方法执行的内容类似于在子线程中执行的步骤（子线程）
        @Override
        protected String doInBackground(String... strings) {
            for (int i = 0; i < 10; i++) {
                Log.e("------------", "i = " + i);
                publishProgress(i);  //更新进度，该方法的调用会调用下面onProgressUpdate()方法
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "success";
        }

        // 更新进度值
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            activity.textView.setText("当前值为" + values[0]);
        }

        // doInBackground方法返回结果之后调用
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            activity.textView.setText(s);
        }
    }
}
