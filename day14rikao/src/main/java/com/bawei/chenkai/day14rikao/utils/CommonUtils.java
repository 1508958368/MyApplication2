package com.bawei.chenkai.day14rikao.utils;

/**
 * Created by Dash
 */

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.View;

import static com.bawei.chenkai.day14rikao.utils.MyApp.getAppContext;


/**
 * Created by Dash
 */
public class CommonUtils {
    public static final String TAG = "Dash";//sp文件的xml名称
    private static SharedPreferences sharedPreferences;

    /**
     * DashApplication.getAppContext()可以使用,但是会使用系统默认的主题样式，如果你自定义了某些样式可能不会被使用
     * @param layoutId
     * @return
     */
    public static View inflate(int layoutId) {
        View view = View.inflate(getAppContext(), layoutId, null);
        return view;
    }

    /**
     * dip---px
     *
     * @param dip 设备独立像素device independent px....1dp = 3px 1dp = 2px 1dp = 1.5px
     * @return
     */
    public static int dip2px(int dip) {
        //获取像素密度
        float density = getAppContext().getResources().getDisplayMetrics().density;
        //
        int px = (int) (dip * density + 0.5f);//100.6
        return px;

    }

    /**
     * px-dip
     *
     * @param px
     * @return
     */
    public static int px2dip(int px) {
        //获取像素密度
        float density = getAppContext().getResources().getDisplayMetrics().density;
        //
        int dip = (int) (px / density + 0.5f);
        return dip;

    }

    /**
     * 获取资源中的字符串
     * @param stringId
     * @return
     */
    public static String getString(int stringId) {
        return getAppContext().getResources().getString(stringId);
    }

    public static Drawable getDrawable(int did) {
        return getAppContext().getResources().getDrawable(did);
    }

    public static int getDimens(int id) {
        return getAppContext().getResources().getDimensionPixelSize(id);
    }

    /**
     * sp存入字符串类型的值
     * @param flag
     * @param str
     */
    public static void saveSp(String flag, String str) {
        if (sharedPreferences == null) {
            sharedPreferences = getAppContext().getSharedPreferences(TAG, getAppContext().MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(flag, str);
        edit.commit();
    }

    public static String getSp(String flag) {
        if (sharedPreferences == null) {
            sharedPreferences = getAppContext().getSharedPreferences(TAG, getAppContext().MODE_PRIVATE);
        }
        return sharedPreferences.getString(flag, "");
    }

    public static boolean getBoolean(String tag) {
        if (sharedPreferences == null) {
            sharedPreferences = getAppContext().getSharedPreferences(TAG, getAppContext().MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(tag, false);
    }

    public static void putBoolean(String tag, boolean content) {
        if (sharedPreferences == null) {
            sharedPreferences = getAppContext().getSharedPreferences(TAG, getAppContext().MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(tag, content);
        edit.commit();
    }

    /**
     * 清除sp数据
     * @param tag
     */
    public static void clearSp(String tag) {
        if (sharedPreferences == null) {
            sharedPreferences = getAppContext().getSharedPreferences(TAG, getAppContext().MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(tag);
        edit.commit();
    }

    /**
     * 自己写的运行在主线程的方法
     * 如果是主线程,执行任务,否则使用handler发送到主线程中去执行
     *
     *
     * @param runable
     */
    public static void runOnUIThread(Runnable runable) {
        //先判断当前属于子线程还是主线程
        if (android.os.Process.myTid() == MyApp.getMainThreadId()) {
            runable.run();
        } else {
            //子线程
            MyApp.getAppHanler().post(runable);
        }
    }
}
