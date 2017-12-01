package com.example.zengcheng.myfirstandroid;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.zengcheng.myfirstandroid.view.PieChart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by zengcheng on 2017/9/13.
 */

public class ActTestPieChart extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_piechart);
        Resources res = getResources();
        final PieChart pie = (PieChart) this.findViewById(R.id.Pie);
        pie.addItem("Agamemnon", 2, res.getColor(R.color.seafoam));
        pie.addItem("Bocephus", 3.5f, res.getColor(R.color.chartreuse));
        pie.addItem("Calliope", 2.5f, res.getColor(R.color.emerald));
        pie.addItem("Daedalus", 3, res.getColor(R.color.bluegrass));
        pie.addItem("Euripides", 1, res.getColor(R.color.turquoise));
        pie.addItem("Ganymede", 3, res.getColor(R.color.slate));

        (findViewById(R.id.Reset)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pie.setCurrentItem(0);
                test();
            }
        });
    }

    private void test() {
        Set<Integer> checkedDevice = new HashSet<>();
        checkedDevice.add(2);
        checkedDevice.add(1);
        checkedDevice.add(0);
        checkedDevice.add(3);
        checkedDevice.add(9);
        checkedDevice.add(8);
        checkedDevice.add(5);
        checkedDevice.add(6);
        checkedDevice.add(2);
        checkedDevice.add(2);
        Integer[] sortedIndex = new Integer[]{};
        sortedIndex = checkedDevice.toArray(sortedIndex);
        Arrays.sort(sortedIndex);
        Object a = null;
        Object b= null;
        boolean isEqual = a==b;
        a.getClass();
        Map map;
        int i = 0;
    }
}
