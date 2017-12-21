package com.example.wxc.gouwuche.net;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 我走路带风 on 2017/11/23.
 * //封装网络请求 get post
 */

public class NetUtil {
    private static NetUtil netUtil;
    private OkHttpClient client;

    //构造
    public NetUtil() {
        //创建okhttp加入拦截器
        client = new OkHttpClient.Builder().addInterceptor(new MyIntercepter()).build();
    }
    //单例模式创建对象
    public static NetUtil getNetUtil(){
        if (netUtil == null){
            synchronized (NetUtil.class){
                if (netUtil==null){
                    netUtil = new NetUtil();
                }
            }
        }
        return netUtil;
    }
    //get请求
    public void doGet(String url, Callback callback){
        //创建请求
        Request build = new Request.Builder().url(url).build();
        //发送请求
        client.newCall(build).enqueue(callback);
    }
    //post请求
    public void doPost(String url, Map<String,String> map,Callback callback){
        //创建formbody循环遍历map集合加入参数
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String,String> entry:map.entrySet()) {
            builder.add(entry.getKey(),entry.getValue());
        }
        FormBody build = builder.build();
        //创建请求
        Request request = new Request.Builder().url(url).post(build).build();
        //发送请求
        client.newCall(request).enqueue(callback);
    }
}
