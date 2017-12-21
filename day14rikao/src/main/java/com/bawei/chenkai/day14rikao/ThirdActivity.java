package com.bawei.chenkai.day14rikao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.chenkai.day14rikao.Gouwuche.model.bean.CartBean;
import com.bawei.chenkai.day14rikao.Gouwuche.model.bean.CountPriceBean;
import com.bawei.chenkai.day14rikao.Gouwuche.presenter.CartPresenter;
import com.bawei.chenkai.day14rikao.Gouwuche.view.IView.IMainActivity;
import com.bawei.chenkai.day14rikao.Gouwuche.view.adapter.MyAdapter;
import com.bawei.chenkai.day14rikao.Gouwuche.view.custom.CartExpanableListview;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity implements IMainActivity, View.OnClickListener{
    private CartExpanableListview expanableListview;
    private String cartUrl = "https://www.zhaoapi.cn/product/getCarts?uid=3004";
    private CartPresenter cartPresenter;
    private Gson gson;
    private MyAdapter myAdapter;
    private CheckBox check_all;
    private TextView text_total;
    private TextView text_buy;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                CountPriceBean countPriceBean = (CountPriceBean) msg.obj;

                //设置
                text_total.setText("合计:¥"+countPriceBean.getPrice());
                text_buy.setText("去结算("+countPriceBean.getCount()+")");
            }else  if (msg.what == 1){//改变全选
                boolean flag = (boolean) msg.obj;

                check_all.setChecked(flag);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        check_all = (CheckBox) findViewById(R.id.check_all);
        text_total = (TextView) findViewById(R.id.text_total);
        text_buy = (TextView) findViewById(R.id.text_buy);
        expanableListview = (CartExpanableListview) findViewById(R.id.expanable_listview);
        //去掉默认的指示器
        expanableListview.setGroupIndicator(null);

        cartPresenter = new CartPresenter(this);
        gson = new Gson();

        //全选:...点击事件
        check_all.setOnClickListener(this);
        text_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdActivity.this,FourthActity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //请求数据
        cartPresenter.getCartData(cartUrl);

    }

    @Override
    public void getSuccessCartData(String json) {
        //解析数据
        CartBean cartBean = gson.fromJson(json, CartBean.class);

        //一个是一级标题的数据
        List<CartBean.DataBean> listGroup = cartBean.getData();

        //所有子条目的数据
        List<List<CartBean.DataBean.ListBean>> listChilds = new ArrayList<>();
        for (int i=0;i<listGroup.size();i++){
            listChilds.add(listGroup.get(i).getList());
        }

        //设置适配器
        myAdapter = new MyAdapter(ThirdActivity.this, listGroup, listChilds,handler);
        expanableListview.setAdapter(myAdapter);

        //展开所有
        for (int i=0;i<listGroup.size();i++){
            expanableListview.expandGroup(i);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.check_all:
                myAdapter.setIfCheckAll(check_all.isChecked());

                break;
        }
    }
}

