package com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.presenter;

import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.GoodsList;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortRithtChildBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.model.Model;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.view.GoodsListIView;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.view.IView;

/**
 * author:Created by WangZhiQiang on 2017/12/13.
 */

public class Passer implements Model.onFinishListenner {

    private IView iView;
    private Model model;
    private GoodsListIView goodsListIView;

    public Passer(IView iView) {
        this.iView = iView;
        model = new Model();
    }

    public Passer() {
        model = new Model();
    }

    public void setGoodsListIView(GoodsListIView goodsListIView) {
        this.goodsListIView = goodsListIView;
    }

    public void onLoad(String url, int i) {
        model.setOnFinishListenner(this);
        model.onLoad(url, i);
    }


    //结果回传
    @Override
    public void onFinsh(SortBean sortBean, int i) {

        if (iView != null) {
            if (sortBean != null) {
                iView.getSortBean(sortBean);
            }
        }

    }

    //子类结果回传
    @Override
    public void onChildFinsh(SortRithtChildBean sortRithtChildBean, int i) {

        if (iView != null) {
            if (sortRithtChildBean != null) {
                iView.getSortChildBean(sortRithtChildBean);
            }
        }

    }

    //获取商品列表数据
    @Override
    public void onGoodsListFinsh(GoodsList goodsList, int i) {
        if (goodsListIView != null) {
            if (goodsList != null) {
                goodsListIView.getGoodsList(goodsList);
            }
        }
    }
}
