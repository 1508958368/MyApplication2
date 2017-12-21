package com.bwie.yanshaohua1509a20171123.bean;

/**
 * Created by 闫少华on 2017/11/23.
 * 加购购物车的Bean类
 */

public class AddCartBean {

    /**
     * msg : 加购成功
     * code : 0
     */

    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AddCartBean{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
