package com.bwie.yanshaohua1509a20171123.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bwie.yanshaohua1509a20171123.R;

/**
 * 闫少华
 * 进入应用实现动画的activity
 */

public class SplashActivity extends AppCompatActivity {

    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        //创建属性动画
        ObjectAnimator a1=ObjectAnimator.ofFloat(mIv, "translationY",-500f, 500);
        ObjectAnimator a2=ObjectAnimator.ofFloat(mIv,"rotation", 0f, 360f);
        ObjectAnimator a3=ObjectAnimator.ofFloat(mIv, "alpha", 0f, 1f);
        ObjectAnimator a4=ObjectAnimator.ofFloat(mIv, "scaleX", 2f, 1f);
        ObjectAnimator a5 = ObjectAnimator.ofFloat(mIv, "scaleY", 2f, 1f);
        //创建动画集合
        AnimatorSet set=new AnimatorSet();
        //设置播放时间
        set.setDuration(3000);
        //一起播放
        set.playTogether(a1,a2,a3,a4,a5);
        //开始
        set.start();
        //设置监听
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //结束后跳转
                Intent intent = new Intent(SplashActivity.this,GoodNewsActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
    }
}
