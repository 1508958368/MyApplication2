package com.bwei.ssp.home_work.okhttp;

import android.os.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * ���޽�
 */

public class OkHttpUtils {
    private Handler handler = new Handler();
    public Handler getHandler(){
        return handler;
    }
    //����
    private static OkHttpUtils okHttpUtils = new OkHttpUtils();
    private OkHttpUtils(){};
    public static OkHttpUtils getInstance(){
        return okHttpUtils;
    }

    private OkHttpClient client;
    private void initOkHttpClient(){
        if(client == null){
            client = new OkHttpClient.Builder().build();
        }
    }

    //���õ�get���󷽷�  ��ɵĹ��ܲ�ȷ��
    public void doGet(String url, Callback callback){
        initOkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}