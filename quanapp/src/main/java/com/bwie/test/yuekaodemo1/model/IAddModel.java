package com.bwie.test.yuekaodemo1.model;

import com.bwie.test.yuekaodemo1.bean.AddCarBean;
import com.bwie.test.yuekaodemo1.net.OnNetListener;

import java.util.Map;

/**
 * author:Created by 董博 on 2017/12/20.
 */

public interface IAddModel {
    public void doCar(String url, Map<String,String> map, OnNetListener<AddCarBean> onNetListener);
}
