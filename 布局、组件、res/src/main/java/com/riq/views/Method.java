package com.riq.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class Method extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method);
        textView = (TextView) findViewById(R.id.tv);
        String s = "<strong>加粗</strong><br/><font color='#125478'>颜色</font><p>段前段后间距</p>";
        textView.setText(Html.fromHtml(s));
    }
}
