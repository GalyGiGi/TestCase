package com.example.zengcheng.myfirstandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


/**
 * Created by zengcheng on 2017/8/28.
 */

public class SimpleHolder extends RecyclerView.ViewHolder {
    private TextView titleView;

    public SimpleHolder(View itemView) {
        super(itemView);
        titleView = (TextView) itemView.findViewById(R.id.device_name);

    }

    public void setContent(final Context context, String name) {
        titleView.setText(name);
    }
    public void clearContent() {

    }
}
