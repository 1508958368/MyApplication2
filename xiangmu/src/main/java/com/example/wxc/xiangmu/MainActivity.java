package com.example.wxc.xiangmu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wxc.xiangmu.fragment.Fragment2;
import com.example.wxc.xiangmu.fragment.Fragment3;
import com.example.wxc.xiangmu.fragment.Fragment4;
import com.example.wxc.xiangmu.fragment.fragment1.Fragment1;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {
    private BottomTabBar mb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mb=(BottomTabBar)findViewById(R.id.bottom_tab_bar);

        mb.init(getSupportFragmentManager())
                .setImgSize(100,100)
                .setFontSize(20)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED,Color.DKGRAY)
                .addTabItem("首页",R.drawable.ac0, Fragment1.class)
                .addTabItem("分类",R.drawable.abw, Fragment2.class)
                .addTabItem("购物车",R.drawable.abu, Fragment3.class)
                .addTabItem("我的",R.drawable.ac2, Fragment4.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });

    }
    }

