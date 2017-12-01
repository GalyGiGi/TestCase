package com.example.zengcheng.myfirstandroid;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigInteger;

/**
 * Created by zengcheng on 2017/4/13.
 */

public class ActScrollViewTest extends Activity {
    FrameLayout layoutContent;
    View scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scroll);
        layoutContent = (FrameLayout) findViewById(R.id.layout_frame);
        BigInteger bt;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void show(View view) {
        if (scrollView != null) {
            layoutContent.removeView(scrollView);
            scrollView = null;
        } else {
            scrollView = LayoutInflater.from(this).inflate(R.layout.quick_setting, null);
            layoutContent.addView(scrollView);
        }
    }

    public void showDynamic(View v) {
        if (scrollView != null) {
            layoutContent.removeView(scrollView);
            scrollView = null;
        } else {
            scrollView = LayoutInflater.from(this).inflate(R.layout.quick_setting, null);
            HorizontalScrollView realScrollView = (HorizontalScrollView) scrollView.findViewById(R.id.scroll_view);
            LinearLayout linearLayout = buildSettingLayout();
            for (int i = 0; i < 4; i++) {
                TextView textView = buildSettingTextView();
                textView.setText("aa");
                if (i > 0) {
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textView.getLayoutParams();
                    params.setMargins((int) getResources().getDimension(R.dimen.margin_left_quik_setting), 0, 0, 0);
                }
                linearLayout.addView(textView);
            }
            realScrollView.addView(linearLayout);
            layoutContent.addView(scrollView);

        }
    }

    public void showHalfDyn(View v) {
        if (scrollView != null) {
            layoutContent.removeView(scrollView);
            scrollView = null;
        } else {
            scrollView = LayoutInflater.from(this).inflate(R.layout.quick_setting, null);
            LinearLayout linearLayout = (LinearLayout) scrollView.findViewById(R.id.linear);
            for (int i = 0; i < 3; i++) {
                TextView textView = buildSettingTextView();
                textView.setText("aaabbb");
                if (i > 0) {
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textView.getLayoutParams();
                    params.setMargins((int) getResources().getDimension(R.dimen.margin_left_quik_setting), 0, 0, 0);
                }
                linearLayout.addView(textView);
            }
            layoutContent.addView(scrollView);
        }
    }

    private LinearLayout buildSettingLayout() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) getResources().getDimension(R.dimen.height_quick_setting_view));
        params.gravity = Gravity.CENTER_HORIZONTAL;
        linearLayout.setLayoutParams(params);
        return linearLayout;
    }

    private TextView buildSettingTextView() {
        TextView textView = new TextView(this);
        Resources res = getResources();
        textView.setTextColor(res.getColor(R.color.quick_setting_default));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimensionPixelSize(R.dimen.text_size_quick_setting));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        textView.setLayoutParams(params);
        return textView;
    }
}
