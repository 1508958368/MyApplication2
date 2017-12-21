package com.bwie.test.demo2.model;

import android.os.Handler;
import android.os.Looper;

import com.bwie.test.demo2.bean.GetCartBean;
import com.bwie.test.demo2.net.Api;
import com.bwie.test.demo2.net.OkHttpUtils;
import com.bwie.test.demo2.net.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author:Created by 董博 on 2017/12/17.
 */

public class GetCartModel implements GetCartService {
    private Handler handler = new Handler(Looper.getMainLooper());


    @Override
    public void getCart(Map<String, String> params, final OnNetListener<GetCartBean> onNetListener) {
        OkHttpUtils.getOkHttpUtils().doPost(Api.str2, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final GetCartBean getCartBean = new Gson().fromJson(string, GetCartBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(getCartBean);
                    }
                });
            }
        });
    }
}
