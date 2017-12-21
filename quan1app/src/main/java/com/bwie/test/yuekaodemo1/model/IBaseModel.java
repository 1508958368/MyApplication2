package com.bwie.test.yuekaodemo1.model;

import com.bwie.test.yuekaodemo1.bean.BaseBean;
import com.bwie.test.yuekaodemo1.net.OnNetListener;

/**
 * author:Created by 董博 on 2017/12/20.
 */

public interface IBaseModel {
    public void doCar(OnNetListener<BaseBean> onNetListener);
}
