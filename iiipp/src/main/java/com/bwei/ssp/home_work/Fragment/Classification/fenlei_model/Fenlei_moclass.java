package com.bwei.ssp.home_work.Fragment.Classification.fenlei_model;

import com.bwei.ssp.home_work.Fragment.Classification.bean.CommodityBean;
import com.bwei.ssp.home_work.Fragment.Classification.bean.Son_Bean;
import com.bwei.ssp.home_work.Okhttputils.GsonObjectCallback;
import com.bwei.ssp.home_work.Okhttputils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

/**
 * Created by lenovo on 2017/12/13.
 */

public class Fenlei_moclass implements Fenlei_Model{
    OnSendConnbeanListener listener;

    public void setListener(OnSendConnbeanListener listener) {
        this.listener = listener;
    }

    public  interface OnSendConnbeanListener{
        void onsendConbean(CommodityBean bean);
    }
    OnSendSonBeanLisenter SonLisenter;

    public void setSonLisenter(OnSendSonBeanLisenter sonLisenter) {
        SonLisenter = sonLisenter;
    }

    public interface OnSendSonBeanLisenter{
        void  onSendSonbean(Son_Bean bean);
    }


    @Override
    public void setCommBean() {
        OkHttp3Utils.doGet("http://120.27.23.105/product/getCatagory", new GsonObjectCallback<CommodityBean>() {
            @Override
            public void onUi(CommodityBean bean) {
                listener.onsendConbean(bean);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

    @Override
    public void setSonBean(int cid) {


            HashMap<String, String> map = new HashMap<>();
            map.put("cid", cid + "");
            OkHttp3Utils.doPost("http://120.27.23.105/product/getProductCatagory", map, new GsonObjectCallback<Son_Bean>() {
                @Override
                public void onUi(Son_Bean bean) {
                    SonLisenter.onSendSonbean(bean);
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });

    }
}
