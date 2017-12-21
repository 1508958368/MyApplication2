package com.example.wxc.xiangmu.fragment.fragment1.presenter1;

import com.example.wxc.xiangmu.fragment.fragment1.bean.Imgs;
import com.example.wxc.xiangmu.fragment.fragment1.model1.Model;
import com.example.wxc.xiangmu.fragment.fragment1.view.Iview;

/**
 * author:Created by WangXinChi on 2017/12/15.
 */

public class Mpresenter3 implements Model.SetOnFinsh {
    private Iview iview;
    private Model model;

   public Mpresenter3(Iview iview){
       this.iview = iview;
       model = new Model();
   }
   public void onLoad(String url){
       model.onLoad(url);
       model.setSetonfinsh(this);

   }

    @Override
    public void onFinsh(Imgs imgs) {
      iview.getData(imgs.getData());
    }
}
