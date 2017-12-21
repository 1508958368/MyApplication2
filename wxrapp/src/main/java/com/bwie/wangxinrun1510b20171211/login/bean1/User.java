package com.bwie.wangxinrun1510b20171211.login.bean1;

/**
 * author: Wangxinrun
 * Date: 2017/12/11
 * Time: 8:58
 */

public class User {
    private String name;
    private String pass;

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
