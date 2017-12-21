package com.bwei.ssp.home_work.App;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class MyApp extends Application {

    private static MyApp mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        ImageLoaderConfiguration defaultcof = ImageLoaderConfiguration.createDefault(getApplicationContext());
        ImageLoader.getInstance().init(defaultcof);

        ZXingLibrary.initDisplayOpinion(this);

    }
    public static MyApp getInstance(){
        return mInstance;
    };

}
