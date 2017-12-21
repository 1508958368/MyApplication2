package com.bwie.wangxinrun1510b20171211.list.v.p;

import com.bwie.wangxinrun1510b20171211.list.v.Lview;
import com.bwie.wangxinrun1510b20171211.list.v.m.ListModel;

import java.util.Map;

import bean.Goods;

/**
 * author: Wangxinrun
 * Date: 2017/12/12
 * Time: 9:39
 */

public class Lpresenter implements ListModel.OnFinish {
    private final ListModel ListModel;
    private Lview lview;

    public Lpresenter(Lview lview) {
        this.lview = lview;
        this.ListModel = new ListModel();
        ListModel.setOnFinish(this);
    }

    public void getUrl(String url, Map<String,String> mmap){
        ListModel.getUrl(url,mmap);
    }
    @Override
    public void onFinishListener(Goods goods) {
        lview.getData(goods);

    }
}
