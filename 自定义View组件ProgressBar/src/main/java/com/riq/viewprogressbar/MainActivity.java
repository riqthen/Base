package com.riq.viewprogressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ViewProgressBar viewProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewProgressBar = (ViewProgressBar) findViewById(R.id.vpb);
        viewProgressBar.setOnProgressCompleteListener(new ViewProgressBar.OnProgressCompleteListener() {
            @Override
            public void onFinish() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "执行完毕", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        viewProgressBar.start();
    }
}
