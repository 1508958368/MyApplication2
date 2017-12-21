package com.bwie.yanshaohua1509a20171123.model;

import android.os.Handler;
import android.os.Looper;

import com.bwie.yanshaohua1509a20171123.bean.AddCartBean;
import com.bwie.yanshaohua1509a20171123.bean.GoodsNewsBean;
import com.bwie.yanshaohua1509a20171123.model.imodel.IGoodsNewsModel;
import com.bwie.yanshaohua1509a20171123.net.NetUtil;
import com.bwie.yanshaohua1509a20171123.net.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 闫少华on 2017/11/23.
 * 进行数据请求 返回是否成功
 */

public class GoodsNewsModel implements IGoodsNewsModel{
    //创建Handler对象
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void getGoodsNews(String url, Map<String, String> map, final OnNetListener<GoodsNewsBean> onNetListener) {
        //调用工具类请求数据
        NetUtil.getNetUtil().doPost(url, map, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //回调接口返回
                        onNetListener.onFail(e);
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //解析字符串
                final GoodsNewsBean goodsNewsBean = new Gson().fromJson(response.body().string(), GoodsNewsBean.class);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //回调接口返回
                        onNetListener.onSuccess(goodsNewsBean);
                    }
                });
            }
        });
    }

    @Override
    public void addToCart(String url, Map<String, String> map, final OnNetListener<AddCartBean> onNetListener) {
        //调用工具类请求数据
        NetUtil.getNetUtil().doPost(url, map, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //回调接口返回
                        onNetListener.onFail(e);
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //解析字符串
                final AddCartBean addCartBean = new Gson().fromJson(response.body().string(), AddCartBean.class);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //回调接口返回
                        onNetListener.onSuccess(addCartBean);
                    }
                });
            }
        });
    }
}
