package com.example.zengcheng.myfirstandroid;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zengcheng on 2017/11/30.
 */

public class ParcelData implements Parcelable {
    public String mName;
    public ICallback mCallback;
    public ICallbackV2 mCallbackV2;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
//        if (mCallback != null) {
//            dest.writeInt(1);
//            dest.writeStrongBinder(mCallback.asBinder());
//        } else {
//            dest.writeInt(0);
//        }
        dest.writeStrongBinder(mCallback.asBinder());
        dest.writeParcelable(mCallbackV2, 0);
    }

    public static final Creator<ParcelData> CREATOR = new Creator<ParcelData>() {
        public ParcelData createFromParcel(Parcel in) {
            return new ParcelData(in);
        }

        public ParcelData[] newArray(int size) {
            return new ParcelData[size];
        }

    };

    public ParcelData(Parcel in) {
        mName = in.readString();

//        int hasBinder = in.readInt();
//        if (hasBinder != 0) {
//            IBinder binder = in.readStrongBinder();
//            mCallback = ICallback.Stub.asInterface(binder);
//        }
        IBinder binder = in.readStrongBinder();
        if (binder != null) {
            mCallback = ICallback.Stub.asInterface(binder);
        }
        Parcelable p = in.readParcelable(ICallbackV2.class.getClassLoader());
        if (p != null) {
            mCallbackV2 = (ICallbackV2) p;
        }

    }

    public ParcelData() {
    }
}
