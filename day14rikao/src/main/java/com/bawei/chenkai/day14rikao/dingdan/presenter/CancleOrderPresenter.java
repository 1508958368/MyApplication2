package com.bawei.chenkai.day14rikao.dingdan.presenter;

import com.bawei.chenkai.day14rikao.dingdan.API.OnNetListener;
import com.bawei.chenkai.day14rikao.dingdan.model.CancleOrderModel;

/**
 * author:Created by WangZhiQiang on 2017/12/20.
 */

public class CancleOrderPresenter {
    private CancleOrderModel cancleOrderModel;

    public CancleOrderPresenter() {
        this.cancleOrderModel = new CancleOrderModel();
    }


    public void cancleOrder(String status, String orderId) {
        cancleOrderModel.cancleOrder(status, orderId, new OnNetListener() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
