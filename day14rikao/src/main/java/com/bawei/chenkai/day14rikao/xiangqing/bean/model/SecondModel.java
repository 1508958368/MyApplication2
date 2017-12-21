package com.bawei.chenkai.day14rikao.xiangqing.bean.model;

import com.bawei.chenkai.day14rikao.xiangqing.bean.SecondBean;
import com.bawei.chenkai.day14rikao.xiangqing.bean.net.AbstractUiCallBack;
import com.bawei.chenkai.day14rikao.xiangqing.bean.net.OkhttpUtils;
import com.bawei.chenkai.day14rikao.xiangqing.bean.view.SecondViewListener;

/**
 * author:Created by WangZhiQiang on 2017/12/19.
 */

public class SecondModel {
    public void getDataModel(final SecondViewListener.ModelSecondInterface modelSecondInterface){
        OkhttpUtils.getInstance().asy(null, "https://www.zhaoapi.cn/product/getProductDetail?pid=1", new AbstractUiCallBack<SecondBean>() {
            @Override
            public void success(SecondBean bean) {
                modelSecondInterface.success(bean);
            }

            @Override
            public void failure(Exception e) {
                modelSecondInterface.failed(e);
            }
        });
    }
}