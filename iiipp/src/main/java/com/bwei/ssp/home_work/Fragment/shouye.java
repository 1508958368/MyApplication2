package com.bwei.ssp.home_work.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.bwei.ssp.home_work.Fragment.Shouye.Home_pagActivity;
import com.bwei.ssp.home_work.Fragment.Shouye.SearchActivity;
import com.bwei.ssp.home_work.Fragment.Shouye.Souye_Madapter.Shouye_adapter;
import com.bwei.ssp.home_work.Fragment.Shouye.bean.Grild_Bean;
import com.bwei.ssp.home_work.Fragment.Shouye.bean.Shouye_Bean;
import com.bwei.ssp.home_work.Fragment.Shouye.bean.Shouye_Xbanner_Bean;
import com.bwei.ssp.home_work.Fragment.Shouye.presenter.Presenter;
import com.bwei.ssp.home_work.R;
import com.stx.xhb.xbanner.XBanner;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/12/6.
 */

public class shouye extends Fragment implements com.bwei.ssp.home_work.Fragment.Shouye.view.View {

    private Presenter p;
    private RecyclerView rlv;
    private XBanner xbanner ,xban;
    private ViewFlipper pmd;
    private ImageButton sys;
    private List<Shouye_Xbanner_Bean.DataBean> data;
    private EditText search;
    private TextView tv_hour;
    private TextView tv_minute;
    private TextView tv_second;
    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;

    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
                computeTime();
                if (mHour<10){
                    tv_hour.setText("0"+mHour+"");
                }else {
                    tv_hour.setText("0"+mHour+"");
                }
                if (mMin<10){
                    tv_minute.setText("0"+mMin+"");
                }else {
                    tv_minute.setText(mMin+"");
                }
                if (mSecond<10){
                    tv_second.setText("0"+mSecond+"");
                }else {
                    tv_second.setText(mSecond+"");
                }
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.shouye, null);
        rlv = v.findViewById(R.id.rlv);
        xbanner = v.findViewById(R.id.xbanner);
        xban = v.findViewById(R.id.xban);
        search = v.findViewById(R.id.search);
        tv_hour = v.findViewById(R.id.tv_hour);
        tv_minute = v.findViewById(R.id.tv_minute);
        tv_second = v.findViewById(R.id.tv_second);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        sys = v.findViewById(R.id.syis);
        sys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,001);
            }
        });
        p = new Presenter(this);
        p.getJs();
        p.getXjs();

        rlv.setLayoutManager(new GridLayoutManager(getContext(),5));
        List<Grild_Bean > list =new ArrayList<>();

        list.add(new Grild_Bean("天猫",R.drawable.g1));
        list.add(new Grild_Bean("聚划算",R.drawable.g2));
        list.add(new Grild_Bean("天猫国际",R.drawable.g3));
        list.add(new Grild_Bean("外卖",R.drawable.g4));
        list.add(new Grild_Bean("天猫超市",R.drawable.g5));
        list.add(new Grild_Bean("充值中心",R.drawable.g6));
        list.add(new Grild_Bean("飞猪旅行",R.drawable.g7));
        list.add(new Grild_Bean("领金币",R.drawable.g8));
        list.add(new Grild_Bean("拍卖",R.drawable.g9));
        list.add(new Grild_Bean("分类",R.drawable.g10));
         Shouye_adapter shouye_adapter = new Shouye_adapter(getContext(), list);
         rlv.setAdapter(shouye_adapter);

        pmd = v.findViewById(R.id.pmd);
        pmd.addView(View.inflate(getActivity(),R.layout.pmd,null));

        return v;
    }

    @Override
    public void getData(Shouye_Bean bean) {


    }

    @Override
    public void getBannerData(Shouye_Xbanner_Bean bean) {
        final List<String>Image =new ArrayList<>();
        List<String>tit =new ArrayList<>();
        data = bean.getData();
        for (int i = 0; i < data.size() ; i++) {
            Image.add(data.get(i).getIcon());
            tit.add(data.get(i).getTitle());

        }

        xbanner.setData(Image,tit);
        xbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(Image.get(position)).into((ImageView) view);
            }
        });

        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                int type = data.get(position).getType();
                if (type==0){
                    String url = data.get(position).getUrl();
                    Intent intent = new Intent(getActivity(), Home_pagActivity.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getActivity(), Home_pagActivity.class);
                    intent.putExtra("url","https://lmnz.tmall.com/p/rd584898.htm?ali_trackid=17_c536f8fa218a5101cedb52728bcbac41&spm=a231o.7076277/a.19985496532.1");
                    startActivity(intent);
                }
            }
        });

        xban.setPageChangeDuration(1000);
        xban.setData(Image,tit);
        xban.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(Image.get(position)).into((ImageView) view);
            }
        });
    }
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        xbanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xbanner.stopAutoPlay();
    }
    @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);

         if (requestCode == 001) {
             if (data != null) {
                 Bundle extras = data.getExtras();
                 if (extras == null) {
                     return;
                 }
                 if (extras.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                     String string = extras.getString(CodeUtils.RESULT_STRING);
                     Toast.makeText(getContext(), "解析结果:" + string, Toast.LENGTH_LONG).show();
                 } else if (extras.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                     Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                 }
             }

         }

     }
}
