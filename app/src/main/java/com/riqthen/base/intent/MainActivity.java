package com.riqthen.base.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.riqthen.base.R;

import static com.riqthen.base.intent.HeightChoiceActivity.CANCEL_CODE;
import static com.riqthen.base.intent.HeightChoiceActivity.OK_CODE;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUESTSEX = 0x1;
    private static final int REQUESTHEIGHT = 0x2;
    Button btnSex;
    Button btnHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSex = (Button) findViewById(R.id.btn_sex);
        btnHeight = (Button) findViewById(R.id.btn_height);
        btnSex.setOnClickListener(this);
        btnHeight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sex:
                Intent intent = new Intent(MainActivity.this, SexChoiceActivity.class);
                startActivityForResult(intent, REQUESTSEX);
                break;
            case R.id.btn_height:
                Intent intent2 = new Intent(MainActivity.this, HeightChoiceActivity.class);
                startActivityForResult(intent2, REQUESTHEIGHT);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUESTSEX:
                if (resultCode == SexChoiceActivity.RESULTMAN) {
                    btnSex.setText("男");
                } else if (resultCode == SexChoiceActivity.RESULTWOMAN) {
                    btnSex.setText("女");
                }
                break;
            case REQUESTHEIGHT:
                if (resultCode == OK_CODE) {
                    int height = data.getIntExtra("height", 0);
                    btnHeight.setText("身高为" + height);
                } else if (resultCode == CANCEL_CODE) {
                    btnHeight.setText("未输入");
                }

                break;
        }
    }
}
