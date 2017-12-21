package com.example.wxc.myapplication.model;

import com.example.wxc.myapplication.bean.GoodsBean;

/**
 * author:Created by WangXinChi on 2017/12/17.
 */

public interface IModel {
    public void success(GoodsBean bean);
    public void failure(Exception e);
}
