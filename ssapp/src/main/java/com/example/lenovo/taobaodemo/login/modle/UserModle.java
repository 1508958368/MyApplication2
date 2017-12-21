package com.example.lenovo.taobaodemo.login.modle;

import com.example.lenovo.taobaodemo.login.Api.Url;
import com.example.lenovo.taobaodemo.login.loginbean.User;
import com.example.lenovo.taobaodemo.login.loginbean.UserInFo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

/**
 * author:Created by WangZhiQiang on 2017/12/7.
 */

public class UserModle implements UserLogin {

    private OnIsFinsh onIsFinsh;

    public interface OnIsFinsh {
        void onfinsh(UserInFo userInFo);
    }


    public void setOnIsFinsh(OnIsFinsh onIsFinsh) {
        this.onIsFinsh = onIsFinsh;
    }

    //请求数据
    @Override
    public void loginState(final User user) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", user.getName());
        map.put("password", user.getPassword());

        OkHttp3Utils.doPost(Url.LOGIN, map, new GsonObjectCallback<UserInFo>() {

            @Override
            public void onUi(UserInFo userInFo) {
                if (onIsFinsh != null) {

                    onIsFinsh.onfinsh(userInFo);

                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });


    }
}
