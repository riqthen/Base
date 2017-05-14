package com.riq.startactivityforresult.bundle_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.riq.startactivityforresult.R;

public class AActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AActivity.this, BActivity.class);
                /*
                 * Bundle传递：
                 * Bundle bundle = new Bundle();
                 * bundle.putInt("num", 666);
                 * intent.putExtras(bundle);
                 */

                intent.putExtra("num", 111);

                startActivity(intent);
            }
        });
    }

}
