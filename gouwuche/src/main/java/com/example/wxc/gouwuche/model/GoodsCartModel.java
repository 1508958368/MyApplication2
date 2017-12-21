package com.example.wxc.gouwuche.model;

import android.os.Handler;
import android.os.Looper;

import com.example.wxc.gouwuche.bean.GoodsBean;
import com.example.wxc.gouwuche.net.NetUtil;
import com.example.wxc.gouwuche.net.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by 我走路带风 on 2017/11/23.
 */

public class GoodsCartModel implements IGoodsCartModel {
    //创建Handler对象
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void getGoods(String url, Map<String, String> map, final OnNetListener<GoodsBean> onNetListener) {
        //调用网络请求工具类获取数据
        NetUtil.getNetUtil().doPost(url, map, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //发送到主线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onFail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //解析数据
                String string = response.body().string();
                final GoodsBean goodsBean = new Gson().fromJson(string, GoodsBean.class);
                //发送到主线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //回调返回数据
                       onNetListener.onSuccess(goodsBean);
                    }
                });
            }
        });
    }
}
