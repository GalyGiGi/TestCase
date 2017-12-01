package com.example.zengcheng.myfirstandroid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengcheng on 2017/12/1.
 */

public class MyWaveView2 extends View {
    // view宽度
    private int mWidth;
    // view高度
    private int mHeight;
    private List<Wave> mWaves;
    private Context mContext;

    public MyWaveView2(Context context) {
        super(context);
        mContext = context;
        initWaves();
    }

    public MyWaveView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initWaves();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mWaves != null) {
            for (Wave w : mWaves) {
                w.onDraw(canvas, mWidth, mHeight);
            }
        }
    }

    public void pause() {
        if (mWaves != null) {
            for (Wave w : mWaves) {
                w.pause();
            }
        }
    }

    public void start() {
        if (mWaves != null) {
            for (Wave w : mWaves) {
                w.start();
            }
        }
    }

    public void refresh() {
        invalidate();
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        //设置一个默认值，就是这个View的默认宽度为500
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {//相当于我们设置为wrap_content
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {//相当于我们设置为match_parent或者为一个具体的值
            result = specSize;
        }
        mWidth = result;
        return result;
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        mHeight = specSize;
        return result;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mWaves != null) {
            for (Wave w : mWaves) {
                w.release();
            }
        }
    }

    private void initWaves() {
        Wave w1 = new Wave(Color.BLUE, 5, 50, 100, 0, mContext, this,1);
        Wave w2 = new Wave(Color.RED, 5, 50, 200, 0, mContext, this,2);
        Wave w3 = new Wave(Color.GREEN, 5, 50, 300, 0, mContext, this,3);
        Wave w4 = new Wave(Color.GRAY, 5, 50, 280, 0, mContext, this,4);
        mWaves = new ArrayList<Wave>();
        mWaves.add(w1);
        mWaves.add(w2);
        mWaves.add(w3);
        mWaves.add(w4);
    }

}
