package com.bwie.test.demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bwie.test.demo2.adapter.ElvAdapter;
import com.bwie.test.demo2.bean.GetCartBean;
import com.bwie.test.demo2.bean.PriceAndCount;
import com.bwie.test.demo2.presenter.GetCartPresenter;
import com.bwie.test.demo2.view.ISecondListener;

import java.util.List;

public class SecondActivity extends AppCompatActivity  implements ISecondListener {
    private GetCartPresenter getCartPresenter;
    private ExpandableListView mElv;
    /**
     * 全选
     */
    private CheckBox mCb;
    /**
     * 合计:
     */
    private TextView mTvTotal;
    /**
     * 去结算(0)
     */
    private TextView mTvCount;
    private ElvAdapter elvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        getCartPresenter = new GetCartPresenter(this);
        getCartPresenter.getCart();

        mCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elvAdapter.AllOrNone(mCb.isChecked());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getCartPresenter.dettach();
    }

    @Override
    public void show(List<GetCartBean.DataBean> group, List<List<GetCartBean.DataBean.ListBean>> child) {
        elvAdapter = new ElvAdapter(this, group, child);
        mElv.setGroupIndicator(null);
        mElv.setAdapter(elvAdapter);
        for (int i = 0; i < group.size(); i++) {
            mElv.expandGroup(i);

        }
    }

    private void initView() {
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCb = (CheckBox) findViewById(R.id.cb);
        mTvTotal = (TextView) findViewById(R.id.tvTotal);
        mTvCount = (TextView) findViewById(R.id.tvCount);
    }

    public void setPriceAndCount(PriceAndCount priceAndCount) {
        mTvTotal.setText("合计：" + priceAndCount.getPrice());
        mTvCount.setText("去结算(" + priceAndCount.getCount() + ")");
    }

    public void setAllChecked(boolean bool) {
        mCb.setChecked(bool);
    }
}
