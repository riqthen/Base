package com.riq.alertdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn;
    String[] s = new String[]{"aaa", "bbb"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    //点击对话框外部或者三个按钮都会关闭对话框
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //TODO 设置标题
        builder.setTitle("标题");
        //TODO 设置显示的内容
//        builder.setMessage("内容");
        //TODO 设置显示条目
//        builder.setItems(s, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Log.e("============2", "3333333333333");
//            }
//        });
        //TODO 设置显示一个布局
//        builder.setView(R.layout.activity_main);
        //TODO 设置多选
//        builder.setMultiChoiceItems(s, new boolean[]{true, false}, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                Toast.makeText(MainActivity.this, "444", Toast.LENGTH_SHORT).show();
//            }
//        });
        //TODO 设置单选类型
//        builder.setSingleChoiceItems()
        //TODO 设置适配器类型
//        builder.setAdapter(adapter, onClickListener);
        //TODO 设置图标
        builder.setIcon(R.mipmap.ic_launcher);
        //TODO 点击对话框外部是否关闭对话框，默认为true，即关闭
        builder.setCancelable(false);
        //TODO 对话框关闭时的监听。点击三个按钮时对话框也会关闭，所以也会触发
//        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                Toast.makeText(MainActivity.this, "111", Toast.LENGTH_SHORT).show();
//                Log.e("==============", "已经关闭");
//            }
//        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show(); //一定要create和show
    }

}
