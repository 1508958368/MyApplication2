package com.bawei.chenkai.day14rikao.xiangqing.bean.view;

import com.bawei.chenkai.day14rikao.xiangqing.bean.SecondBean;

/**
 * author:Created by WangZhiQiang on 2017/12/19.
 */

public interface SecondViewListener {
    public interface ModelSecondInterface{
        public void success(SecondBean bean);
        public void failed(Exception e);
    }
    public interface PresenterSecondInterface{
        public void success(SecondBean bean);
        public void failed(Exception e);
    }

    public interface PresenterSecondInterfac {
    }
}