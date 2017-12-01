package com.example.zengcheng.myfirstandroid.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.zengcheng.myfirstandroid.R;

/**
 * Created by zengcheng on 2017/4/28.
 */

public class CircleProgressView extends View {
    private int mColorLineBack = -1;
    private int mColorLinePro = -1;
    private int mColorBack = -1;
    private float mLineWidth = 1;
    private Paint mPaintProgress, mPaintLineBack;
    private int mWidth, mHeight;
    private int mMinWidth;
    private int mCurProgress;
    private float sweepAngle;
    private RectF mRect;

    public CircleProgressView(Context context) {
        super(context);
        initPain();

    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        readAttrs(attrs);
        initPain();
    }

    public void setProgress(int progress) {
        mCurProgress = progress;
        sweepAngle = ((float) mCurProgress / 100) * 360;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(mColorBack);//底色
        canvas.drawArc(mRect, 0, 360, false, mPaintLineBack);
        canvas.drawArc(mRect, -90, sweepAngle, false, mPaintProgress);
    }

    private void initPain() {
        mPaintProgress = new Paint();
        mPaintProgress.setColor(mColorLinePro);
        mPaintProgress.setAntiAlias(true);
        mPaintProgress.setStrokeWidth(mLineWidth);
        mPaintProgress.setStyle(Paint.Style.STROKE);

        mPaintLineBack = new Paint();
        mPaintLineBack.setColor(mColorLineBack);
        mPaintLineBack.setAntiAlias(true);
        mPaintLineBack.setStrokeWidth(mLineWidth);
        mPaintLineBack.setStyle(Paint.Style.STROKE);
    }

    private void readAttrs(AttributeSet attrs) {
        TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.CircleProgressViewAttr);
        mColorBack = t.getColor(R.styleable.CircleProgressViewAttr_back_color, 0xffffff);
        mColorLineBack = t.getColor(R.styleable.CircleProgressViewAttr_back_line_color, 0x000000);
        mColorLinePro = t.getColor(R.styleable.CircleProgressViewAttr_line_progress_color, 0x000000);
        mLineWidth = t.getDimension(R.styleable.CircleProgressViewAttr_circle_width, 1);
        mCurProgress = t.getInt(R.styleable.CircleProgressViewAttr_progress, 50);
        setProgress(mCurProgress);
        t.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        caculateLocation();
    }

    private void caculateLocation() {
        int width = getWidth();
        int height = getHeight();
        if (mWidth == width && mHeight == height && width > 0 && height > 0) {
            return;
        }
        mWidth = width;
        mHeight = height;
        mMinWidth = width > height ? height : width;
        float left = width - mMinWidth > 0 ? (width - mMinWidth) / 2 : 0;
        float top = height - mMinWidth > 0 ? (height - mMinWidth) / 2 : 0;
        float right = width - mMinWidth > 0 ? width - (width - mMinWidth) / 2 : width;
        float bottom = height - mMinWidth > 0 ? height - (height - mMinWidth) / 2 : height;
        left += mLineWidth / 2;
        top += mLineWidth / 2;
        right -= mLineWidth / 2;
        bottom -= mLineWidth / 2;
        if (mRect == null) {
            mRect = new RectF(left, top, right, bottom);
        } else {
            mRect.set(left, top, right, bottom);
        }
    }
}
