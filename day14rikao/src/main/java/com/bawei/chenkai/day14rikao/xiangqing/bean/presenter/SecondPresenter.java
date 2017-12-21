package com.bawei.chenkai.day14rikao.xiangqing.bean.presenter;

import com.bawei.chenkai.day14rikao.xiangqing.bean.SecondBean;
import com.bawei.chenkai.day14rikao.xiangqing.bean.model.SecondModel;
import com.bawei.chenkai.day14rikao.xiangqing.bean.view.SecondViewListener;

/**
 * author:Created by WangZhiQiang on 2017/12/19.
 */

public class SecondPresenter {
    SecondViewListener.PresenterSecondInterface  presenterSecondInterface;
    SecondModel secondModel;

    public SecondPresenter(SecondViewListener.PresenterSecondInterface  presenterSecondInterface) {
        this.presenterSecondInterface = presenterSecondInterface;
        this.secondModel = new SecondModel();
    }

    public void getData(){
        secondModel.getDataModel(new SecondViewListener.ModelSecondInterface() {
            @Override
            public void success(SecondBean bean) {
                presenterSecondInterface.success(bean);
            }

            @Override
            public void failed(Exception e) {
                presenterSecondInterface.failed(e);
            }
        });
    }

    //防止内存泄露
    public void detach(){
        presenterSecondInterface = null;
    }
}

