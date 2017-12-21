package com.bawei.chenkai.day14rikao.Gouwuche.presenter;


import com.bawei.chenkai.day14rikao.Gouwuche.model.CartModel;
import com.bawei.chenkai.day14rikao.Gouwuche.presenter.interfac.ICartPresenter;
import com.bawei.chenkai.day14rikao.Gouwuche.view.IView.IMainActivity;

/**
 * Created by Dash on 2017/12/12.
 */
public class CartPresenter implements ICartPresenter {

    private final CartModel cartModel;
    private IMainActivity iMainActivity;

    public CartPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        cartModel = new CartModel(this);
    }

    public void getCartData(String cartUrl) {
        cartModel.getCartData(cartUrl);

    }

    @Override
    public void getSuccessCartJson(String json) {
        //回调给view
        iMainActivity.getSuccessCartData(json);
    }
}
