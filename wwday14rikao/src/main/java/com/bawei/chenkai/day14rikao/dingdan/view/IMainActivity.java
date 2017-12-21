package com.bawei.chenkai.day14rikao.dingdan.view;

import com.bawei.chenkai.day14rikao.dingdan.bean.OrderBean;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/12/20.
 */

public interface IMainActivity {
    public void showData(boolean isRefresh, List<OrderBean.DataBean> list);
}
