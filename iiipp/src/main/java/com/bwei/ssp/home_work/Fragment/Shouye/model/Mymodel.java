package com.bwei.ssp.home_work.Fragment.Shouye.model;

import android.util.Log;

import com.bwei.ssp.home_work.Fragment.Shouye.bean.Shouye_Bean;
import com.bwei.ssp.home_work.Fragment.Shouye.bean.Shouye_Xbanner_Bean;
import com.bwei.ssp.home_work.Okhttputils.GsonObjectCallback;
import com.bwei.ssp.home_work.Okhttputils.OkHttp3Utils;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by lenovo on 2017/12/8.
 */

public class Mymodel implements Model {

    OnGetdataListener listener;

    public void setListener(OnGetdataListener listener) {
        this.listener = listener;
    }

    public interface  OnGetdataListener{
       void  onGetdatalistener(Shouye_Bean bean);
   }
    OnXbannerListener xlistener;

    public void setXlistener(OnXbannerListener xlistener) {
        this.xlistener = xlistener;
    }

    public  interface OnXbannerListener{
       void onXbanner(Shouye_Xbanner_Bean bean);
   }


    @Override
    public void setData() {
        OkHttp3Utils.doGet("http://v.juhe.cn/toutiao/index?type=top&key=dbedecbcd1899c9785b95cc2d17131c5", new GsonObjectCallback<Shouye_Bean>() {
            @Override
            public void onUi(Shouye_Bean shouye_bean) {
                Log.e("***", shouye_bean.toString() );

                if (listener!=null){
                    listener.onGetdatalistener(shouye_bean);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }

    @Override
    public void setXbaneerData() {
        OkHttp3Utils.doGet("http://120.27.23.105/ad/getAd", new GsonObjectCallback<Shouye_Xbanner_Bean>() {
            @Override
            public void onUi(Shouye_Xbanner_Bean shouye_xbanner_bean) {
                Log.e("***", shouye_xbanner_bean.getMsg() );

                xlistener.onXbanner(shouye_xbanner_bean);

            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
