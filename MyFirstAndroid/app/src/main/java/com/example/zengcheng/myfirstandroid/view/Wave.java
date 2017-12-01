package com.example.zengcheng.myfirstandroid.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

/**
 * Created by zengcheng on 2017/12/1.
 */

public class Wave {
    private Paint mPaint;
    private Path mPath;

    // 波浪高低偏移量
    private int offset = 20;

    // X轴，view的偏移量
    private int xoffset = 0;
    private final int xStartOffset;
    // view的Y轴高度
    private int viewY = 0;


    // 波浪速度
    private int waveSpeed = 50;
    private int waveFrequence = 1;

    private ValueAnimator animator;
    private int mWidth;
    private int mHeight;
    private MyWaveView2 mParent;
    private int waveLength;

    public Wave(int color, int strokeWidth, int speed, int height, int xOffset, Context context, MyWaveView2 parent, int waveFrequence) {
        mParent = parent;
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(strokeWidth);
        mPath = new Path();
        offset = height;
        this.xStartOffset = xOffset;
        waveSpeed = speed;
        this.waveFrequence = waveFrequence;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWidth = wm.getDefaultDisplay().getWidth();
        animator = new ValueAnimator();
        animator.setFloatValues(0, mWidth);
        animator.setDuration(waveSpeed * 20);
        animator.setRepeatCount(-1);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float change = (float) animation.getAnimatedValue();
                xoffset = (int) change;
                mParent.refresh();
            }
        });
        animator.start();
    }

    //    public void onDraw(Canvas canvas, int width, int height) {
//        mWidth = width;
//        mHeight = height;
//        mPath.reset();
//
//        viewY = mHeight / 2;
//
//        // 绘制屏幕内的波浪
//        mPath.moveTo(xStartOffset + xoffset, viewY);
//        mPath.quadTo(mWidth / 4 + xoffset + xStartOffset, viewY - offset, mWidth / 2 + xoffset + xStartOffset, viewY);
//        mPath.moveTo(mWidth / 2 + xoffset + xStartOffset, viewY);
//        mPath.quadTo(mWidth / 4 * 3 + xoffset, viewY + offset, mWidth + xoffset + xStartOffset, viewY);
//
//        // 绘制屏幕外的波浪
//        mPath.moveTo(xoffset + xStartOffset - mWidth, viewY);
//        mPath.quadTo(mWidth / 4 + xoffset + xStartOffset - mWidth, viewY - offset, mWidth / 2 + xoffset + xStartOffset - mWidth, viewY);
//        mPath.moveTo(mWidth / 2 + xoffset + xStartOffset - mWidth, viewY);
//        mPath.quadTo(mWidth / 4 * 3 + xoffset + xStartOffset - mWidth, viewY + offset, mWidth + xoffset + xStartOffset - mWidth, viewY);
//        Log.i("Wave", "---onDraw---xoffset:" + xoffset);
//        canvas.drawPath(mPath, mPaint);
//    }
//    public void onDraw(Canvas canvas, int width, int height) {
//        mWidth = width;
//        mHeight = height;
//        waveLength = mWidth / 4;
//        mPath.reset();
//        viewY = mHeight / 2;
//        int startX = xStartOffset + xoffset;
//        boolean isUpWave = true;
//        while (startX < width) {
//            mPath.moveTo(startX, viewY);
//            mPath.quadTo(startX + waveLength, isUpWave ? (viewY - offset) : (viewY + offset), startX + 2 * waveLength, viewY);
//            startX += 2 * waveLength;
//            isUpWave = !isUpWave;
//        }
//
//        // 绘制屏幕外的波浪
//        mPath.moveTo(xoffset + xStartOffset - mWidth, viewY);
//        mPath.quadTo(mWidth / 4 + xoffset + xStartOffset - mWidth, viewY - offset, mWidth / 2 + xoffset + xStartOffset - mWidth, viewY);
//        mPath.moveTo(mWidth / 2 + xoffset + xStartOffset - mWidth, viewY);
//        mPath.quadTo(mWidth / 4 * 3 + xoffset + xStartOffset - mWidth, viewY + offset, mWidth + xoffset + xStartOffset - mWidth, viewY);
//        Log.i("Wave", "---onDraw---xoffset:" + xoffset);
//        canvas.drawPath(mPath, mPaint);
//    }
    public void onDraw(Canvas canvas, int width, int height){
        mWidth = width;
        mHeight = height;
        mPath.reset();
    }
    private void drawRight(Canvas canvas, int width, int height) {
        int startX = xStartOffset + xoffset;
        boolean isUpWave = true;
        while (startX < width) {
            mPath.moveTo(startX, viewY);
            mPath.quadTo(startX + waveLength, isUpWave ? (viewY - offset) : (viewY + offset), startX + 2 * waveLength, viewY);
            startX += 2 * waveLength;
            isUpWave = !isUpWave;
        }
    }

    public void pause() {
        animator.pause();
    }

    public void start() {
        animator.start();
    }

    public void release() {
        animator.cancel();
    }
}
