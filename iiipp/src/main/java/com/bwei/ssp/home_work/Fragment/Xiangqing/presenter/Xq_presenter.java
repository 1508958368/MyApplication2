package com.bwei.ssp.home_work.Fragment.Xiangqing.presenter;

import android.util.Log;

import com.bwei.ssp.home_work.Fragment.Xiangqing.bean.Xiang_Bean;
import com.bwei.ssp.home_work.Fragment.Xiangqing.model.Xq_mod_C;
import com.bwei.ssp.home_work.Fragment.Xiangqing.view.Xq_view;

/**
 * Created by lenovo on 2017/12/14.
 */

public class Xq_presenter implements Xq_mod_C.OnGetXiangListener {
    Xq_view xqView;
    Xq_mod_C xqModC;

    public Xq_presenter(Xq_view xqView) {
        this.xqView = xqView;
        this.xqModC=new Xq_mod_C();
    }
  public  void getJS(int pid){
      xqModC.setData(pid);
      xqModC.setListener(this);
  }
    @Override
    public void onGetXq(Xiang_Bean bean) {
        Log.e("pid", bean.getCode() );
        if (bean.getData()!=null){
            xqView.getData(bean);
        }

    }
}
