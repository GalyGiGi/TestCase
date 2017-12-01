package com.example.zengcheng.myfirstandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by zengcheng on 2017/4/6.
 */

public class ResultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    public void setResultForBack(View view){
        setResult(6);
        finish();
    }
}
