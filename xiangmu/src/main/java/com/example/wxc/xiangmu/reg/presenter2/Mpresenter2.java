package com.example.wxc.xiangmu.reg.presenter2;

import com.example.wxc.xiangmu.reg.bean2.User1;
import com.example.wxc.xiangmu.reg.bean2.Userbean1;
import com.example.wxc.xiangmu.reg.model2.Imodel1;
import com.example.wxc.xiangmu.reg.model2.UserModel2;
import com.example.wxc.xiangmu.reg.view2.Iview1;

/**
 * author:Created by WangXinChi on 2017/12/13.
 */

public class Mpresenter2 implements UserModel2.OnFinishLisenter {

    private UserModel2 userModel2;
    private Iview1 iview1;
    private Imodel1 imodel1;

    public Mpresenter2(Iview1 iview1) {
        this.iview1 = iview1;
        this.userModel2 = new UserModel2();
    }


    public  void Reg(User1 user1){
        userModel2.login1(user1);
        userModel2.setOnFinishLisenter(this);
    }

    @Override
    public void OnFinish(Userbean1 userbean) {
        String code = userbean.getCode();
        if ("0".equals(code)) {
            iview1.RegSuccess();
        }else {
            iview1.ReginFideled();
        }
    }
}
