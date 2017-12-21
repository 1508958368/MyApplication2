package com.bwie.wangxinrun1510b20171211.login.model1;

import com.bwie.wangxinrun1510b20171211.login.bean1.User;
import com.bwie.wangxinrun1510b20171211.login.bean1.Userbean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

/**
 * author: Wangxinrun
 * Date: 2017/12/11
 * Time: 9:02
 */

public class UserModel implements Imodel{

    public OnFinishLisenter lisenter;

    public interface OnFinishLisenter{
        void OnFinish(Userbean userbean);
    }
    public void setOnFinishLisenter(OnFinishLisenter lisenter){
        this.lisenter=lisenter;
    }



    @Override
    public void login(User user) {
        Map<String,String> map=new HashMap<>();
        map.put("mobile",user.getName());
        map.put("password",user.getPass());
        OkHttp3Utils.doPost("http://120.27.23.105/user/login", map, new GsonObjectCallback<Userbean>() {
            @Override
            public void onUi(Userbean userbean) {
                if(lisenter!=null){
                    lisenter.OnFinish(userbean);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
