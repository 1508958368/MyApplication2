package com.example.wxc.myapplication.app;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * author:Created by WangXinChi on 2017/12/12.
 */

public class MApp extends Application {
    private static MApp minstance;

    @Override
    public void onCreate() {
        super.onCreate();
        minstance = this;
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);

    }
     public  static  MApp getInstance(){
        return minstance;
    }
}
