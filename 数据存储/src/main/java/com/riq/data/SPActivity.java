package com.riq.data;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * 存放在/data/data/com.riq.data/shared_prefs/目录下  保存为map键值对
 * 只用MODE_PRIVATE模式
 * editor.commit()和.apply()区别在于：
 * 1. apply没有返回值而commit返回boolean表明修改是否提交成功；
 * 2. apply是将修改数据原子提交到内存, 而后异步真正提交到硬件磁盘, 而commit是同步的提交到硬件磁盘，因此，在多个并发的提交commit的时候，他们会等待正在处理的commit保存到磁盘后在操作，从而降低了效率。而apply只是原子的提交到内容，后面有调用apply的函数的将会直接覆盖前面的内存数据，这样从一定程度上提高了很多效率；
 * 3. apply方法不会提示任何失败的提示。
 * 由于在一个进程中，sharedPreference是单实例，一般不会出现并发冲突，如果对提交的结果不关心的话，建议使用apply，当然需要确保提交成功且有后续操作的话，还是需要用commit的。
 */
public class SPActivity extends AppCompatActivity {
    private EditText etSp;
    private String text;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        etSp = (EditText) findViewById(R.id.et_sp);
    }

    //存储
    public void spSave(View view) {
        text = etSp.getText().toString();
//        只用MODE_PRIVATE模式
//        SharedPreferences sharedPreferences = getSharedPreferences("文件", MODE_PRIVATE);    //①文件.xml 此处不需要给文件输入扩展名，都是默认的xml
//        sharedPreferences = getPreferences(MODE_PRIVATE);     //②MainActivity.xml
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(this);    // ③包名_preferences.xml 此处需要导入包
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("text", text);
        editor.apply();
    }

    //读取
    public void spRead(View view) {
        if (sharedPreferences != null) {
            String name = sharedPreferences.getString("text", "");
            etSp.setText(name);
        }
    }
}
