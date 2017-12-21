package com.example.lenovo.taobaodemo.login.loginbean;

/**
 * author:Created by WangZhiQiang on 2017/12/7.
 */

public class User {
    private String name;
    private String password;


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
