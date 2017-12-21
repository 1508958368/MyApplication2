package com.dash.a18_shopping_cart.presenter;

import com.dash.a18_shopping_cart.model.CartModel;
import com.dash.a18_shopping_cart.presenter.interfac.ICartPresenter;
import com.dash.a18_shopping_cart.view.IView.IMainActivity;
import com.dash.a18_shopping_cart.view.MainActivity;

/**
 * Created by Dash on 2017/12/12.
 */
public class CartPresenter implements ICartPresenter{

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
