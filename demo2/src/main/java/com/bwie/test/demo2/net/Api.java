package com.bwie.test.demo2.net;

/**
 * Created by peng on 2017/12/1.
 */

public interface Api {
    boolean ISONLINE = true;
    String DEV = "http://169.27.23.105";
    public static String ONLINE = "http://120.27.23.105";

    public static String HOST = ISONLINE ? ONLINE : DEV;
    public static String Login = HOST + "/user/login";
    public static String REGISTER = HOST + "/user/reg";
    public static String CATAGORY = HOST + "/product/getCatagory";
    public static String PRODUCT_CATAGORY = HOST + "/product/getProductCatagory?cid=%s";
    public static String PRODUCT_CATAGORY_LIST = HOST + "/product/getProducts";
    public static String PRODUCT_DETAIL = HOST + "/product/getProductDetail?pid=%s&source=android";
    public static String ADD_CARD = HOST + "/product/addCart";
    public static String SELECT_CARD = HOST + "/product/getCarts";
    public static String DEL_CARD = HOST + "/product/deleteCart";


    public static String str1 = "https://www.zhaoapi.cn/product/getProductDetail";
    public static String str2 = "https://www.zhaoapi.cn/product/getCarts";
}
