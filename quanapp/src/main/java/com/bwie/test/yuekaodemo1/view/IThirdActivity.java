package com.bwie.test.yuekaodemo1.view;

import com.bwie.test.yuekaodemo1.bean.OrderBean;

import java.util.List;

/**
 * author:Created by 董博 on 2017/12/21.
 */

public interface IThirdActivity {
    public void showData(boolean isRefresh, List<OrderBean.DataBean> list);
}
