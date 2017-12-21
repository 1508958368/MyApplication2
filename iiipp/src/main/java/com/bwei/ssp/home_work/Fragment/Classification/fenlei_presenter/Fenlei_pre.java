package com.bwei.ssp.home_work.Fragment.Classification.fenlei_presenter;

import android.util.Log;

import com.bwei.ssp.home_work.Fragment.Classification.bean.CommodityBean;
import com.bwei.ssp.home_work.Fragment.Classification.bean.Son_Bean;
import com.bwei.ssp.home_work.Fragment.Classification.fellei_View.Fenlei_View;
import com.bwei.ssp.home_work.Fragment.Classification.fenlei_model.Fenlei_moclass;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by lenovo on 2017/12/13.
 */

public class Fenlei_pre implements Fenlei_moclass.OnSendConnbeanListener ,Fenlei_moclass.OnSendSonBeanLisenter{
    Fenlei_View fenleiView;
    Fenlei_moclass fenleiMoclass;

    public Fenlei_pre(Fenlei_View fenleiView) {
        this.fenleiView = fenleiView;
        this.fenleiMoclass=new Fenlei_moclass();
    }

    public void getJs(){
        fenleiMoclass.setCommBean();
        fenleiMoclass.setListener(this);
    }

    public void getSonJs(int cid){
        fenleiMoclass.setSonBean(cid);
        fenleiMoclass.setSonLisenter(this);
    }

    @Override
    public void onsendConbean(CommodityBean bean) {
        Log.e(TAG,bean.getMsg() );
        fenleiView.getConnBean(bean);
    }

    @Override
    public void onSendSonbean(Son_Bean bean) {
        Log.e("***",bean.getMsg() );
        fenleiView.getSonBean(bean);
    }
}
