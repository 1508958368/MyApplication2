package com.bwie.test.yuekaodemo1.presenter;

import com.bwie.test.yuekaodemo1.bean.ShowCarBean;
import com.bwie.test.yuekaodemo1.model.ShowModel;
import com.bwie.test.yuekaodemo1.net.Api;
import com.bwie.test.yuekaodemo1.net.OnNetListener;
import com.bwie.test.yuekaodemo1.view.ISecondActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author:Created by 董博 on 2017/12/20.
 */

public class ShowCarPresenter {
    private ISecondActivity iSecondActivity;
    private final ShowModel showModel;

    public ShowCarPresenter(ISecondActivity iSecondActivity) {
        this.iSecondActivity = iSecondActivity;
        showModel = new ShowModel();
    }
    public void show(){
        HashMap<String, String> map = new HashMap<>();
        map.put("uid","71");
        showModel.showCar(Api.ShowCar, map, new OnNetListener<ShowCarBean>() {
            @Override
            public void OnSuccess(ShowCarBean showCarBean) {
                List<ShowCarBean.DataBean> group = showCarBean.getData();
                List<List<ShowCarBean.DataBean.ListBean>> child = new ArrayList<>();
                for (int i = 0; i <group.size() ; i++) {
                    child.add(group.get(i).getList());
                }
                iSecondActivity.showCar(group,child);
            }

            @Override
            public void OnFailed(Exception e) {

            }
        });
    }
    //解决内存泄漏
    public void Dettouch() {
        if (iSecondActivity != null) {
            iSecondActivity = null;
        }
    }
}
