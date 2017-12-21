package com.example.wxc.gouwuche01.model.imdel;

import com.example.wxc.gouwuche01.bean.AddBean;
import com.example.wxc.gouwuche01.bean.GoodNewsBean;
import com.example.wxc.gouwuche01.net.OnNetListener;

import java.util.Map;

/**
 * author:Created by WangXinChi on 2017/12/20.
 */

public interface IGoodsNews {
    public void getGoodsNews(String url, Map<String,String>map, OnNetListener<GoodNewsBean> onNetListener);
    public void addToCart(String url, Map<String,String>map, OnNetListener<AddBean> onNetListener);
}
