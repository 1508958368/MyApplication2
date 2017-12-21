package com.bwei.ssp.home_work.Login.presenter;

import com.bwei.ssp.home_work.Login.bean.Bean;
import com.bwei.ssp.home_work.Login.bean.Userbean;
import com.bwei.ssp.home_work.Login.model.Model;
import com.bwei.ssp.home_work.Login.view.Myview;

/**
 * Created by lenovo on 2017/12/8.
 */

public class Presenter implements Model.OnGetJsListener {
    Myview view;
    Model model;

    public Presenter(Myview view) {
        this.view = view;
        this.model =new Model();
    }

    public void getData(Bean bean){
        model.setdata(bean);
        model.setListener(this);
    }

    @Override
    public void onGetjs(Userbean userbean) {
        String code = userbean.getCode();
        if("0".equals(code)){
            view.getYes();
        }else {
            view.getNo();
        }
    }
}
