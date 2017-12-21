package com.bawei.chenkai.day14rikao.dingdan.model;

import android.os.Handler;
import android.os.Looper;

import com.bawei.chenkai.day14rikao.dingdan.API.APi;
import com.bawei.chenkai.day14rikao.dingdan.API.HttpUtils;
import com.bawei.chenkai.day14rikao.dingdan.API.OnNetListener;
import com.bawei.chenkai.day14rikao.dingdan.bean.OrderBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author:Created by WangZhiQiang on 2017/12/20.
 */

public class OrderModel implements IOrderModel {
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void getOrder(final String status, String page, final OnNetListener<OrderBean> onNetListener) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", "71");
        params.put("status", status);
        params.put("page", page);
        HttpUtils.getHttpUtils().doPost(APi.ORDER, params, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final OrderBean orderBean = new Gson().fromJson(string, OrderBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(orderBean);
                    }
                });
            }
        });
    }
}

