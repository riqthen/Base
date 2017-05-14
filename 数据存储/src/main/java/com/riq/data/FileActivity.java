package com.riq.data;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


/**
 * 点击按钮，将文本存储在文件中
 * 文件存储在 /data/data/com.riq.data/files/文件名.txt
 * 使用的类：FileOutputStream、BufferedWriter、OutputStreamWriter
 * MODE_PRIVATE 是默认的操作模式，表示当指定同样文件名的时候，所写入的内容将会覆盖原文件中的内容；
 * MODE_APPEND 则表示如果该文件已存在就往文件里面追加内容，不存在就创建新文件。
 */
public class FileActivity extends AppCompatActivity {
    private EditText etFile;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        etFile = (EditText) findViewById(R.id.et);

    }

    //存储数据到文件
    public void fileSave(View view) {
        text = etFile.getText().toString();
        FileOutputStream out;
        BufferedWriter writer = null;
        OutputStreamWriter osw;
        try {
            out = openFileOutput("文件名.txt", MODE_PRIVATE);   //追加文本；MODE_PRIVATE覆盖文件
            osw = new OutputStreamWriter(out);
            writer = new BufferedWriter(osw);
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //点击读取文件中的数据
    public void fileRead(View view) {
        if (text != null) {
            FileOutputStream out;
            BufferedWriter writer = null;
            OutputStreamWriter osw;
            try {
                out = openFileOutput("文件名.txt", MODE_PRIVATE);
                osw = new OutputStreamWriter(out);
                writer = new BufferedWriter(osw);
                writer.write(text);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            etFile.setText(text); //点击fileSave时已经给text赋值了
        }
    }
}
