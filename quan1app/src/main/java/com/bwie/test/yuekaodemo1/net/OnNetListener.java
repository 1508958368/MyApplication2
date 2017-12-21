package com.bwie.test.yuekaodemo1.net;

/**
 * author:Created by 董博 on 2017/12/19.
 */

public interface OnNetListener<T> {
    void OnSuccess(T t);
    void OnFailed(Exception e);
}
