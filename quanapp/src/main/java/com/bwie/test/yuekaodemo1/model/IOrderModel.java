package com.bwie.test.yuekaodemo1.model;

import com.bwie.test.yuekaodemo1.bean.OrderBean;
import com.bwie.test.yuekaodemo1.net.OnNetListener;

/**
 * author:Created by 董博 on 2017/12/21.
 */

public interface IOrderModel {
    public void getOrder(String status, String page, OnNetListener<OrderBean> onNetListener);
}
