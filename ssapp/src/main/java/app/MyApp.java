package app;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class MyApp extends Application {

    private static MyApp mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ImageLoaderConfiguration loaderConfiguration = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(loaderConfiguration);
    }

    public static MyApp getInstance() {
        return mInstance;
    }

    ;

}
