package com.bawei.chenkai.day14rikao.dingdan.presenter;

import com.bawei.chenkai.day14rikao.dingdan.API.OnNetListener;
import com.bawei.chenkai.day14rikao.dingdan.bean.OrderBean;
import com.bawei.chenkai.day14rikao.dingdan.model.OrderModel;
import com.bawei.chenkai.day14rikao.dingdan.view.IMainActivity;

/**
 * author:Created by WangZhiQiang on 2017/12/20.
 */

public class MainPresenter {
    private OrderModel orderModel;
    private IMainActivity iMainActivity;

    public MainPresenter(IMainActivity iMainActivity) {
        orderModel = new OrderModel();
        this.iMainActivity = iMainActivity;
    }

    public void getOrder(final boolean isRefresh, String status, String page) {
        orderModel.getOrder(status, page, new OnNetListener<OrderBean>() {
            @Override
            public void onSuccess(OrderBean orderBean) {
                iMainActivity.showData(isRefresh, orderBean.getData());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void dettach() {
        if (iMainActivity != null) {
            iMainActivity = null;
        }
    }
}