package com.bwie.test.yuekaodemo1.presenter;

import com.bwie.test.yuekaodemo1.bean.OrderBean;
import com.bwie.test.yuekaodemo1.model.OrderModel;
import com.bwie.test.yuekaodemo1.net.OnNetListener;
import com.bwie.test.yuekaodemo1.view.IThirdActivity;

/**
 * author:Created by 董博 on 2017/12/21.
 */

public class OrderPresenter {
    private IThirdActivity iThirdActivity;
    private final OrderModel orderModel;

    public OrderPresenter(IThirdActivity iThirdActivity) {
        this.iThirdActivity = iThirdActivity;
        orderModel = new OrderModel();
    }
    public void getOrder(final boolean isRefresh, String status, String page) {
        orderModel.getOrder(status, page, new OnNetListener<OrderBean>() {

            @Override
            public void OnSuccess(OrderBean orderBean) {
                iThirdActivity.showData(isRefresh, orderBean.getData());
            }

            @Override
            public void OnFailed(Exception e) {

            }
        });
    }
    public void dettach() {
        if (iThirdActivity != null) {
            iThirdActivity = null;
        }
    }
}
