package com.riq.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by 锐 on 2017/2/27.
 */

public class MyView extends View {
    Paint paint = new Paint();

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //初始化画笔
    private void init() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;       //获取屏幕宽度
        int height = dm.heightPixels;     //获取屏幕高度
        paint.setAntiAlias(true);   //抗锯齿
        paint.setDither(true);      //防抖动
        paint.setColor(Color.RED);  //画笔颜色
        paint.setAlpha(128);        //画笔透明度
        paint.setStyle(Paint.Style.STROKE);     //画笔类型，此处为空心
        paint.setStrokeWidth(10);   //线条宽度
//设置渐变效果    RadialGradient、SweepGradient
        Shader shader = new LinearGradient(width / 2, height / 2, width, height, Color.RED, Color.BLUE, Shader.TileMode.REPEAT);
        paint.setShader(shader);

    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(300,300);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(500, 500, 200, paint);
    }
}
