package com.riqthen.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * AsyncTask的准则：
 * 1. AsyncTask的实例必须在主线程创建
 * 2. execute方法必须在主线程中调用
 * 3. 该Task只能被执行一次，多次调用会出现异常
 * 4. AsyncTask不能完全取代线程，在一些逻辑复杂或者需要在后台反复执行的逻辑可能需要用线程来实现
 */

/**
 * 使用AsyncTask实现下载
 */
public class MainActivity2 extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        progressBar = (ProgressBar) findViewById(R.id.pb);
    }

    public void downloadClick(View view) {
        new DownloadAsyncTask(this).execute("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3330325448,3545219178&fm=116&gp=0.jpg");
    }


    //定义一个类继承于AsyncTask
    static class DownloadAsyncTask extends AsyncTask<String, Integer, Integer> {

        MainActivity2 activity2;

        public DownloadAsyncTask(MainActivity2 activity2) {
            this.activity2 = activity2;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            activity2.progressBar.setProgress(0);
        }

        @Override
        protected Integer doInBackground(String... strings) {   //对应于泛型第二个Integer
            String s = strings[0];
            URL url;
            try {
                url = new URL(s);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                int size = urlConnection.getContentLength();
                publishProgress(0, size);   //0表示需要更新最大进度值，1表示更新当前下载的进度值
                byte[] bytes = new byte[50];
                int len;
                InputStream in = urlConnection.getInputStream();
                FileOutputStream out = new FileOutputStream("/mnt/sdcard/" + "aaa" + ".jpg");
                while ((len = in.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                    publishProgress(1, len);    //更新进度
                    out.flush();
                }
                out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 111;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            switch (values[0]) {
                case 0:
                    activity2.progressBar.setMax(values[1]);
                    break;
                case 1:
                    activity2.progressBar.incrementProgressBy(values[1]);
                    break;
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if (integer == 111) {
                Toast.makeText(activity2, "下载完成", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
