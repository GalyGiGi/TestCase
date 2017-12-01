package com.example.zengcheng.myfirstandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zengcheng on 2017/11/30.
 */

public class ReceiveDataActivity extends Activity {
    static final String TAG = "ReceiveDataActivity";
    public static final String EXTRA_DATA = "EXTRA_DATA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            Parcelable parcelable = intent.getParcelableExtra(EXTRA_DATA);
            ParcelData data = (ParcelData) parcelable;
            Log.i(TAG, "data.name:" + data.mName + " ,data.callback:" + data.mCallback + " .data.callbackV2:" + data.mCallbackV2);
            if(data.mCallbackV2!=null){
                data.mCallbackV2.onClick();
            }
        } else {
            Log.i(TAG, "没有数据");

        }
    }
}
