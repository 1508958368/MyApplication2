package com.bwie.yanshaohua1509a20171123.precenter;

import com.bwie.yanshaohua1509a20171123.bean.GoodsBean;
import com.bwie.yanshaohua1509a20171123.model.GoodsCartModel;
import com.bwie.yanshaohua1509a20171123.model.imodel.IGoodsCartModel;
import com.bwie.yanshaohua1509a20171123.net.API;
import com.bwie.yanshaohua1509a20171123.net.OnNetListener;
import com.bwie.yanshaohua1509a20171123.view.iview.IShopCartActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 闫少华 on 2017/11/23.
 * 实现购物车逻辑的P层
 */

public class GoodsCartPrecenter {
    private IGoodsCartModel goodsCartModel;
    private IShopCartActivity shopCartActivity;

    public GoodsCartPrecenter(IShopCartActivity shopCartActivity) {
        this.shopCartActivity = shopCartActivity;
        goodsCartModel = new GoodsCartModel();
    }

    public void getGoods(){
        Map<String, String> map = new HashMap<>();
        map.put("uid","72");
        goodsCartModel.getGoods(API.CART_URL, map, new OnNetListener<GoodsBean>() {
            @Override
            public void onSuccess(GoodsBean goodsBean) {
                shopCartActivity.showNews(goodsBean);
            }

            @Override
            public void onFail(Exception e) {
                shopCartActivity.show(e+"加载失败");
            }
        });
    }

    //解决内存泄漏
    public void Dettouch() {
        if (shopCartActivity != null) {
            shopCartActivity = null;
        }
    }
}
