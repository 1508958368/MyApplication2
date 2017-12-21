package com.bawei.chenkai.day14rikao.dingdan.API;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * author:Created by WangZhiQiang on 2017/12/20.
 */

public class HttpUtils {
    private static HttpUtils httpUtils;
    private final OkHttpClient client;

    private HttpUtils() {
        //创建OkHttpClient
        //创建拦截器

        client = new OkHttpClient.Builder()
                .addInterceptor(new MyInterceptor())
                .build();
    }

    public static HttpUtils getHttpUtils() {
        if (httpUtils == null) {
            synchronized (HttpUtils.class) {
                if (httpUtils == null) {
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    /**
     * GET请求
     *
     * @param url      请求地址
     * @param callback 回调
     */
    public void doGet(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * POST请求
     *
     * @param url      请求地址
     * @param params   请求的参数
     * @param callback 回调
     */
    public void doPost(String url, Map<String, String> params, Callback callback) {
        //这里可以加网络判断

        //判断参数
        if (params == null || params.size() == 0) {
            throw new RuntimeException("params is null！！！");
        }
        // 创建Request
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
