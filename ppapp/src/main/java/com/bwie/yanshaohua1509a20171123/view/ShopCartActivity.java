package com.bwie.yanshaohua1509a20171123.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.yanshaohua1509a20171123.R;
import com.bwie.yanshaohua1509a20171123.adapter.MyAdapter;
import com.bwie.yanshaohua1509a20171123.bean.GoodsBean;
import com.bwie.yanshaohua1509a20171123.eventbus.CheckBoxEventBus;
import com.bwie.yanshaohua1509a20171123.eventbus.SumEventBus;
import com.bwie.yanshaohua1509a20171123.precenter.GoodsCartPrecenter;
import com.bwie.yanshaohua1509a20171123.view.iview.IShopCartActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * 闫少华
 * 实现购物车的activity
 */
public class ShopCartActivity extends AppCompatActivity implements IShopCartActivity, View.OnClickListener {

    private MyAdapter myAdapter;
    private ImageView mBackIv;
    private ExpandableListView mElv;
    /**
     * 全选/反选
     */
    private CheckBox mCheckAll;
    private LinearLayout mActivityShopCart;
    private List<GoodsBean.DataBean> data;
    private GoodsCartPrecenter goodsCartPrecenter;
    private TextView mSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);
        initView();

        //注册EventBus
        EventBus.getDefault().register(this);
        goodsCartPrecenter = new GoodsCartPrecenter(this);
        goodsCartPrecenter.getGoods();
    }

    @Override
    public void show(String str) {
        Toast.makeText(ShopCartActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNews(GoodsBean goodsBean) {
        Log.e("TAG", "showNews: " + goodsBean.getMsg());
        data = goodsBean.getData();
        myAdapter = new MyAdapter(ShopCartActivity.this, data);
        mElv.setAdapter(myAdapter);
        mElv.setGroupIndicator(null);
        for (int i = 0; i < data.size(); i++) {
            mElv.expandGroup(i);
        }
    }


    private void initView() {
        mBackIv = (ImageView) findViewById(R.id.back_iv);
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCheckAll = (CheckBox) findViewById(R.id.checkAll);
        mActivityShopCart = (LinearLayout) findViewById(R.id.activity_shop_cart);
        mBackIv.setOnClickListener(this);
        mActivityShopCart.setOnClickListener(this);
        mSum = (TextView) findViewById(R.id.sum);
        mCheckAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back_iv:
                ShopCartActivity.this.finish();
                break;
            case R.id.checkAll:
                myAdapter.changeAll(mCheckAll.isChecked());
                break;
            case R.id.activity_shop_cart:
                break;
        }
    }

    //接受EventBus传来的数据 改变UI数据
    @Subscribe
    public void onMessage(SumEventBus sumEventBus) {
        mSum.setText("总价 :¥"+sumEventBus.getSum());
    }
    @Subscribe
    public void onMessage(CheckBoxEventBus checkBoxEventBus) {
        mCheckAll.setChecked(checkBoxEventBus.ischeckedAll());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止内存泄露
        goodsCartPrecenter.Dettouch();
        //注销EventBus
        EventBus.getDefault().unregister(this);
    }
}
