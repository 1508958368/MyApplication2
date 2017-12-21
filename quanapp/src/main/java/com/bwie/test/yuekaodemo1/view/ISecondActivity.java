package com.bwie.test.yuekaodemo1.view;

import com.bwie.test.yuekaodemo1.bean.ShowCarBean;

import java.util.List;

/**
 * author:Created by 董博 on 2017/12/20.
 */

public interface ISecondActivity {
    public void show(String str);
    public void showCar(List<ShowCarBean.DataBean> group,List<List<ShowCarBean.DataBean.ListBean>> child);
}
