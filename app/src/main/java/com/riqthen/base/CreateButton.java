package com.riqthen.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

public class CreateButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_button);
        LinearLayout linearLayout0= (LinearLayout) findViewById(R.id.activity_create_button);
        for (int i = 0; i < 4; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < 4; j++) {
                Button button = new Button(this);
                button.setText(i + " " + j);
                button.setPadding(4, 4, 4, 4);
//                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                button.setLayoutParams(params);
                linearLayout.addView(button);
            }
            linearLayout0.addView(linearLayout);
        }
    }
}
