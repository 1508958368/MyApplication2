package com.bwie.wangxinrun1510b20171211.list.v.m;

import java.io.IOException;
import java.util.Map;

import bean.Goods;
import okhttp3.Call;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

/**
 * author: Wangxinrun
 * Date: 2017/12/12
 * Time: 9:34
 */

public class ListModel implements lmodel {

    Goods goods = new Goods();

    private OnFinish onFinish;

    public interface OnFinish {
        void onFinishListener(Goods goods);
    }

    public void setOnFinish(OnFinish onFinish) {
        this.onFinish = onFinish;
    }


    @Override
    public void getUrl(String url, Map<String, String> mmap) {
        OkHttp3Utils.doPost(url, mmap, new GsonObjectCallback<Goods>() {
            @Override
            public void onUi(Goods goods) {
                onFinish.onFinishListener(goods);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
