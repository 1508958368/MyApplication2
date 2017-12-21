package com.bwie.test.demo2.presenter;

import com.bwie.test.demo2.bean.DetailsBean;
import com.bwie.test.demo2.model.DetailsModel;
import com.bwie.test.demo2.model.DetailsService;
import com.bwie.test.demo2.net.OnNetListener;
import com.bwie.test.demo2.view.IMainListener;

import java.util.HashMap;
import java.util.Map;

/**
 * author:Created by 董博 on 2017/12/17.
 */

public class DetailsPresenter {
    private IMainListener iMainListener;
    private DetailsService detailsService;

    public DetailsPresenter(IMainListener iMainListener) {
        this.iMainListener = iMainListener;
        detailsService = new DetailsModel();
    }

    public void dettach() {
        iMainListener = null;
    }

    public void getProductDetail() {
        Map<String, String> params = new HashMap<>();
        params.put("pid", "71");
        detailsService.getProductDetail(params, new OnNetListener<DetailsBean>() {
            @Override
            public void onSuccess(DetailsBean detailsBean) {
                if (iMainListener != null) {
                    iMainListener.show(detailsBean);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
