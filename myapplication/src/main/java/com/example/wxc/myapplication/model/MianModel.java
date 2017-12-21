package com.example.wxc.myapplication.model;

import com.example.wxc.myapplication.bean.GoodsBean;
import com.example.wxc.myapplication.utils.GsonObjectCallback;
import com.example.wxc.myapplication.utils.OkHttp3Utils;

import java.io.IOException;

import okhttp3.Call;

/**
 * author:Created by WangXinChi on 2017/12/17.
 */

public class MianModel implements IModel{
    public void getData(final GoodsBean callback){
        OkHttp3Utils.doGet(null, "http://120.27.23.105/product/getCarts?uid=100", new GsonObjectCallback<GoodsBean>() {
            @Override
            public void onUi(GoodsBean goodsBean) {
            }

            @Override
            public void onFailed(Call call, IOException e) {
                callback.failure(e);
            }
        });



    }

    @Override
    public void success(GoodsBean bean) {

    }

    @Override
    public void failure(Exception e) {

    }
}
