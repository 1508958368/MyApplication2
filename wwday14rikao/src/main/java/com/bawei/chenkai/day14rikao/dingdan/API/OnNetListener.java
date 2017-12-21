package com.bawei.chenkai.day14rikao.dingdan.API;

/**
 * author:Created by WangZhiQiang on 2017/12/20.
 */

public interface OnNetListener<T> {
    //成功回调
    public void onSuccess(T t);

    //失败回调
    public void onFailure(Exception e);
}
