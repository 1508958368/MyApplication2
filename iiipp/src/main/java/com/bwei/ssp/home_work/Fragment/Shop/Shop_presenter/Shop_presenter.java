package com.bwei.ssp.home_work.Fragment.Shop.Shop_presenter;

import android.util.Log;

import com.bwei.ssp.home_work.Fragment.Shop.Shop_model.Shop_mclass;
import com.bwei.ssp.home_work.Fragment.Shop.Sop_view.Shop_view;
import com.bwei.ssp.home_work.Fragment.Shop.bean.Shop_Bean;

/**
 * Created by lenovo on 2017/12/13.
 */

public class Shop_presenter implements Shop_mclass.OnSendPscidListener {
    Shop_view shopView;
    Shop_mclass mclass;

    public Shop_presenter(Shop_view shopView) {
        this.shopView = shopView;
        this.mclass =new Shop_mclass();
    }
  public  void getJs(int pscid){
       mclass.setData(pscid);
      mclass.setListener(this);
  }
    @Override
    public void onSendPscid(Shop_Bean bean) {
        Log.e("***", bean.toString() );

        shopView.getData(bean);
    }
}
