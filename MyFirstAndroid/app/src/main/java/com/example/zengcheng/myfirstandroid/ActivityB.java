package com.example.zengcheng.myfirstandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Arrays;

/**
 * Created by zengcheng on 2017/8/7.
 */

public class ActivityB extends Activity {
    private final static String[] DEVICES = {"米家HIFI跑鞋", "小米智能插座", "墙壁双键开关", "智米智能马桶盖", "米家小白摄像机", "飞米相机", "AI音箱", "小方摄像机","智能牙膏","智能苹果","皮卡丘"};

    private SimpleAdapter sliderAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_b);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sliderAdapter = new SimpleAdapter(recyclerView);
        recyclerView.setAdapter(sliderAdapter);
        sliderAdapter.setDeviceData(Arrays.asList(DEVICES));
    }
    @Override
    public void onBackPressed() {
        if(recyclerView.getVisibility()== View.VISIBLE){
            recyclerView.setVisibility(View.INVISIBLE);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.scrollToPosition(0);
        }
//        super.onBackPressed();
    }
}
