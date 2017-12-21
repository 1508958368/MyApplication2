package com.example.lenovo.taobaodemo.register.view;

/**
 * author:Created by WangZhiQiang on 2017/12/8.
 */

public interface RView {
    //注册成功
    void onSucceed(String msg);

    //注册失败
    void onFailure(String msg);

}
