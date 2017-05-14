package com.riq.dialog;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.dialog_my, R.id.dialog_alert, R.id.dialog_menu, R.id.dialog_single, R.id.dialog_multi, R.id.dialog_adapter, R.id.dialog_custom, R.id.dialog_dismiss, R.id.dialog_progress, R.id.dialog_date_picker, R.id.dialog_time_picker})
    public void onClick(View view) {
        switch (view.getId()) {
            //自定义Dialog。实现失败
            case R.id.dialog_my:
                new MyDialog(this).show();
                break;
            //对话框
            case R.id.dialog_alert:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示框")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("显示内容")
                        .setPositiveButton("确定", listener)
                        .setNeutralButton("忽略", listener)
                        .setNegativeButton("取消", listener)
                        .create()
                        .show();
                break;
            //菜单对话框
            case R.id.dialog_menu:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("菜单对话框")
                        .setItems(new String[]{"a", "b", "c"}, listener)
                        .create()
                        .show();
                break;
            //单选对话框
            case R.id.dialog_single:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                builder3.setTitle("单选对话框")
                        .setSingleChoiceItems(new String[]{"a", "b", "c"}, 1, listener)     //1表示默认选择的条目
                        .create()
                        .show();
                break;
            //多选对话框
            case R.id.dialog_multi:
                AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
                builder4.setTitle("多选对话框")
                        .setMultiChoiceItems(new String[]{"a", "b", "c"}, new boolean[]{true, true, false}, listener2)
                        .create()
                        .show();
                break;
            //适配器对话框
            case R.id.dialog_adapter:
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{"a", "b", "c"});
                AlertDialog.Builder builder5 = new AlertDialog.Builder(this);
                builder5.setTitle("适配器对话框")
                        .setAdapter(adapter, listener)
                        .create()
                        .show();
                break;
            //自定义对话框
            case R.id.dialog_custom:
                EditText editText = new EditText(this);
                editText.setHint("hint");
                AlertDialog.Builder builder6 = new AlertDialog.Builder(this);
                builder6.setTitle("自定义对话框")
                        .setView(editText)
                        .create()
                        .show();
                break;
            case R.id.dialog_dismiss:
                View view1 = getLayoutInflater().inflate(R.layout.my_dialog, null);
                ProgressBar pb = (ProgressBar) view1.findViewById(R.id.progressBar1);
                //只能dialog.dismiss()，所以可以用AlertDialog dialog = new AlertDialog.Builder()模式创建dialog
                final AlertDialog dialog = new AlertDialog.Builder(this).setTitle("关闭对话框")
                        .setCancelable(true)   //点击外部是否关闭对话框,默认为true
                        .setView(view1)
                        .create();
                dialog.show();
                // 对话框关闭监听
                pb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "33333333333", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.setOnDismissListener(listener3);
                break;

            //此有专门的类ProgressDialog
            case R.id.dialog_progress:
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("进度条对话框");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i = 0;
                        while (i++ < 100) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progressDialog.setProgress(i);
                            //当进度条走完的时候关闭对话框
//                            if (progressDialog.getProgress() == progressDialog.getMax()) {
//                                try {
//                                    Thread.sleep(1000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                progressDialog.dismiss();
//                            }
                        }
                    }
                }).start();
                break;

            //日期选择对话框 DatePickerDialog
            case R.id.dialog_date_picker:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(MainActivity.this, year + "年" + (month + 1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
                    }
                }, 2000, 0, 1);
                datePickerDialog.show();
                break;

            //时间选择对话框 TimePickerDialog
            case R.id.dialog_time_picker:
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(MainActivity.this, hourOfDay + "时" + minute + "分", Toast.LENGTH_SHORT).show();
                    }
                }, 23, 59, true);
                timePickerDialog.show();
                break;
        }
    }

    //点击事件
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {    //which即点击的条目的序号
            Toast.makeText(MainActivity.this, "点击", Toast.LENGTH_SHORT).show();
        }
    };
    DialogInterface.OnMultiChoiceClickListener listener2 = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            switch (which) {
                case 0:
                    if (isChecked) {
                        Toast.makeText(MainActivity.this, "111", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 1:
                    if (isChecked) {
                        Toast.makeText(MainActivity.this, "222", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    if (isChecked) {
                        Toast.makeText(MainActivity.this, "333", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    DialogInterface.OnDismissListener listener3 = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            Toast.makeText(MainActivity.this, "dismiss", Toast.LENGTH_SHORT).show();
        }
    };

}
