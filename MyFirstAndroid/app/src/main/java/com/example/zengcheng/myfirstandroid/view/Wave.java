package com.example.zengcheng.myfirstandroid.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zengcheng on 2017/12/1.
 */

public class Wave {
    private Paint mPaint;
    private Path mPath;
    List<QuadPath> mQuadPaths;
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

    public Wave(int color, int strokeWidth, int speed, int height, final int xOffset, Context context, MyWaveView2 parent, int waveFrequence) {
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
        animator.setDuration(waveSpeed * 200);
        animator.setRepeatCount(-1);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float change = (float) animation.getAnimatedValue();
                xoffset = (int) change;
                mParent.refresh();
//                updatePath(mWidth, mHeight);
//                if (xOffset == mWidth) {
//                    updatePath(mWidth,mHeight);
//                }
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
    public void onDraw(Canvas canvas, int width, int height) {
        if (mWidth != width || mHeight != height || mQuadPaths == null) {
            mWidth = width;
            mHeight = height;
            updatePath(mWidth, mHeight);
        }

        mPath.reset();
        List<QuadPath> paths = mQuadPaths;
        for (QuadPath p : paths) {
            mPath.moveTo(p.a.x+xoffset, p.a.y);
            mPath.quadTo(p.b.x+xoffset, p.b.y, p.c.x+xoffset, p.c.y);
        }
        canvas.drawPath(mPath, mPaint);
    }

    public void updatePath(int width, int height) {
        List<Point> points = generatePath(width, height, 0, 0);
        mQuadPaths = generateQuadPath(points, width, height);
//        mQuadPaths = generateTestQuadPath();
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

    public List<Point> generatePath(final int width, final int height, final int offsetX, final int offsetY) {
        int startX = 0 + offsetX;
        int startY = height / 2;
        int curLength = 0;
        List<Point> points = new ArrayList<>();
        points.add(new Point(startX, startY));
        while (curLength < width) {
            int moveX = getRandom((width - curLength) / 5, width - curLength);
            if (curLength > width - 80) {
                moveX = width - curLength;
            }
            int x = startX + moveX;
            curLength += moveX;
            int y = startY;
            Point point = new Point(x, y);
            startX = x;
            points.add(point);
        }
        return points;
    }

    private List<QuadPath> generateQuadPath(List<Point> points, int width, int height) {

        List<QuadPath> path = new ArrayList<>();
        boolean upper = true;
        for (int i = 0; i < points.size() - 1; i++) {
            Point a = points.get(i);
            Point c = points.get(i + 1);
            int x = a.x + (c.x - a.x) / 2;
            int y = getRandom(height / 5, height / 2);
            if (!upper) {
                y = -y;
            }
            int by = height / 2 + y;
            Point b = new Point(x, by);
            upper = !upper;
            QuadPath p = new QuadPath(a, b, c);
            path.add(p);
        }
        return path;
    }

    private List<QuadPath> generateTestQuadPath() {
        int centerY = 500;
        Point a1 = new Point(0, centerY);
        Point b1 = new Point(100, centerY - 50);
        Point c1 = new Point(200, centerY);

        Point a2 = new Point(200, centerY);
        Point b2 = new Point(300, centerY + 80);
        Point c2 = new Point(400, centerY);

        Point a3 = new Point(400, centerY);
        Point b3 = new Point(425, centerY - 5);
        Point c3 = new Point(450, centerY);

        Point a4 = new Point(450, centerY);
        Point b4 = new Point(650, centerY - 500);
        Point c4 = new Point(850, centerY);

        QuadPath pa = new QuadPath(a1, b1, c1);
        QuadPath pa1 = new QuadPath(a2, b2, c2);
        QuadPath pa2 = new QuadPath(a3, b3, c3);
        QuadPath pa3 = new QuadPath(a4, b4, c4);

        List<QuadPath> path = new ArrayList<>();
        path.add(pa);
        path.add(pa1);
        path.add(pa2);
        path.add(pa3);

        return path;
    }

    private int getRandom(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException();
        }
        return (int) (min + Math.random() * max);
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

    class QuadPath {
        Point a, b, c;

        public QuadPath(Point a, Point b, Point c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
