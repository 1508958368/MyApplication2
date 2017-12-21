package com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.goodsxiangqing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.api.Url;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.GoodsList;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortRithtChildBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.presenter.Passer;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.view.GoodsListIView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class GoodsDetailsActivity extends AppCompatActivity implements GoodsListIView {

    private SortRithtChildBean.DataBean.ListBean listbean;
    private TextView listtitles;
    private XRecyclerView goodslist;
    private Passer passer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        Intent intent = getIntent();
        listbean = (SortRithtChildBean.DataBean.ListBean) intent.getSerializableExtra("listBean");

        passer = new Passer();
        passer.setGoodsListIView(this);
        passer.onLoad(Url.GOODSDDETAILS + "?pscid=" + listbean.getPscid(), 2);

        //初始化控件
        initView();
    }

    //初始化控件
    private void initView() {
        listtitles = (TextView) this.findViewById(R.id.listtitles);
        listtitles.setText(listbean.getName() + "列表");
        goodslist = (XRecyclerView) this.findViewById(R.id.goodslist);
        goodslist.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void getGoodsList(GoodsList goodsList) {

    }

    //获取商品列表数据

    /*public void getGoodsList(GoodsList goodsList) {
        if ()
            Toast.makeText(this, goodsList.getData().toString() + "", Toast.LENGTH_SHORT).show();

    }*/
}
