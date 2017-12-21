package com.example.wxc.gouwuche01.model.imdel;

import com.example.wxc.gouwuche01.bean.GoodsBean;
import com.example.wxc.gouwuche01.net.OnNetListener;

import java.util.Map;

/**
 * author:Created by WangXinChi on 2017/12/20.
 */

public interface IGoodsModel {
    public void getGoods(String url, Map<String,String>map, OnNetListener<GoodsBean> onNetListener);
}
