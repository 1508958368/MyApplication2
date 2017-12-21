package com.bwie.test.demo2.model;

import com.bwie.test.demo2.bean.DetailsBean;
import com.bwie.test.demo2.net.OnNetListener;

import java.util.Map;

/**
 * author:Created by 董博 on 2017/12/17.
 */

public interface DetailsService {
    void getProductDetail(Map<String, String> params, OnNetListener<DetailsBean> onNetListener);
}
