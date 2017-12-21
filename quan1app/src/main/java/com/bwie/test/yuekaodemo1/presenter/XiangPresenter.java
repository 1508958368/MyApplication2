package com.bwie.test.yuekaodemo1.presenter;

import com.bwie.test.yuekaodemo1.bean.GoodXiangBean;
import com.bwie.test.yuekaodemo1.model.Model;
import com.bwie.test.yuekaodemo1.net.Api;
import com.bwie.test.yuekaodemo1.net.OnNetListener;
import com.bwie.test.yuekaodemo1.view.IMainActivity;

import java.util.HashMap;

/**
 * author:Created by 董博 on 2017/12/19.
 */

public class XiangPresenter {
    private IMainActivity iMainActivity;
    private final Model model;

    public XiangPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        model = new Model();
    }
    public  void getGood(String pid){
        HashMap<String, String> map = new HashMap<>();
        map.put("pid",pid);
        model.doPost(Api.GOOD_XIANG, map, new OnNetListener<GoodXiangBean>() {
            @Override
            public void OnSuccess(GoodXiangBean goodXiangBean) {
                iMainActivity.showNews(goodXiangBean);
            }

            @Override
            public void OnFailed(Exception e) {

            }
        });
    }
}
