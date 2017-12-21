package com.example.wxc.myapplication.presenter;

import com.example.wxc.myapplication.bean.GoodsBean;
import com.example.wxc.myapplication.model.MianModel;
import com.example.wxc.myapplication.view.Imain;

/**
 * author:Created by WangXinChi on 2017/12/17.
 */

public class MainPresenter implements MianModel {
    Imain view;
    MianModel model;
    public MainPresenter(Imain view){
        this.view = view;
        this.model = new MianModel();
    }

    public void getData(){
        model.getData(new MianModel() {
            @Override
            public void success(GoodsBean bean) {
                if (view != null){
                    view.success(bean);
                }
            }

            @Override
            public void failure(Exception e) {
                if (view != null){
                    view.failure(e);
                }
            }
        });
    }

    public void detach(){
        view = null;
    }
}
