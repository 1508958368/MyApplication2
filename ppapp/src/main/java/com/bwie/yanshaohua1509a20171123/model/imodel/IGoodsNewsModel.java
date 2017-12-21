package com.bwie.yanshaohua1509a20171123.model.imodel;

import com.bwie.yanshaohua1509a20171123.bean.AddCartBean;
import com.bwie.yanshaohua1509a20171123.bean.GoodsNewsBean;
import com.bwie.yanshaohua1509a20171123.net.OnNetListener;

import java.util.Map;

/**
 * Created by 闫少华 on 2017/11/23.
 * 接口
 */

public interface IGoodsNewsModel {
    public void getGoodsNews(String url, Map<String,String> map, OnNetListener<GoodsNewsBean> onNetListener);
    public void addToCart(String url, Map<String,String> map, OnNetListener<AddCartBean> onNetListener);
}
