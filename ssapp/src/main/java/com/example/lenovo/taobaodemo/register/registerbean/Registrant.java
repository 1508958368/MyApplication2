package com.example.lenovo.taobaodemo.register.registerbean;

/**
 * author:Created by WangZhiQiang on 2017/12/8.
 */

public class Registrant {

    private String name;
    private String pwd;
    private String checkingpwd;
    private String email;

    public Registrant() {
    }

    public Registrant(String name, String checkingpwd) {
        this.name = name;
        this.checkingpwd = checkingpwd;
    }

    public Registrant(String name, String pwd, String checkingpwd, String email) {
        this.name = name;
        this.pwd = pwd;
        this.checkingpwd = checkingpwd;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCheckingpwd() {
        return checkingpwd;
    }

    public void setCheckingpwd(String checkingpwd) {
        this.checkingpwd = checkingpwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
