package com.example.zengcheng.myfirstandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.example.zengcheng.myfirstandroid.view.MyWaveView2;

/**
 * Created by zengcheng on 2017/12/1.
 */

public class ActWaveView extends AppCompatActivity {

    private MyWaveView2 myview;
    private SeekBar seekbar;
    private SeekBar seekbarspeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_wave_view);
        initView();
        initEvent();
    }

    private void initEvent() {

//        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                myview.setWaveHeight(progress);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//
//        seekbarspeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                myview.setWaveSpeed(progress);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

    }

    private void initView() {
        myview = (MyWaveView2) findViewById(R.id.myview);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        seekbarspeed = (SeekBar) findViewById(R.id.seekbarspeed);

//        seekbar.setProgress(myview.getWaveHeight());
//        seekbarspeed.setProgress(myview.getWaveSpeed());
    }

}
