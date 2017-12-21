package com.bwie.test.demo2.model;

import com.bwie.test.demo2.bean.GetCartBean;
import com.bwie.test.demo2.net.OnNetListener;

import java.util.Map;

/**
 * author:Created by 董博 on 2017/12/17.
 */

public interface GetCartService {
    void getCart(Map<String, String> params, OnNetListener<GetCartBean> onNetListener);
}
