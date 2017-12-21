package com.example.wxc.xiangmu.login.model1;

import com.example.wxc.xiangmu.login.bean.User;
import com.example.wxc.xiangmu.login.bean.UserBean;
import com.example.wxc.xiangmu.utils.GsonObjectCallback;
import com.example.wxc.xiangmu.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * author:Created by WangXinChi on 2017/12/13.
 */

public class UserModel implements Imodellog{
    public OnFinishLisenter lisenter;
    public interface OnFinishLisenter{
        void OnFinish(UserBean userBean);
    }
    public void setOnFinishLisenter(OnFinishLisenter lisenter){
        this.lisenter=lisenter;
    }


    @Override
    public void longin(User user) {
        Map<String,String>map = new HashMap<>();
        map.put("mobile",user.getName());
        map.put("password",user.getPass());
        OkHttp3Utils.doPost("http://120.27.23.105/user/login", map, new GsonObjectCallback<UserBean>() {
            @Override
            public void onUi(UserBean userBean) {
                if (lisenter != null) {
                    lisenter.OnFinish(userBean);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
