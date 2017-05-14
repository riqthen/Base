package com.riq.startactivityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HeightChoiceActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int OK_CODE = 0x5;
    public static final int CANCEL_CODE = 0x6;
    EditText et;
    Button btnOk;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_choice);
        et = (EditText) findViewById(R.id.et);
        btnOk = (Button) findViewById(R.id.btn_ok);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                if (!TextUtils.isEmpty(et.getText().toString())) {
                    Intent intent = new Intent();   // Intent intent = getIntent(); 都可以
                    int height = Integer.parseInt(et.getText().toString());
                    intent.putExtra("height", height);
                    setResult(OK_CODE, intent);
                    finish();
                }else {
                    Toast.makeText(this, "请输入身高", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_cancel:
                setResult(CANCEL_CODE);
                finish();
                break;
        }
    }
}
