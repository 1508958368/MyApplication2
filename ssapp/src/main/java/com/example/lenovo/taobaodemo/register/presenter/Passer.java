package com.example.lenovo.taobaodemo.register.presenter;


import com.example.lenovo.taobaodemo.register.model.Model;
import com.example.lenovo.taobaodemo.register.registerbean.RegisterInfo;
import com.example.lenovo.taobaodemo.register.registerbean.Registrant;
import com.example.lenovo.taobaodemo.register.view.RView;


/**
 * author:Created by WangZhiQiang on 2017/12/8.
 */

public class Passer implements Model.setSesult {

    private RView rview;
    private Model model;


    public Passer(RView rview) {
        this.rview = rview;
        model = new Model(this);
    }


    public void register(Registrant registrant) {
        if (model != null) {
            model.RegisterState(registrant);
        }

    }

    //注册结果
    @Override
    public void onfinsh(RegisterInfo registerInfo) {
        if (registerInfo.getCode().equals("0")) {
            if (rview != null) {
                rview.onSucceed(registerInfo.getMsg());
            }
        } else {
            if (rview != null) {
                rview.onFailure(registerInfo.getMsg());
            }
        }
    }
}
