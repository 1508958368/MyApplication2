package com.example.lenovo.taobaodemo.register.model;

import com.example.lenovo.taobaodemo.register.registerbean.RegisterInfo;
import com.example.lenovo.taobaodemo.register.registerbean.Registrant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

import static com.example.lenovo.taobaodemo.register.api.Url.REGISTER;

/**
 * author:Created by WangZhiQiang on 2017/12/8.
 */

public class Model implements RModel {

    private setSesult setSesult;

    public Model(Model.setSesult setSesult) {
        this.setSesult = setSesult;
    }

    //结果接口
    public interface setSesult {
        void onfinsh(RegisterInfo registerInfo);
    }

    //注册状态
    @Override
    public void RegisterState(Registrant registrant) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", registrant.getName());
        map.put("password", registrant.getCheckingpwd());
        OkHttp3Utils.doPost(REGISTER, map, new GsonObjectCallback<RegisterInfo>() {
            @Override
            public void onUi(RegisterInfo registerInfo) {
                if (setSesult != null) {
                    setSesult.onfinsh(registerInfo);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }
}
