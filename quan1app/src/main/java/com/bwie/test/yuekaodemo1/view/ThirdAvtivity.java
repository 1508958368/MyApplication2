package com.bwie.test.yuekaodemo1.view;

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

import com.bwie.test.yuekaodemo1.R;
import com.bwie.test.yuekaodemo1.adapter.MyAdapter;
import com.bwie.test.yuekaodemo1.bean.OrderBean;
import com.bwie.test.yuekaodemo1.presenter.OrderPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ThirdAvtivity extends AppCompatActivity implements View.OnClickListener,IThirdActivity {

    private ImageView mIvPop;
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
    private OrderPresenter orderPresenter;
    private int page = 1;
    private List<OrderBean.DataBean> list = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_avtivity);
        initView();
        mXrv.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this, list);
        mXrv.setAdapter(myAdapter);
        orderPresenter = new OrderPresenter(this);
        orderPresenter.getOrder(true,"1",page+"");
        mXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                orderPresenter.getOrder(true, "1", page + "");
            }

            @Override
            public void onLoadMore() {
                //加载更多
                page++;
                orderPresenter.getOrder(false, "1", page + "");
            }
        });
    }

    private void initView() {
        mIvPop = (ImageView) findViewById(R.id.iv_pop);
        mIvPop.setOnClickListener(this);
        mRb1 = (RadioButton) findViewById(R.id.rb1);
        mRb2 = (RadioButton) findViewById(R.id.rb2);
        mRb3 = (RadioButton) findViewById(R.id.rb3);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mXrv = (XRecyclerView) findViewById(R.id.xrv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_pop:
                View view = View.inflate(ThirdAvtivity.this, R.layout.pop_item, null);
                TextView tv1 = view.findViewById(R.id.tv1);
                TextView tv2 = view.findViewById(R.id.tv2);
                TextView tv3 = view.findViewById(R.id.tv3);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                PopupWindow popupWindow = new PopupWindow(view, layoutParams.width, layoutParams.height);
                popupWindow.showAsDropDown(mIvPop, 0, 30);
                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ThirdAvtivity.this, "tv1", Toast.LENGTH_SHORT).show();
                        orderPresenter.getOrder(true, "1", "1");
                    }
                });
                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ThirdAvtivity.this, "tv2", Toast.LENGTH_SHORT).show();
                    }
                });
                tv3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ThirdAvtivity.this, "tv3", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        orderPresenter.dettach();
    }
    @Override
    public void showData(boolean isRefresh, List<OrderBean.DataBean> newList) {
        if (isRefresh){
            list.clear();
            list.addAll(newList);
            mXrv.refreshComplete();
        }else{
            list.addAll(newList);
            mXrv.setLoadingMoreEnabled(false);
        }
        myAdapter.notifyDataSetChanged();
    }
}
