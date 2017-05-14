package com.riqthen.base.MyRelativeLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MyRelativeLayout extends RelativeLayout {

	public MyRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			Log.e("TAG", "点击事件");
			onClickListener.onClick(this);
			break;
		}
		return super.onTouchEvent(event);
	}

	public OnClickListener onClickListener;

	public interface OnClickListener {
		void onClick(View v);
	}

}
