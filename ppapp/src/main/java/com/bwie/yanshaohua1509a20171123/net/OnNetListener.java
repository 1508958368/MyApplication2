package com.bwie.yanshaohua1509a20171123.net;

/**
 * Created by 闫少华 on 2017/11/23.
 * //数据请求是否成功的接口
 */

public interface OnNetListener<T> {
    public void onSuccess(T t);
    public void onFail(Exception e);
}
