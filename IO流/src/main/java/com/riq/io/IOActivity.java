package com.riq.io;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 所有输入流都是抽象类InputStream(字节输入流)，或Reader(字符输入流)的子类
 * 所有输出流都是抽象类OutputStream(字节输出流)，或Writer(字符输出流)的子类
 * TODO 输入流的使用步骤
 * 输入流，即把文件输入进程序
 * 1.设定输入流的源
 * 2.创建指向源的输入流
 * 3.让输入流读取源中的数据
 * 4.关闭输入流
 */
public class IOActivity extends AppCompatActivity {
    InputStream is;    //文件字节输入流
    OutputStream os;
    TextView textView;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_io);
        textView = (TextView) findViewById(R.id.tv);
        file = new File("/mnt/sdcard/zzz.txt");

    }

    //字节输入流
    //读取一个文件
    public void onFileInputStream(View view) {
        int len = 0;   //表示已经读取的长度
        byte[] buf = new byte[1024];
        try {
            is = new FileInputStream(file);
            while ((len = is.read(buf, 0, 100)) != -1) {    //从0开始读到100，然后写入buf数组（先进后读，即倒着读）   如果没有读出字节，则返回-1
                String s = new String(buf, 0, len);     //读了多少写多少
                textView.setText(s);
                Log.e("=============", s);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //字节输出流
    //可以创建并写入一个文件
    public void onFileOutputStream(View view) {
        byte[] bytes = "123456".getBytes();
        byte[] bytes2 = "16156".getBytes();
        try {
            os = new FileOutputStream(file);
            os.write(bytes);
            os.close();     //此处不关闭也可以，但是关闭为好
            os = new FileOutputStream(file, true);  //追加输入
            os.write(bytes2);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        file.delete();
        Log.e("删除成功?", "-------------" + file.delete());
    }
}
