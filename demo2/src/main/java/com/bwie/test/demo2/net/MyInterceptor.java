package com.bwie.test.demo2.net;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/12/12.
 */
public class MyInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request orgin=chain.request();
        FormBody body= (FormBody) orgin.body();
        FormBody.Builder builder=new FormBody.Builder();
        for(int i=0;i<body.size();i++){
            String name=body.name(i);
            String values=body.value(i);
            builder.add(name,values);
        }
        builder.add("source","android");
        FormBody formBody=builder.build();
        Request request=chain.request().newBuilder().url(orgin.url()).post(formBody).build();

        return chain.proceed(request);
    }
}
