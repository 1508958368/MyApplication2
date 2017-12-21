package com.bawei.chenkai.day14rikao.dingdan.model;

import com.bawei.chenkai.day14rikao.dingdan.API.APi;
import com.bawei.chenkai.day14rikao.dingdan.API.HttpUtils;
import com.bawei.chenkai.day14rikao.dingdan.API.OnNetListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author:Created by WangZhiQiang on 2017/12/20.
 */

public class CancleOrderModel {
    public void cancleOrder(String status, String orderId, OnNetListener onNetListener) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", "71");
        params.put("orderId", orderId);
        params.put("status", status);
        HttpUtils.getHttpUtils().doPost(APi.CANCLE_ORDER, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
