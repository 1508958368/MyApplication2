package com.bwei.ssp.home_work.Fragment.Shouye.bean;

/**
 * Created by lenovo on 2017/12/12.
 */

public class Grild_Bean {
    private  String name;
    private   int    image;

    public Grild_Bean(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
