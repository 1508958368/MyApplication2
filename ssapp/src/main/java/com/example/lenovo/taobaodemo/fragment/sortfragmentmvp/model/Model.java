package com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.model;

import android.util.Log;

import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.GoodsList;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortRithtChildBean;

import java.io.IOException;

import okhttp3.Call;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

/**
 * author:Created by WangZhiQiang on 2017/12/13.
 */

public class Model implements IModel {

    private onFinishListenner onFinishListenner;

    public interface onFinishListenner {
        void onFinsh(SortBean sortBean, int i);

        void onGoodsListFinsh(GoodsList goodsList, int i);

        void onChildFinsh(SortRithtChildBean sortRithtChildBean, int i);
    }

    public void setOnFinishListenner(onFinishListenner onFinishListenner) {
        this.onFinishListenner = onFinishListenner;

    }

    ;

    //获取数据
    @Override
    public void onLoad(String url, int i) {
        if (i == 0) {
            OkHttp3Utils.doGet(url, new GsonObjectCallback<SortBean>() {
                @Override
                public void onUi(SortBean sortBean) {
                    if (onFinishListenner != null) {
                        onFinishListenner.onFinsh(sortBean, 0);
                    }
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });
        } else if (i == 1) {
            Log.e("Iv", url + "");
            OkHttp3Utils.doGet(url, new GsonObjectCallback<SortRithtChildBean>() {
                @Override
                public void onUi(SortRithtChildBean sortRithtChildBean) {
                    if (onFinishListenner != null) {
                        onFinishListenner.onChildFinsh(sortRithtChildBean, 1);
                    }

                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });

        } else if (i == 2) {
            OkHttp3Utils.doGet(url, new GsonObjectCallback<GoodsList>() {
                @Override
                public void onUi(GoodsList goodsList) {

                    if (onFinishListenner != null) {

                        onFinishListenner.onGoodsListFinsh(goodsList, 2);
                    }
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });

        }

    }
}
