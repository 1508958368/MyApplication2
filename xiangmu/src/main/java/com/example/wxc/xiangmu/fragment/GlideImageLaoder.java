package com.example.wxc.xiangmu.fragment;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * author:Created by WangXinChi on 2017/12/15.
 */

 public class GlideImageLaoder extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        com.nostra13.universalimageloader.core.ImageLoader imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        imageLoader.displayImage((String) path,imageView);
    }
}
