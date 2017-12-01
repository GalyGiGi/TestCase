package com.example.zengcheng.myfirstandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by zengcheng on 2017/9/5.
 */

public class MyGroup extends LinearLayout {
    private static String TAG = "MyGroup";

    public MyGroup(Context context) {
        super(context);
    }

    public MyGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG,"MyGroup---measureChildWithMargins---");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG,"MyGroup---measureChildWithMargins---");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        int specMode = MeasureSpec.getMode(parentWidthMeasureSpec);
        int specSize = MeasureSpec.getSize(parentWidthMeasureSpec);
        String specModeStr = specMode == MeasureSpec.EXACTLY ? "EXACTLY" : specMode == MeasureSpec.AT_MOST ? "AT_MOST" : "UNSPECIFIED";
//        Log.i(TAG, "specMode:" + specModeStr + " , specSize:" + specSize + " ,widthUsed:" + widthUsed);
        Log.i(TAG,"MyGroup---measureChildWithMargins---");
        super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }
}
