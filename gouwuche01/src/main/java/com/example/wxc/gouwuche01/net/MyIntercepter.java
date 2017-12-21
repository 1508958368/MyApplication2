package com.example.wxc.gouwuche01.net;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author:Created by WangXinChi on 2017/12/20.
 */

public class MyIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        FormBody oldBody = (FormBody) request.body();
        FormBody.Builder builder = new FormBody.Builder();
        for (int i = 0; i <oldBody.size(); i++) {
            builder.add(oldBody.name(i),oldBody.value(i));
        }
        builder.add("source", "android");
        FormBody newbody = builder.build();
        Request request1 = request.newBuilder().url(request.url()).post(newbody).build();

        return chain.proceed(request);
    }
}
