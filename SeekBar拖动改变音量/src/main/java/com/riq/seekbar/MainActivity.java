package com.riq.seekbar;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.sb);
        textView = (TextView) findViewById(R.id.tv);
        // 获取系统服务
        // 获取音频管理器
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        seekBar.setMax(maxVolume);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBar.setProgress(currentVolume);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 进度改变时
                textView.setText(progress + "/" + seekBar.getMax());
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress,
                        AudioManager.FLAG_SHOW_UI | AudioManager.FLAG_PLAY_SOUND);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 开始拖动(碰上去的时候)
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 拖动完毕
            }
        });
    }

    //让按音量键的时候也会影响SeekBar
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            seekBar.setProgress(seekBar.getProgress() + 1);
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            seekBar.setProgress(seekBar.getProgress() - 1);
        }
        return super.onKeyUp(keyCode, event);
    }
}
