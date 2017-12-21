package com.example.wxc.gouwuche.model;


import com.example.wxc.gouwuche.bean.GoodsBean;
import com.example.wxc.gouwuche.net.OnNetListener;

import java.util.Map;

/**
 * Created by 闫少华 on 2017/11/23.
 * 获取购物车列表接口
 */

public interface IGoodsCartModel {
    public void getGoods(String url, Map<String, String> map, OnNetListener<GoodsBean> onNetListener);
}
