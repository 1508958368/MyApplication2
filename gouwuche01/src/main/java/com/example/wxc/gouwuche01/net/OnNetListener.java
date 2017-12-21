package com.example.wxc.gouwuche01.net;

/**
 * author:Created by WangXinChi on 2017/12/20.
 */

public interface OnNetListener<T> {
    public  void  onSuccess(T t);
    public  void  onFail(Exception e);
}
