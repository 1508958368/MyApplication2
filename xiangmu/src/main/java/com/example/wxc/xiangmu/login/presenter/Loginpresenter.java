package com.example.wxc.xiangmu.login.presenter;

import com.example.wxc.xiangmu.login.bean.User;
import com.example.wxc.xiangmu.login.bean.UserBean;
import com.example.wxc.xiangmu.login.model1.Imodellog;
import com.example.wxc.xiangmu.login.model1.UserModel;
import com.example.wxc.xiangmu.login.view1.Iview1;

/**
 * author:Created by WangXinChi on 2017/12/13.
 */

public class Loginpresenter implements UserModel.OnFinishLisenter {
    private  UserModel userModel;
    private Imodellog imodellog;
    private Iview1 iview1;

    public Loginpresenter(Iview1 iview1) {
        this.iview1 = iview1;
        this.userModel = new UserModel();
    }
    public void login(User user){
        userModel.longin(user);
        userModel.setOnFinishLisenter(this);
    }

    @Override
    public void OnFinish(UserBean userBean) {
        String code = userBean.getCode();
        if ("0".equals(code)) {
            iview1.LoginFindeled();
        }else{

            iview1.LoginSuccess();

        }
    }
}
