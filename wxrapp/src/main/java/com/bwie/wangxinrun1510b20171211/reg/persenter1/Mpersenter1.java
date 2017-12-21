package com.bwie.wangxinrun1510b20171211.reg.persenter1;

import com.bwie.wangxinrun1510b20171211.reg.bean2.User1;
import com.bwie.wangxinrun1510b20171211.reg.bean2.Userbean1;
import com.bwie.wangxinrun1510b20171211.reg.model2.Imodel2;
import com.bwie.wangxinrun1510b20171211.reg.model2.UserModel2;
import com.bwie.wangxinrun1510b20171211.reg.view.Iview1;

/**
 * author: Wangxinrun
 * Date: 2017/12/11
 * Time: 9:34
 */

public class Mpersenter1 implements UserModel2.OnFinishLisenter{

    private final UserModel2 UserModel2;
    private Iview1 iview1;
    private Imodel2 imodel2;

    public Mpersenter1(Iview1 iview1) {
        this.iview1 = iview1;
        this.UserModel2 = new UserModel2();
    }
    public void login1(User1 user1){
        UserModel2.login1(user1);
        UserModel2.setOnFinishLisenter(this);
    }
    @Override
    public void OnFinish(Userbean1 userbean) {
        String code = userbean.getCode();
        if("0".equals(code)){
            iview1.RegSuccess();
        }else{
            iview1.ReginFideled();
        }

    }
}
