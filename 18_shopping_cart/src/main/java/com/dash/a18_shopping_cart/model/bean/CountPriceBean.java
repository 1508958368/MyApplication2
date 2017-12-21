package com.dash.a18_shopping_cart.model.bean;

/**
 * Created by Dash on 2017/12/12.
 */
public class CountPriceBean {
    private double price;
    private int count;

    public CountPriceBean(double price, int count) {
        this.price = price;
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
