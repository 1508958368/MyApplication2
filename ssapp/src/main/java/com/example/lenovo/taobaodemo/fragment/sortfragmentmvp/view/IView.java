package com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.view;

import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortRithtChildBean;

/**
 * author:Created by WangZhiQiang on 2017/12/13.
 */

public interface IView {

    void getSortBean(SortBean sortBean);

    void getSortChildBean(SortRithtChildBean sortRithtChildBean);

}
