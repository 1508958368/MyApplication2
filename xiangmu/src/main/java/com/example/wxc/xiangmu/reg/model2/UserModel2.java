package com.example.wxc.xiangmu.reg.model2;


import com.example.wxc.xiangmu.reg.bean2.User1;
import com.example.wxc.xiangmu.reg.bean2.Userbean1;
import com.example.wxc.xiangmu.utils.GsonObjectCallback;
import com.example.wxc.xiangmu.utils.OkHttp3Utils;

import java.io.IOException;

import okhttp3.Call;


/**
 * author: Wangxinrun
 * Date: 2017/12/11
 * Time: 9:31
 */

public class UserModel2 implements Imodel1{


    public OnFinishLisenter lisenter;



    public interface OnFinishLisenter{
        void OnFinish(Userbean1 userbean);
    }
    public void setOnFinishLisenter(OnFinishLisenter lisenter){
        this.lisenter=lisenter;
    }
    @Override
    public void login1(User1 user1) {
        OkHttp3Utils.doGet("http://120.27.23.105/user/reg?mobile="+user1.getName1()+"&password="+user1.getPass1(), new GsonObjectCallback<Userbean1>() {
            @Override
            public void onUi(Userbean1 userbean1) {
                if(lisenter!=null){
                    lisenter.OnFinish(userbean1);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
