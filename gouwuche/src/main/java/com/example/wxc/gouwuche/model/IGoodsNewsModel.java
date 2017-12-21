package com.example.wxc.gouwuche.model;


import com.example.wxc.gouwuche.bean.AddCartBean;
import com.example.wxc.gouwuche.bean.GoodsNewsBean;
import com.example.wxc.gouwuche.net.OnNetListener;

import java.util.Map;

/**
 * Created by 闫少华 on 2017/11/23.
 * 接口
 */

public interface IGoodsNewsModel {
    public void getGoodsNews(String url, Map<String, String> map, OnNetListener<GoodsNewsBean> onNetListener);
    public void addToCart(String url, Map<String, String> map, OnNetListener<AddCartBean> onNetListener);
}
