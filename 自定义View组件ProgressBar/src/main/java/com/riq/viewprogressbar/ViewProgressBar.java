package com.riq.viewprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * invalidate()是用来刷新View的，必须是在UI线程中进行工作。比如在修改某个view的显示时，调用invalidate()才能看到重新绘制的界面。
 * postInvalidate()在工作者线程中被调用
 */
//圆形的进度框
public class ViewProgressBar extends View {
    Paint paintCircle;
    Paint paintText;
    int width;
    int max;
    int progress;

    public ViewProgressBar(Context context) {
        super(context);
        init();
    }

    public ViewProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyProgressBar);
        max = a.getInt(R.styleable.MyProgressBar_max, 100);
        progress = a.getInt(R.styleable.MyProgressBar_progress, 0);
        a.recycle();    //必须释放！
        init();
    }

    private void init() {
        paintCircle = new Paint();  //
        paintCircle.setAntiAlias(true);   //抗锯齿
        paintCircle.setDither(true);      //防抖动
        paintCircle.setStyle(Paint.Style.STROKE);     //空心画笔
        paintCircle.setStrokeWidth(10);    //画笔宽度
        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setDither(true);
        paintText.setTextSize(50);
        paintText.setColor(Color.LTGRAY);
        paintText.setTextAlign(Paint.Align.CENTER); //文本对其位置
    }

    RectF rectF;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getWidth(); //在该重写方法中，可以获取宽度
        Log.e("======g*****=======", getWidth() + "");
        rectF = new RectF(width / 2 - 200, width / 2 - 200, width / 2 + 200, width / 2 + 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintCircle.setColor(Color.GRAY);
        canvas.drawCircle(width / 2, width / 2, 200, paintCircle);
        paintCircle.setColor(Color.YELLOW);
        canvas.drawArc(rectF, 270, (int) (progress * 1f / max * 360), false, paintCircle);  //此处必须先转为float，再强转为int
        canvas.drawText((int) (progress * 1f / max * 100) + "%", width / 2, width / 2, paintText);
    }


    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress < max) {
                    progress++;
                    try {
                        Thread.sleep(50);
                        postInvalidate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (onProgressCompleteListener != null) {
                    onProgressCompleteListener.onFinish();
                }
            }
        }).start();
    }

    OnProgressCompleteListener onProgressCompleteListener;

    public void setOnProgressCompleteListener(OnProgressCompleteListener onProgressCompleteListener) {
        this.onProgressCompleteListener = onProgressCompleteListener;
    }

    interface OnProgressCompleteListener {
        void onFinish();
    }
}
