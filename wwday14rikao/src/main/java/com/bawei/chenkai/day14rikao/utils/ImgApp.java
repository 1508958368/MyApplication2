package com.bawei.chenkai.day14rikao.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * Created by Wangrx on 2017/11/3.
 */

public class ImgApp extends ImageLoader {

    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}