package com.bwie.test.yuekaodemo1.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.test.yuekaodemo1.R;
import com.bwie.test.yuekaodemo1.adapter.ElvAdapter;
import com.bwie.test.yuekaodemo1.bean.PriceAndCount;
import com.bwie.test.yuekaodemo1.bean.ShowCarBean;
import com.bwie.test.yuekaodemo1.presenter.ShowCarPresenter;

import java.util.List;

public class SecondActivity extends AppCompatActivity implements ISecondActivity, View.OnClickListener {

    private ImageView mBackIv;
    private ExpandableListView mElv;
    /**
     * 全选/反选
     */
    private CheckBox mCheckAll;
    /**
     * 总价 :¥0
     */
    private TextView mSum;
    private ElvAdapter elvAdapter;
    private ShowCarPresenter showCarPresenter;
    /**
     * 去结算(0)
     */
    private TextView mTvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        showCarPresenter = new ShowCarPresenter(this);
        showCarPresenter.show();
        mCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elvAdapter.AllOrNone(mCheckAll.isChecked());
            }
        });
    }

    private void initView() {
        mBackIv = (ImageView) findViewById(R.id.back_iv);
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCheckAll = (CheckBox) findViewById(R.id.checkAll);
        mSum = (TextView) findViewById(R.id.sum);
        mTvCount = (TextView) findViewById(R.id.tvCount);
        mTvCount.setOnClickListener(this);
    }


    @Override
    public void show(String str) {

    }

    @Override
    public void showCar(List<ShowCarBean.DataBean> group, List<List<ShowCarBean.DataBean.ListBean>> child) {

        elvAdapter = new ElvAdapter(group, child, SecondActivity.this);
        mElv.setAdapter(elvAdapter);
        for (int i = 0; i < group.size(); i++) {
            mElv.expandGroup(i);

        }
    }

    public void setPriceAndCount(PriceAndCount priceAndCount) {
        mSum.setText("合计：" + priceAndCount.getPrice());
        mTvCount.setText("去结算(" + priceAndCount.getCount() + ")");
    }

    public void setAllChecked(boolean bool) {
        mCheckAll.setChecked(bool);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止内存泄露
        showCarPresenter.Dettouch();
        //注销EventBus
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tvCount:
                Intent intent = new Intent(SecondActivity.this, ThirdAvtivity.class);
                startActivity(intent);
                break;
        }
    }
}
