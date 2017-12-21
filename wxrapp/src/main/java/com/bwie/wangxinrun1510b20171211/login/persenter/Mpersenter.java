package com.bwie.wangxinrun1510b20171211.login.persenter;

import com.bwie.wangxinrun1510b20171211.login.bean1.User;
import com.bwie.wangxinrun1510b20171211.login.bean1.Userbean;
import com.bwie.wangxinrun1510b20171211.login.model1.Imodel;
import com.bwie.wangxinrun1510b20171211.login.model1.UserModel;
import com.bwie.wangxinrun1510b20171211.login.vie1.Iview;

/**
 * author: Wangxinrun
 * Date: 2017/12/11
 * Time: 9:12
 */

public class Mpersenter implements UserModel.OnFinishLisenter{
    private final UserModel UserModel;
    private Imodel imodel;
    private Iview iview;

    public Mpersenter(Iview iview) {
        this.iview = iview;
        this.UserModel=new UserModel();
    }
    public void login(User user){
        UserModel.login(user);
        UserModel.setOnFinishLisenter(this);
    }
    @Override
    public void OnFinish(Userbean userbean) {
        String code = userbean.getCode();
        if("0".equals(code)){
            iview.LoginSuccess();
        }else{
            iview.LoginFideled();
        }
    }
}
