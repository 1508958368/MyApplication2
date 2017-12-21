package com.example.wxc.xiangmu.fragment.fragment1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wxc.xiangmu.R;
import com.example.wxc.xiangmu.api.Api;
import com.example.wxc.xiangmu.fragment.GlideImageLaoder;
import com.example.wxc.xiangmu.fragment.fragment1.adapter.Myadapter;
import com.example.wxc.xiangmu.fragment.fragment1.bean.Imgs;
import com.example.wxc.xiangmu.fragment.fragment1.presenter1.Mpresenter3;
import com.example.wxc.xiangmu.fragment.fragment1.view.Iview;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * author:Created by WangXinChi on 2017/12/15.
 */


public class Fragment1 extends Fragment implements Iview{
    private Banner banner;
    private List<String> mListImage, mListTitle;
    private Mpresenter3 mpresenter3;
    private ArrayList<String> banner1;
    private XRecyclerView xRecyclerView;
    private Myadapter myadapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment1,null);
        banner = (Banner) view.findViewById(R.id.banner);
        xRecyclerView = (XRecyclerView) view.findViewById(R.id.xrlv);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.LineScalePulseOutRapid);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);

        xRecyclerView.setAdapter(myadapter);
        mpresenter3 = new Mpresenter3(this);
        banner1 = new ArrayList<>();
       mpresenter3.onLoad(Api.BANNER);
        //设置Banner样式
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器

        //实例化图片集合
        /*mListImage = new ArrayList<>();
        //将图片放入集合中
        mListImage.add("http://img3.imgtn.bdimg.com/it/u=3823223463,3047520970&fm=23&gp=0.jpg");
        mListImage.add("http://img.hc360.com/auto-a/info/images/200803/8998419-10.jpg");
        mListImage.add("http://img.ichemo.cn/model/5188ebc96c74dd.jpg");
        mListImage.add("http://img0.imgtn.bdimg.com/it/u=2631448432,2817427988&fm=214&gp=0.jpg");
        mListImage.add("http://i.ebayimg.com/00/s/MzU1WDcwOQ==/%24%28KGrHqR,%21hYFDuguSMpNBQ8e+seDCQ~~60_1.JPG?set_id=880000500F");
        //设置Banner图片集合
        banner.setImages(mListImage);
        //设置Banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //实例化Title集合
        mListTitle = new ArrayList<>();
        //将标题放入集合
        mListTitle.add("第一张图片");
        mListTitle.add("第二张图片");
        mListTitle.add("第三张图片");
        mListTitle.add("第四张图片");
        mListTitle.add("第五张图片");
        //设置Banner标题集合（当banner样式有显示title时）
        banner.setBannerTitles(mListTitle);
        //设置轮播时间
        banner.setDelayTime(1000);*/
        //设置指示器位置（当banner模式中有指示器时）



        return view;
    }

    @Override
    public void getData(List<Imgs.DataBean> list) {
        for (int i = 0; i <list.size() ; i++) {


            banner1.add(list.get(i).getIcon());
                banner.setImages(banner1);
            Log.e(TAG, "getData: "+banner1.size() );
            banner.setImageLoader(new GlideImageLaoder());
            banner.start();


        }
    }

}
