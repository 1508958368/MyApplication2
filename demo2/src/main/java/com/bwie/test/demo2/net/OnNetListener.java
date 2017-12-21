package com.bwie.test.demo2.net;

/**
 * author:Created by 董博 on 2017/12/17.
 */

public interface OnNetListener<T> {
    public void onSuccess(T t);

    public void onFailure(Exception e);
}
