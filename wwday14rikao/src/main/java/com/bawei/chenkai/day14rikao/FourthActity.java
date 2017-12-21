package com.bawei.chenkai.day14rikao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.chenkai.day14rikao.dingdan.Adapter.MyAdapter;
import com.bawei.chenkai.day14rikao.dingdan.bean.OrderBean;
import com.bawei.chenkai.day14rikao.dingdan.presenter.MainPresenter;
import com.bawei.chenkai.day14rikao.dingdan.view.IMainActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FourthActity extends AppCompatActivity implements IMainActivity, View.OnClickListener {
    /**
     * 待支付
     */
    private RadioButton mRb1;
    /**
     * 已支付
     */
    private RadioButton mRb2;
    /**
     * 已取消
     */
    private RadioButton mRb3;
    private RadioGroup mRg;
    private XRecyclerView mXrv;
    private MainPresenter mainPresenter;
    private int page = 1;
    private List<OrderBean.DataBean> list = new ArrayList<>();
    private MyAdapter myAdapter;
    private ImageView mIvPop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_actity);
        initView();

        mXrv.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this, list);
        mXrv.setAdapter(myAdapter);

        //想去获取数据，测试一下数据是否获取到
        mainPresenter = new MainPresenter(this);
        mainPresenter.getOrder(true, "1", page + "");
        mXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                page = 1;
                mainPresenter.getOrder(true, "1", page + "");

            }

            @Override
            public void onLoadMore() {
                //加载更多
                page++;
                mainPresenter.getOrder(false, "1", page + "");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.dettach();
    }

    private void initView() {
        mRb1 = (RadioButton) findViewById(R.id.rb1);
        mRb2 = (RadioButton) findViewById(R.id.rb2);
        mRb3 = (RadioButton) findViewById(R.id.rb3);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mXrv = (XRecyclerView) findViewById(R.id.xrv);
        mIvPop = (ImageView) findViewById(R.id.iv_pop);
        mIvPop.setOnClickListener(this);
    }

    @Override
    public void showData(boolean isRefresh, List<OrderBean.DataBean> newList) {
        if (isRefresh) {
            //如果是刷新
            list.clear();
            list.addAll(newList);
            mXrv.refreshComplete();
        } else {
            //如果是加载更多
            list.addAll(newList);

//            mXrv.loadMoreComplete();
//            if (list.size() == 总条目数) {
            mXrv.setLoadingMoreEnabled(false);
//            }
        }
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_pop:
                //弹出popupwindow
                View view = View.inflate(FourthActity.this, R.layout.pop_item, null);
                TextView tv1 = view.findViewById(R.id.tv1);
                TextView tv2 = view.findViewById(R.id.tv2);
                TextView tv3 = view.findViewById(R.id.tv3);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                PopupWindow popupWindow = new PopupWindow(view, layoutParams.width, layoutParams.height);
                popupWindow.showAsDropDown(mIvPop, 0, 30);

                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(FourthActity.this, "tv1", Toast.LENGTH_SHORT).show();
                        mainPresenter.getOrder(true, "1", "1");
                    }
                });
                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(FourthActity.this, "tv2", Toast.LENGTH_SHORT).show();
                    }
                });
                tv3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(FourthActity.this, "tv3", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}