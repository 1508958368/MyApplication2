package com.bwie.test.demo2.view;

import com.bwie.test.demo2.bean.GetCartBean;

import java.util.List;

/**
 * author:Created by 董博 on 2017/12/17.
 */

public interface ISecondListener {
    void show(List<GetCartBean.DataBean> group, List<List<GetCartBean.DataBean.ListBean>> child);
}
