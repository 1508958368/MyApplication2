package com.example.wxc.gouwuche.view;


import com.example.wxc.gouwuche.bean.GoodsBean;

/**
 * Created by 闫少华on 2017/11/23.
 * 购物车的借口
 */

public interface IShopCartActivity {
    public void show(String str);
    public void showNews(GoodsBean goodsBean);
}
