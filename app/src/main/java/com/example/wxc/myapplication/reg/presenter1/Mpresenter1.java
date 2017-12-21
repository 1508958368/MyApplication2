package com.example.wxc.myapplication.reg.presenter1;

import com.example.wxc.myapplication.reg.bean2.User1;
import com.example.wxc.myapplication.reg.bean2.UserBean1;
import com.example.wxc.myapplication.reg.model2.Imodel2;
import com.example.wxc.myapplication.reg.model2.UserModel2;
import com.example.wxc.myapplication.reg.view2.Iview1;

/**
 * author:Created by WangXinChi on 2017/12/12.
 */

public class Mpresenter1 implements UserModel2.OnFinishLisenter{
    private  UserModel2 userModel2;
    private Iview1 iview1;
    private Imodel2 imodel2;

    public Mpresenter1(Iview1 iview1) {
        this.iview1 = iview1;
        this.userModel2 = new UserModel2();
    }
public void login1(User1 user1){
    userModel2.Longin1(user1);
    userModel2.setOnFinishLisenter(this);
}
    @Override
    public void OnFinish(UserBean1 userBean1) {
        String code = userBean1.getCode();
        if ("0".equals(code)) {
            iview1.RegSuccess();

        }else {
            iview1.RegFideled();
        }
    }
}
