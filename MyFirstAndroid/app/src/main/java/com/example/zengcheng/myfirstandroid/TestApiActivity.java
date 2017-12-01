package com.example.zengcheng.myfirstandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zengcheng on 2017/10/19.
 */

public class TestApiActivity extends Activity {
    private static String TAG = "TestApiActivity";
    private ParcelData data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test_api);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                testSet();
                testParcel();
            }
        });
    }

    private void testSet() {
        Set<Data> set;
        List<Data> source1 = new ArrayList<>();
        source1.add(new Data("123"));
        source1.add(new Data("456"));
        source1.add(new Data("789"));
        source1.add(new Data("123"));
        set = new HashSet<>(source1);
        for (Data d : set) {
            Log.i(TAG, "遍历set:" + d + " ,d.did:" + d.did);
        }
    }

    private void testParcel() {
        data = new ParcelData();
        data.mName = "zc_test_parcel";
        data.mCallback = new ICallback() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public void onClick() throws RemoteException {
                Log.i(TAG, "---onClick---");
            }

            @Override
            public IBinder asBinder() {
                return null;
            }
        };
        data.mCallbackV2 = new ICallbackV2() {
            @Override
            public void onClick() {
                Log.i(TAG, "---mCallbackV2.onClick---");
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {

            }
        };
        Intent intent = new Intent(this, ReceiveDataActivity.class);
        intent.putExtra(ReceiveDataActivity.EXTRA_DATA, data);
        startActivity(intent);
    }

    class Data {
        String did;

        public Data(String did) {
            this.did = did;
        }

        @Override
        public int hashCode() {
            return did.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Data)) {
                return false;
            }
            Data other = (Data) obj;
            return other.did.equals(this.did);
        }
    }
}
