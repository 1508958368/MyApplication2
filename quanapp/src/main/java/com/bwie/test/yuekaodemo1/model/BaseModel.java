package com.bwie.test.yuekaodemo1.model;

import android.os.Handler;
import android.os.Looper;

import com.bwie.test.yuekaodemo1.bean.BaseBean;
import com.bwie.test.yuekaodemo1.net.Api;
import com.bwie.test.yuekaodemo1.net.OnNetListener;
import com.bwie.test.yuekaodemo1.utils.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author:Created by 董博 on 2017/12/20.
 */

public class BaseModel implements IBaseModel {
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void doCar(final OnNetListener<BaseBean> onNetListener) {
        HttpUtils.getHttpUtils().doGet(Api.getAd, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onNetListener.OnFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        BaseBean baseBean = new Gson().fromJson(string, BaseBean.class);
                        onNetListener.OnSuccess(baseBean);
                    }
                });
            }
        });
    }
}
