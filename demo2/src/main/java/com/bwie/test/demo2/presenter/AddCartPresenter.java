package com.bwie.test.demo2.presenter;

import com.bwie.test.demo2.bean.AddCartBean;
import com.bwie.test.demo2.model.AddCartModel;
import com.bwie.test.demo2.model.AddCartService;
import com.bwie.test.demo2.net.OnNetListener;
import com.bwie.test.demo2.view.IMainListener;

import java.util.HashMap;
import java.util.Map;

/**
 * author:Created by 董博 on 2017/12/17.
 */

public class AddCartPresenter {
    private IMainListener iMainListener;
    private AddCartService addCartService;

    public AddCartPresenter(IMainListener iMainListener) {
        this.iMainListener = iMainListener;
        addCartService = new AddCartModel();
    }

    public void dettach() {
        iMainListener = null;
    }

    public void addCart() {
        Map<String, String> params = new HashMap<>();
        params.put("pid", "71");
        params.put("uid", "39");
        addCartService.addCart(params, new OnNetListener<AddCartBean>() {
            @Override
            public void onSuccess(AddCartBean addCartBean) {
                if (iMainListener != null) {
                    iMainListener.show(addCartBean.getCode().equals("0") ? "添加成功了" : "添加失败了");
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
