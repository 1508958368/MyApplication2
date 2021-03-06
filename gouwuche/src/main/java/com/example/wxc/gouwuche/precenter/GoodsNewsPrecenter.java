package com.example.wxc.gouwuche.precenter;


import com.example.wxc.gouwuche.bean.AddCartBean;
import com.example.wxc.gouwuche.bean.GoodsNewsBean;
import com.example.wxc.gouwuche.model.GoodsNewsModel;
import com.example.wxc.gouwuche.model.IGoodsNewsModel;
import com.example.wxc.gouwuche.net.API;
import com.example.wxc.gouwuche.net.OnNetListener;
import com.example.wxc.gouwuche.view.GoodNewsActivity;
import com.example.wxc.gouwuche.view.IGoodsNewsActivity;

import java.util.HashMap;

/**
 * Created by 闫少华 on 2017/11/23.
 * 接收数据进行处理
 */

public class GoodsNewsPrecenter {
    private IGoodsNewsModel goodsNewsModel;
    private IGoodsNewsActivity goodsNewsActivity;

    public GoodsNewsPrecenter(GoodNewsActivity goodsNewsActivity) {
        this.goodsNewsActivity = goodsNewsActivity;
        goodsNewsModel = new GoodsNewsModel();
    }

    //获取商品详情
    public void getGoodsNews() {
        //封装参数
        HashMap<String, String> map = new HashMap<>();
        map.put("pid", "1");
        //调用m层方法获取数据
        goodsNewsModel.getGoodsNews(API.GOOD_URL, map, new OnNetListener<GoodsNewsBean>() {
            @Override
            public void onSuccess(GoodsNewsBean goodsNewsBean) {
                //进行数据展示
                goodsNewsActivity.showNews(goodsNewsBean);
            }

            @Override
            public void onFail(Exception e) {
                //吐司异常
                goodsNewsActivity.show(e + "数据请求失败!");
            }
        });
    }

    //添加商品到购物车
    public void addToCart(int pid) {
        //封装参数
        HashMap<String, String> map = new HashMap<>();
        map.put("pid",pid+"");
        map.put("uid", "71");

        //调用m层方法获取数据
        goodsNewsModel.addToCart(API.GOOD_URL, map, new OnNetListener<AddCartBean>() {
            @Override
            public void onSuccess(AddCartBean addCartBean) {
                //进行数据展示
                //获取加购是否成功 进行toast吐司
                goodsNewsActivity.show(addCartBean.getMsg()+"加购成功!");
            }

            @Override
            public void onFail(Exception e) {
                //吐司异常
                goodsNewsActivity.show(e + "数据请求失败!");
            }
        });
    }

    //解决内存泄漏
    public void Dettouch() {
        if (goodsNewsActivity != null) {
            goodsNewsActivity = null;
        }
    }

}
