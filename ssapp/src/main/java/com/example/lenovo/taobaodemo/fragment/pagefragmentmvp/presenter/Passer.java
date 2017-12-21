package com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.presenter;

import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.bean.HeadbannerImg;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.model.Model;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.view.IView;

/**
 * author:Created by WangZhiQiang on 2017/12/12.
 */

public class Passer implements Model.SetOnFinsh {
    private IView iView;
    private Model model;

    public Passer(IView iView) {
        this.iView = iView;
        model = new Model();
    }


    public void onLoad(String url) {
        model.setfinish(this);
        model.onLoad(url);
    }


    //请求数据回传
    @Override
    public void onFinish(HeadbannerImg headbannerImg) {
        iView.getData(headbannerImg);
    }
}
