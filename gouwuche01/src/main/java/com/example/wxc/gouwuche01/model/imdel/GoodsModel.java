package com.example.wxc.gouwuche01.model.imdel;

import android.os.Handler;
import android.os.Looper;

import com.example.wxc.gouwuche01.bean.GoodsBean;
import com.example.wxc.gouwuche01.net.Netutils;
import com.example.wxc.gouwuche01.net.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author:Created by WangXinChi on 2017/12/20.
 */

public class GoodsModel implements IGoodsModel {
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void getGoods(String url, Map<String, String> map, final OnNetListener<GoodsBean> onNetListener) {
        Netutils.getNetutils().doPost(url,map, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onFail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final GoodsBean goodsBean = new Gson().fromJson(string,GoodsBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(goodsBean);
                    }
                });
            }
        });
    }
}
