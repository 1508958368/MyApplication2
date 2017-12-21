package com.bwie.yanshaohua1509a20171123.model.imodel;

import com.bwie.yanshaohua1509a20171123.bean.GoodsBean;
import com.bwie.yanshaohua1509a20171123.net.OnNetListener;

import java.util.Map;

/**
 * Created by 闫少华 on 2017/11/23.
 * 获取购物车列表接口
 */

public interface IGoodsCartModel {
    public void getGoods(String url, Map<String,String> map, OnNetListener<GoodsBean> onNetListener);
}
