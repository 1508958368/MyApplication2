package com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.model;

import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.bean.HeadbannerImg;

import java.io.IOException;

import okhttp3.Call;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

/**
 * author:Created by WangZhiQiang on 2017/12/12.
 */

public class Model implements IModel {

    private SetOnFinsh setonfinish;

    public interface SetOnFinsh {
        void onFinish(HeadbannerImg headbannerImg);
    }

    public void setfinish(SetOnFinsh setonfinish) {
        this.setonfinish = setonfinish;
    }


    @Override
    public void onLoad(String url) {

        OkHttp3Utils.doGet(url, new GsonObjectCallback<HeadbannerImg>() {
            @Override
            public void onUi(HeadbannerImg headbannerImg) {
                if (setonfinish != null) {

                    setonfinish.onFinish(headbannerImg);

                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });


    }
}
