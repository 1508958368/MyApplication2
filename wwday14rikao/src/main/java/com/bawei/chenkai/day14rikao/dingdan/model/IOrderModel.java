package com.bawei.chenkai.day14rikao.dingdan.model;

import com.bawei.chenkai.day14rikao.dingdan.API.OnNetListener;
import com.bawei.chenkai.day14rikao.dingdan.bean.OrderBean;

/**
 * author:Created by WangZhiQiang on 2017/12/20.
 */

public interface IOrderModel {
    public void getOrder(String status, String page, OnNetListener<OrderBean> onNetListener);
}
