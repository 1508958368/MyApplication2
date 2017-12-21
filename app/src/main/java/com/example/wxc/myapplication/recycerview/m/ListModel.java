package com.example.wxc.myapplication.recycerview.m;

import com.example.wxc.myapplication.bean.Goods;
import com.example.wxc.myapplication.utils.GsonObjectCallback;
import com.example.wxc.myapplication.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;

/**
 * author:Created by WangXinChi on 2017/12/12.
 */

public class ListModel implements Imodel1 {
    Goods goods = new Goods();
    private OnFinish onFinish;
    public  interface OnFinish{
        void onFinishLisenter(Goods goods);
    }
    public  void setOnFinish(OnFinish onFinish){
        this.onFinish =onFinish;
    }



    @Override
    public void getUrl(String url, Map<String, String> mmap) {
        OkHttp3Utils.doPost(url, mmap, new GsonObjectCallback<Goods>() {
            @Override
            public void onUi(Goods goods) {
                onFinish.onFinishLisenter(goods);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
