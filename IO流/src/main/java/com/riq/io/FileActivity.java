package com.riq.io;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
//TODO File文件操作
public class FileActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        imageView = (ImageView) findViewById(R.id.iv);
        textView = (TextView) findViewById(R.id.tv);
        File file = new File("/storage/emulated/0/新","p.txt");
        //TODO 文件名
        String name = file.getName();       // aaa.png
        //TODO 绝对路径，即文件的路径
        String absolutePath = file.getAbsolutePath();   // /mnt/sdcard/aaa.png
        //
        String path = file.getPath();       // /mnt/sdcard/aaa.png
        //TODO 文件所在的路径，不含文件名
        String parent = file.getParent();   // /mnt/sdcard
        //TODO 最后修改时间
        Date date = new Date(file.lastModified());
        //TODO 格式化时间的方法
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String t = format.format(date);
        //TODO 文件是否存在
        boolean exists = file.exists();
        //TODO 创建文件夹，如果父文件夹不存在，则会创建出来
        boolean mkdirsSuccess = file.mkdirs();      //成功
        //TODO 创建文件
        boolean createFileSuccess = false;
        try {
            createFileSuccess = file.createNewFile();   //成功，要先主动创建文件夹mkdirs，且没有同名的文件夹
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO 删除文件
        boolean deleteSuccess = file.delete();  //成功
        //TODO 返回目录下全部文件为String类型
        String[] list = file.list();        //[Preload, sogou, Download,..., bbb.png, 111.txt]
        //TODO 返回目录下全部文件为File类型
        File[] files = file.listFiles();    //[/storage/emulated/0/Preload, /storage/emulated/0/sogou, ..., /storage/emulated/0/111.txt]
        Log.e("--------", Arrays.toString(list));
        textView.setText(createFileSuccess + "");


        //TODO 将图片加载到ImageView
        Bitmap bitmap = BitmapFactory.decodeFile("/storage/emulated/0/aaa.png");
        imageView.setImageBitmap(bitmap);
        Glide.with(this).load(file).into(imageView);

    }
}
