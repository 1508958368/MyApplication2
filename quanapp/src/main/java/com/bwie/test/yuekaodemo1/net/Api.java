package com.bwie.test.yuekaodemo1.net;

/**
 * author:Created by 董博 on 2017/12/19.
 */

public interface Api {
    //商品详情接口
    public static final String GOOD_XIANG="https://www.zhaoapi.cn/product/getProductDetail";
    //加入购物车接口
    public static final String ADDCART="https://www.zhaoapi.cn/product/addCart";
    //购物车列表接口
    public static final String ShowCar="https://www.zhaoapi.cn/product/getCarts";
    public static final String getAd="http://120.27.23.105/ad/getAd";
    //订单列表
    public static final String ORDER = "https://www.zhaoapi.cn/product/getOrders";//uid
    //修改订单
    public static final String CANCLE_ORDER = "https://www.zhaoapi.cn/product/updateOrder";
}
