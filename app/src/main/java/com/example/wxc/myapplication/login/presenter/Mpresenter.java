package com.example.wxc.myapplication.login.presenter;

import com.example.wxc.myapplication.login.bean1.User;
import com.example.wxc.myapplication.login.bean1.UserBean;
import com.example.wxc.myapplication.login.model1.Imodel;
import com.example.wxc.myapplication.login.model1.UserModel;
import com.example.wxc.myapplication.login.view1.Iview;

/**
 * author:Created by WangXinChi on 2017/12/12.
 */

public class Mpresenter implements UserModel.OnFinishLisenter {
    private UserModel userModel;
    private Imodel imodel;
    private Iview iview;

    public Mpresenter(Iview iview) {
        this.iview = iview;
        this.userModel = new UserModel();
    }
    public void  login(User user){
        userModel.login(user);
        userModel.setOnFinishLiaenter(this);
    }

    @Override
    public void OnFinish(UserBean userBean) {
        String code = userBean.getCode();
        if ("0".equals(code)) {
            iview.LoginSuccess();
        }else {
            iview.LoginFideled();
        }
    }
}
