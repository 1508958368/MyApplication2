package com.example.lenovo.taobaodemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.taobaodemo.fragment.MyFragment;
import com.example.lenovo.taobaodemo.fragment.PageFragment;
import com.example.lenovo.taobaodemo.fragment.ShoppingFragment;
import com.example.lenovo.taobaodemo.fragment.SortFragment;
import com.hjm.bottomtabbar.BottomTabBar;

public class HomePageActivity extends AppCompatActivity {

    private BottomTabBar menuchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //初始化控件
        initView();
    }

    //初始化控件
    private void initView() {
        menuchange = (BottomTabBar) this.findViewById(R.id.menuchange);
        menuchange.init(getSupportFragmentManager())
                .setImgSize(42, 40)
                .setFontSize(16)
                .setTabPadding(10, 4, 10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("首页",R.mipmap.home, PageFragment.class)
                .addTabItem("分类",R.mipmap.radiowaves, SortFragment.class)
                .addTabItem("购物车",R.mipmap.ios7cartoutline, ShoppingFragment.class)
                .addTabItem("我的",R.mipmap.my, MyFragment.class)
                .isShowDivider(true);
                //导航栏切换监听
//                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
//                    @Override
//                    public void onTabChange(int position, String name) {
//                        Toast.makeText(HomePageActivity.this, position + " " + name, Toast.LENGTH_SHORT).show();
//                    }
//                });


    }
}
