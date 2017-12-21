package com.bwei.ssp.home_work.Login.model;

import com.bwei.ssp.home_work.Login.bean.Bean;
import com.bwei.ssp.home_work.Login.bean.Userbean;
import com.bwei.ssp.home_work.Okhttputils.GsonObjectCallback;
import com.bwei.ssp.home_work.Okhttputils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

/**
 * Created by lenovo on 2017/12/8.
 */

public class Model implements Mymodel{
    OnGetJsListener listener;

    public void setListener(OnGetJsListener listener) {
        this.listener = listener;
    }

    public  interface OnGetJsListener{
    void onGetjs(Userbean userbean);
}

    @Override
    public void setdata(Bean bean) {
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile", bean.getName());
        map.put("password", bean.getPass());
        OkHttp3Utils.doPost("http://120.27.23.105/user/login", map, new GsonObjectCallback<Userbean>() {
            @Override
            public void onUi(Userbean userbean) {
                if (listener!=null){
                    listener.onGetjs(userbean);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
