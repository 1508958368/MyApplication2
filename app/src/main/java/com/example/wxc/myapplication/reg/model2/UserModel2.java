package com.example.wxc.myapplication.reg.model2;

import com.example.wxc.myapplication.reg.bean2.User1;
import com.example.wxc.myapplication.reg.bean2.UserBean1;
import com.example.wxc.myapplication.utils.GsonObjectCallback;
import com.example.wxc.myapplication.utils.OkHttp3Utils;

import java.io.IOException;

import okhttp3.Call;

/**
 * author:Created by WangXinChi on 2017/12/12.
 */

public class UserModel2 implements Imodel2{
    public OnFinishLisenter lisenter;
    public interface OnFinishLisenter{
        void OnFinish(UserBean1 userBean1);
    }
    public void setOnFinishLisenter(OnFinishLisenter onFinishLisenter){
        this.lisenter=lisenter;
    }




    @Override
    public void Longin1(User1 user1) {
        OkHttp3Utils.doGet("http://120.27.23.105/user/reg?mobile=" + user1.getName1() + "&password=" + user1.getPass1(), new GsonObjectCallback<UserBean1>() {
            @Override
            public void onUi(UserBean1 userBean1) {
                if (lisenter != null) {
                    lisenter.OnFinish(userBean1);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
