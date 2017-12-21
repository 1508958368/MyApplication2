package com.bwei.ssp.home_work.Fragment.Shop.Shop_model;

import com.bwei.ssp.home_work.Fragment.Shop.bean.Shop_Bean;
import com.bwei.ssp.home_work.Okhttputils.GsonObjectCallback;
import com.bwei.ssp.home_work.Okhttputils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

/**
 * Created by lenovo on 2017/12/13.
 */

public class Shop_mclass implements Shop_model {

    OnSendPscidListener listener;

    public void setListener(OnSendPscidListener listener) {
        this.listener = listener;
    }

    public interface OnSendPscidListener{
        void onSendPscid(Shop_Bean bean);
    }

    @Override
    public void setData(int pscid) {
        HashMap<String, String> map = new HashMap<>();
        map.put("pscid",pscid+"");
        OkHttp3Utils.doPost("http://120.27.23.105/product/getProducts", map, new GsonObjectCallback<Shop_Bean>() {
            @Override
            public void onUi(Shop_Bean bean) {
                listener.onSendPscid(bean);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
