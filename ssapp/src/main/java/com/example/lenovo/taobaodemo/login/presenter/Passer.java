package com.example.lenovo.taobaodemo.login.presenter;

import com.example.lenovo.taobaodemo.login.loginbean.User;
import com.example.lenovo.taobaodemo.login.loginbean.UserInFo;
import com.example.lenovo.taobaodemo.login.modle.UserModle;
import com.example.lenovo.taobaodemo.login.view.Iview;

/**
 * author:Created by WangZhiQiang on 2017/12/7.
 */

public class Passer implements UserModle.OnIsFinsh {
    private Iview iview;
    private UserModle userModle;

    public Passer(Iview iview) {
        this.iview = iview;
        userModle = new UserModle();
    }

    public void login(User user) {
        userModle.setOnIsFinsh(this);
        userModle.loginState(user);

    }


    //请求状态
    @Override
    public void onfinsh(UserInFo userInFo) {
        if (userInFo.getCode().equals("0")) {
            iview.onSucceed(userInFo.getMsg());
        } else {
            iview.onFailed(userInFo.getMsg());
        }

    }
}
