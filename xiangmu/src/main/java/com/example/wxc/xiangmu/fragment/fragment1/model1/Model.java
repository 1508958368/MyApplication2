package com.example.wxc.xiangmu.fragment.fragment1.model1;

import com.example.wxc.xiangmu.fragment.fragment1.bean.Imgs;
import com.example.wxc.xiangmu.utils.GsonObjectCallback;
import com.example.wxc.xiangmu.utils.OkHttp3Utils;

import java.io.IOException;

import okhttp3.Call;

/**
 * author:Created by WangXinChi on 2017/12/15.
 */

public class Model implements Imodel3 {
     SetOnFinsh setonfinsh;

    public interface SetOnFinsh{
        void onFinsh(Imgs imgs);
    }

    public void setSetonfinsh(SetOnFinsh setonfinsh) {
        this.setonfinsh = setonfinsh;
    }

    @Override
    public void onLoad(String url) {
        OkHttp3Utils.doGet(url, new GsonObjectCallback<Imgs>() {
            @Override
            public void onUi(Imgs imgs) {
                if (setonfinsh != null) {
                    setonfinsh.onFinsh(imgs);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

}
