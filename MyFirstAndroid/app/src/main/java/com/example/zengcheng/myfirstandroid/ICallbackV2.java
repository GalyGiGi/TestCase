package com.example.zengcheng.myfirstandroid;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zengcheng on 2017/11/30.
 */

public class ICallbackV2 implements Parcelable {
    public ICallbackV2(){}
    public void onClick() {
    }

    public static final Creator<ICallbackV2> CREATOR = new Creator<ICallbackV2>() {
        public ICallbackV2 createFromParcel(Parcel in) {
            return new ICallbackV2(in);
        }

        public ICallbackV2[] newArray(int size) {
            return new ICallbackV2[size];
        }

    };

    public ICallbackV2(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
