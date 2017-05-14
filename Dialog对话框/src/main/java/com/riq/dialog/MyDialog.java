package com.riq.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

class MyDialog extends Dialog {
    MyDialog(Context context) {
        super(context, R.style.MyDialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }
}
