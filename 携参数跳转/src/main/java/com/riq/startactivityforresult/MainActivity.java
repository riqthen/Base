package com.riq.startactivityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.riq.startactivityforresult.HeightChoiceActivity.CANCEL_CODE;
import static com.riq.startactivityforresult.HeightChoiceActivity.OK_CODE;
import static com.riq.startactivityforresult.SexChoiceActivity.RESULTMAN;
import static com.riq.startactivityforresult.SexChoiceActivity.RESULTWOMAN;


/**
 * 都用intent的putExtra()不用Bundle吧。
 * startActivity()携带数据是，A→B
 * startActivityForResult() 则是A→B→A onActivityResult()方法写在A。即，B的操作会影响A
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUESTSEX = 0x1;
    private static final int REQUESTHEIGHT = 0x2;
    Button btnMsg;
    Button btnSex;
    Button btnHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMsg = (Button) findViewById(R.id.btn_msg);
        btnSex = (Button) findViewById(R.id.btn_sex);
        btnHeight = (Button) findViewById(R.id.btn_height);
        btnMsg.setOnClickListener(this);
        btnSex.setOnClickListener(this);
        btnHeight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_msg:
                Intent intent1 = new Intent(MainActivity.this, MsgActivity.class);
                intent1.putExtra("msg", "哈喽");
                intent1.putExtra("age", 2);
                startActivity(intent1);
                break;
            case R.id.btn_sex:
                Intent intent2 = new Intent(MainActivity.this, SexChoiceActivity.class);
                startActivityForResult(intent2, REQUESTSEX);
                break;
            case R.id.btn_height:
                Intent intent3 = new Intent(MainActivity.this, HeightChoiceActivity.class);
                startActivityForResult(intent3, REQUESTHEIGHT);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUESTSEX:
                if (resultCode == RESULTMAN) {
                    btnSex.setText("男");
                } else if (resultCode == RESULTWOMAN) {
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
