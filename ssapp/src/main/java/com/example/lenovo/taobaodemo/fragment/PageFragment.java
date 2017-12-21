package com.example.lenovo.taobaodemo.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.api.PageUrl;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.adapter.Head3Adapter;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.adapter.Jianjv;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.adapter.PageAdapter;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.bean.HeadbannerImg;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.presenter.Passer;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.view.IView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class PageFragment extends Fragment implements IView {

    private XBanner hxbn;
    private ArrayList<String> listxbn;
    private ArrayList<String> listtitle;
    private XRecyclerView xRecyclerView;
    private View headbanner;
    private PageAdapter adapter;
    private View head2;
    private View head3;
    private ImageView h2;
    private RecyclerView gridview;
    private ArrayList<String> listmiaoshu = new ArrayList<>();
    private ArrayList<Integer> listimg = new ArrayList<>();
    private Head3Adapter adapter1;
    private TextView pmd;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, null);
        listxbn = new ArrayList<>();
        listtitle = new ArrayList<>();

        listmiaoshu.add("京东超市");
        listmiaoshu.add("全球购");
        listmiaoshu.add("京东服饰");
        listmiaoshu.add("京东生鲜");
        listmiaoshu.add("京东到家");
        listmiaoshu.add("充值缴费");
        listmiaoshu.add("京东超市");
        listmiaoshu.add("京东超市");
        listmiaoshu.add("京东超市");
        listmiaoshu.add("京东超市");

        listimg.add(R.drawable.g1);
        listimg.add(R.drawable.g2);
        listimg.add(R.drawable.g3);
        listimg.add(R.drawable.g4);
        listimg.add(R.drawable.g1);
        listimg.add(R.drawable.g2);
        listimg.add(R.drawable.g3);
        listimg.add(R.drawable.g4);
        listimg.add(R.drawable.g6);
        listimg.add(R.drawable.shop);

        listmiaoshu.add("京东超市");
        listmiaoshu.add("全球购");
        listmiaoshu.add("京东服饰");
        listmiaoshu.add("京东生鲜");
        listmiaoshu.add("京东到家");
        listmiaoshu.add("充值缴费");
        listmiaoshu.add("京东超市");
        listmiaoshu.add("京东超市");
        listmiaoshu.add("京东超市");
        listmiaoshu.add("京东超市");

        listimg.add(R.drawable.g1);
        listimg.add(R.drawable.g2);
        listimg.add(R.drawable.g3);
        listimg.add(R.drawable.g4);
        listimg.add(R.drawable.g1);
        listimg.add(R.drawable.g2);
        listimg.add(R.drawable.g3);
        listimg.add(R.drawable.g4);
        listimg.add(R.drawable.g6);
        listimg.add(R.drawable.shop);

        //加载头布局xbanner
        headbanner = View.inflate(getActivity(), R.layout.headbanner, null);

        head2 = View.inflate(getActivity(), R.layout.head2, null);
        h2 = (ImageView) head2.findViewById(R.id.h2);
        h2.setImageResource(R.drawable.head2);

        head3 = View.inflate(getActivity(), R.layout.head3, null);
        gridview = (RecyclerView) head3.findViewById(R.id.gridviews);
        pmd = (TextView) head3.findViewById(R.id.pmd);
        TranslateAnimation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF,0,TranslateAnimation.RELATIVE_TO_SELF,0,TranslateAnimation.RELATIVE_TO_SELF,1,TranslateAnimation.RELATIVE_TO_SELF,0);
        translateAnimation.setDuration(2000);
        translateAnimation.setRepeatCount(1000000);
        pmd.setAnimation(translateAnimation);


        gridview.addItemDecoration(new Jianjv(10));
        gridview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter1 = new Head3Adapter(getActivity(), listmiaoshu, listimg);
        gridview.setAdapter(adapter1);
        //初始化控件
        xRecyclerView = (XRecyclerView) view.findViewById(R.id.xrlv);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.LineScalePulseOutRapid);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        adapter = new PageAdapter(getActivity());
        xRecyclerView.setAdapter(adapter);

        //上下拉加载更多
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            //下拉刷新
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();
                //xRecyclerView.refreshComplete();

            }

            //上拉加载更多
            @Override
            public void onLoadMore() {
                Toast.makeText(getActivity(), "更多", Toast.LENGTH_SHORT).show();
                xRecyclerView.loadMoreComplete();
            }
        });


        //添加头布局
        hxbn = headbanner.findViewById(R.id.headxbanner);
        hxbn.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(listxbn.get(position)).into((ImageView) view);
            }
        });

        //添加头布局
        xRecyclerView.addHeaderView(headbanner);
        xRecyclerView.addHeaderView(head2);
        xRecyclerView.addHeaderView(head3);
        new Passer(this).onLoad(PageUrl.HEAD_XBANNER);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        hxbn.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        hxbn.stopAutoPlay();
    }

    //请求返回的数据
    @Override
    public void getData(HeadbannerImg headbannerImg) {
        List<HeadbannerImg.DataBean> data = headbannerImg.getData();
        for (int i = 0; i < data.size(); i++) {
            listxbn.add(data.get(i).getIcon());
            listtitle.add(data.get(i).getTitle());
        }
        hxbn.setData(listxbn, listtitle);
    }
}
