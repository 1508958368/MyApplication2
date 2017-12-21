package app;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * author: Wangxinrun
 * Date: 2017/12/11
 * Time: 8:52
 */

public class MApp extends Application{
    public static MApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        //初始化imageLoader框架
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();//开始构建

//全局初始化此配置
        ImageLoader.getInstance().init(config);
    }
    public static MApp getInstance(){
        return mInstance;
    }
}
