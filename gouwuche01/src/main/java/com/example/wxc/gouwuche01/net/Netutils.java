package com.example.wxc.gouwuche01.net;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * author:Created by WangXinChi on 2017/12/20.
 */

public class Netutils {
    private static Netutils netutils;
    private OkHttpClient okHttpClient;

    public Netutils() {
        okHttpClient = new OkHttpClient.Builder().addInterceptor(new MyIntercepter()).build();
    }

    public static Netutils getNetutils() {
        if (netutils == null) {
            synchronized (Netutils.class) {
                if (netutils == null) {
                    netutils = new Netutils();
                }
            }
        }
        return netutils;
    }

    public void doGet(String url, Callback callback) {
        //创建请求
        Request build = new Request.Builder().url(url).build();
        //发送请求
        okHttpClient.newCall(build).enqueue(callback);
    }

    //post请求
    public void doPost(String url, Map<String, String> map, Callback callback) {
        //创建formbody循环遍历map集合加入参数
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        FormBody build = builder.build();
        //创建请求
        Request request = new Request.Builder().url(url).post(build).build();
        //发送请求
        okHttpClient.newCall(request).enqueue(callback);
    }
}
