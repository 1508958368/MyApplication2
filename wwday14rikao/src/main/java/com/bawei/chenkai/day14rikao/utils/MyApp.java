package com.bawei.chenkai.day14rikao.utils;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class MyApp extends Application {

    private static MyApp mInstance;
    private static Context context;
    private static Handler handler;
    private static   int i ;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        ImageLoaderConfiguration defaultcof = ImageLoaderConfiguration.createDefault(getApplicationContext());
        ImageLoader.getInstance().init(defaultcof);

        context = getApplicationContext();
         handler = new Handler();
         i = Process.myTid();


    }


    /**
     * 获取主线程id
     * @return
     */
    public static int getMainThreadId() {

        return i;
    }

    public static MyApp getInstance(){
        return mInstance;
    };

    public static Context getAppContext() {
        return context;
    }

    public static Handler getAppHanler() {
        return handler;
    }
}
