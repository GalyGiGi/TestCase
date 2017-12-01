package com.example.zengcheng.myfirstandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

/**
 * Created by zengcheng on 2017/8/7.
 */

public class ActivityA extends Activity {
    private static String TAG = "ActivityA";
    ViewGroup group;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_a);
        group = (ViewGroup) findViewById(R.id.anim);
    }

    public void jump(View view) {
        Intent intent = new Intent(this, ActivityB.class);
        startActivity(intent);
    }

    public void showAnim(View view) {
        startLayoutAnim(group);
    }

    private void startLayoutAnim(final ViewGroup viewGroup) {//卡片一张一张出来的动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_item_card);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                for (int i = 0; i < mRecycleView.getChildCount() - 1; i++) {
//                    View view = mRecycleView.getChildAt(i);
//                    if(mRecycleView.getLayoutManager().getPosition(view)==currentPosition){
//                        view.getAnimation().cancel();
//                        break;
//                    }
//                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                View card = findViewById(R.id.temp_card_for_splash);
//                card.setVisibility(View.INVISIBLE);
                Log.i(TAG, "---onAnimationEnd---");
                group.setVisibility(View.INVISIBLE);
//                if (firstEnd) {
//                    hideAnimCard();
//                    firstEnd = false;
//                }
//                for (int i = 0; i < mRecycleView.getChildCount() - 1; i++) {
//                    View view = mRecycleView.getChildAt(i);
//                    view.getAnimation().cancel();
//
////                    if(mRecycleView.getLayoutManager().getPosition(view)==currentPosition){
////                        break;
////                    }
//                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        final LayoutAnimationController controller = new LayoutAnimationController(animation, 0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_REVERSE);   //设置控件显示间隔时间；
//        firstEnd = true;
        viewGroup.setLayoutAnimation(controller);
        viewGroup.scheduleLayoutAnimation();
        viewGroup.setVisibility(View.VISIBLE);
//        View lastView = viewGroup.getChildAt(viewGroup.getChildCount()-1);
//        controller.getAnimationForView(lastView).cancel();
//        animation.start();
//        Animation animation = new TranslateAnimation(0,0,80,0);
//        animation.setDuration(1000);
//        LayoutAnimationController controller = new LayoutAnimationController(animation,0.5f);
//        controller.setOrder(LayoutAnimationController.ORDER_REVERSE);
//        viewGroup.setLayoutAnimation(controller);
    }
}
