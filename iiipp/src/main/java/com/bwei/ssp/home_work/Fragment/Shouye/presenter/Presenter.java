package com.bwei.ssp.home_work.Fragment.Shouye.presenter;

import android.util.Log;

import com.bwei.ssp.home_work.Fragment.Shouye.bean.Shouye_Bean;
import com.bwei.ssp.home_work.Fragment.Shouye.bean.Shouye_Xbanner_Bean;
import com.bwei.ssp.home_work.Fragment.Shouye.model.Mymodel;
import com.bwei.ssp.home_work.Fragment.Shouye.view.View;

/**
 * Created by lenovo on 2017/12/8.
 */

public class Presenter implements Mymodel.OnGetdataListener,Mymodel.OnXbannerListener {

    View view;
    Mymodel mymodel;

    public Presenter(View view) {

        this.view = view;
        this.mymodel =new Mymodel();
    }
  public void  getJs(){
      mymodel.setListener(this);
      mymodel.setData();
  }

  public void  getXjs(){
      mymodel.setXbaneerData();
      mymodel.setXlistener(this);
  }
    @Override
    public void onGetdatalistener(Shouye_Bean bean) {
        Log.e("***", bean.toString());
        view.getData(bean);
    }




    @Override
    public void onXbanner(Shouye_Xbanner_Bean bean) {
        Log.e("***", bean.toString());
        view.getBannerData(bean);
    }
}
