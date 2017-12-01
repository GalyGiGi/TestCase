package com.example.zengcheng.myfirstandroid;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengcheng on 2017/8/28.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleHolder> {
    private List<String> mAllDevice = new ArrayList<>();
    private RecyclerView mRecyclerView;

    public SimpleAdapter(RecyclerView recyclerview) {
        mRecyclerView = recyclerview;
    }


    @Override
    public SimpleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("SimpleAdapter", "---onCreateViewHolder---");
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.control_card, parent, false);
        return new SimpleHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleHolder holder, int position) {
        Log.i("SimpleAdapter", "---onBindViewHolder---position:" + position);
        holder.setContent(mRecyclerView.getContext(), mAllDevice.get(position));

    }

    @Override
    public void onViewRecycled(SimpleHolder holder) {
        holder.clearContent();
    }

    @Override
    public int getItemCount() {
        return mAllDevice.size();
    }

    public void setDeviceData(List<String> commonUseDevices) {
        mAllDevice.clear();
        mAllDevice.addAll(commonUseDevices);
    }
}
