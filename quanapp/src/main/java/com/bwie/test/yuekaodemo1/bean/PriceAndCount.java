package com.bwie.test.yuekaodemo1.bean;

/**
 * Created by peng on 2017/12/13.
 */

public class PriceAndCount {
    private double price;
    private int count;

    public PriceAndCount(double price, int count) {
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
