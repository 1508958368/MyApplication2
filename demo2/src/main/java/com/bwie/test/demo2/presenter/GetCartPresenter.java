package com.bwie.test.demo2.presenter;

import com.bwie.test.demo2.bean.GetCartBean;
import com.bwie.test.demo2.model.GetCartModel;
import com.bwie.test.demo2.model.GetCartService;
import com.bwie.test.demo2.net.OnNetListener;
import com.bwie.test.demo2.view.ISecondListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:Created by 董博 on 2017/12/17.
 */

public class GetCartPresenter {
    private ISecondListener iSecondListener;
    private final GetCartService getCartService;

    public GetCartPresenter(ISecondListener iSecondListener) {
        this.iSecondListener = iSecondListener;
        getCartService = new GetCartModel();
    }

    public void dettach() {
        iSecondListener = null;
    }

    public void getCart() {
        Map<String, String> params = new HashMap<>();
        params.put("uid", "1234");
        params.put("pid", "71");
        getCartService.getCart(params, new OnNetListener<GetCartBean>() {
            @Override
            public void onSuccess(GetCartBean getCartBean) {
                if (iSecondListener != null) {
                    List<GetCartBean.DataBean> group = getCartBean.getData();
                    List<List<GetCartBean.DataBean.ListBean>> child = new ArrayList<>();
                    for (int i = 0; i < group.size(); i++) {
                        child.add(group.get(i).getList());
                    }
                    iSecondListener.show(group, child);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
