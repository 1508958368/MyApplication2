package com.example.wxc.myapplication.recycerview.p;

import com.example.wxc.myapplication.bean.Goods;
import com.example.wxc.myapplication.recycerview.m.ListModel;
import com.example.wxc.myapplication.recycerview.v.Iview;

import java.util.Map;

/**
 * author:Created by WangXinChi on 2017/12/12.
 */

public class Lpresenter implements ListModel.OnFinish {
    private ListModel listModel;
    private Iview iview;

    public Lpresenter(Iview iview) {
        this.iview = iview;
        this.listModel=new ListModel();
        listModel.setOnFinish(this);
    }
public void getUrl(String url, Map<String,String>mmap){
    listModel.getUrl(url,mmap);
}
    @Override
    public void onFinishLisenter(Goods goods) {
iview.getData(goods);
    }
}
