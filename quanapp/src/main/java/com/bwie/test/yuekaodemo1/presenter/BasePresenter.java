package com.bwie.test.yuekaodemo1.presenter;

import com.bwie.test.yuekaodemo1.bean.BaseBean;
import com.bwie.test.yuekaodemo1.model.BaseModel;
import com.bwie.test.yuekaodemo1.net.OnNetListener;
import com.bwie.test.yuekaodemo1.view.Activity1;

/**
 * author:Created by 董博 on 2017/12/20.
 */

public class BasePresenter {
    private Activity1 activity1;
    private final BaseModel baseModel;

    public BasePresenter(Activity1 activity1) {
        this.activity1 = activity1;
        baseModel = new BaseModel();
    }
    public void dopost(){
        baseModel.doCar(new OnNetListener<BaseBean>() {
            @Override
            public void OnSuccess(BaseBean baseBean) {
                activity1.showNews(baseBean);
            }

            @Override
            public void OnFailed(Exception e) {

            }
        });
    }
}
