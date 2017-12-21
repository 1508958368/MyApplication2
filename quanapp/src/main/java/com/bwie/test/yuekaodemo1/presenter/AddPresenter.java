package com.bwie.test.yuekaodemo1.presenter;

import com.bwie.test.yuekaodemo1.bean.AddCarBean;
import com.bwie.test.yuekaodemo1.model.AddModel;
import com.bwie.test.yuekaodemo1.net.Api;
import com.bwie.test.yuekaodemo1.net.OnNetListener;
import com.bwie.test.yuekaodemo1.view.IMainActivity;

import java.util.HashMap;

/**
 * author:Created by 董博 on 2017/12/20.
 */

public class AddPresenter {
    private IMainActivity iMainActivity;
    private final AddModel addModel;

    public AddPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        addModel = new AddModel();
    }
    public void addShop(int pid){
        HashMap<String, String> map = new HashMap<>();
        map.put("pid",pid+"");
        map.put("uid","71");
        addModel.doCar(Api.ADDCART, map, new OnNetListener<AddCarBean>() {
            @Override
            public void OnSuccess(AddCarBean addCarBean) {
                iMainActivity.show(addCarBean.getMsg());
            }

            @Override
            public void OnFailed(Exception e) {

            }
        });
    }
}
