package com.example.wxc.gouwuche.net;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 闫少华 on 2017/11/23.
 * //自定义拦截器封装公共参数
 */

public class MyIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取之前请求
        Request request = chain.request();
        //获取参数循环存入新的formbody
        FormBody oldBody = (FormBody) request.body();
        FormBody.Builder builder = new FormBody.Builder();
        for (int i = 0; i < oldBody.size(); i++) {
            builder.add(oldBody.name(i),oldBody.value(i));
        }
        builder.add("source","android");
        FormBody newbody = builder.build();
        //生成新的请求
        Request request1 = request.newBuilder().url(request.url()).post(newbody).build();
        return chain.proceed(request1);
    }
}
