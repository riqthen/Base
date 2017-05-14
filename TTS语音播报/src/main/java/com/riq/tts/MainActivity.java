package com.riq.tts;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

/**
 * 1.创建TextToSpeech对象，创建时传入OnInitListener监听器监听创建是否成功；
 * 2.设置TextToSpeech所用的语言、国家选项，通过返回值判断TTS是否支持该语言和国家选项；
 * 3.调用speak()或者synthesizeToFile()方法；
 * 4.关闭TTS，回收资源。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextToSpeech tts;
    private Button btnSpeech;
    private Button btnFile;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.et);
        btnSpeech = (Button) findViewById(R.id.btn_speech);
        btnFile = (Button) findViewById(R.id.btn_file);

        btnSpeech.setOnClickListener(this);
        btnFile.setOnClickListener(this);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            //加载TTS引擎
            @Override
            public void onInit(int status) {
                //如果加载成功
                if (status == TextToSpeech.SUCCESS) {
                    int lan = tts.setLanguage(Locale.CHINESE);
                    //如果不支持所设置的语言
                    if (lan != TextToSpeech.LANG_AVAILABLE && lan != TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                        Toast.makeText(MainActivity.this, "不支持这种语言的朗读", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.shutdown();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_speech:
                submit();
                Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
                tts.speak(et.getText().toString(), TextToSpeech.QUEUE_ADD, null);
                break;
            case R.id.btn_file:
                submit();
                tts.synthesizeToFile(et.getText().toString(), null, "/mnt/sdcard/tts.wav");
                Toast.makeText(MainActivity.this, "保存了", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void submit() {
        // validate
        String etString = et.getText().toString().trim();
        if (TextUtils.isEmpty(etString)) {
            Toast.makeText(this, "etString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
