package com.riq.startactivityforresult;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SexChoiceActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int RESULTMAN = 0x3;
    public static final int RESULTWOMAN = 0x4;
    Button btnMan;
    Button btnWomen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex_choice);
        btnMan = (Button) findViewById(R.id.btn_man);
        btnWomen = (Button) findViewById(R.id.btn_woman);
        btnMan.setOnClickListener(this);
        btnWomen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_man:
                setResult(RESULTMAN);
                finish();
                break;
            case R.id.btn_woman:
                setResult(RESULTWOMAN);
                finish();
                break;
        }
    }
}
