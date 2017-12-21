package com.dash.a18_shopping_cart.view;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dash.a18_shopping_cart.model.bean.CartBean;
import com.dash.a18_shopping_cart.model.bean.CountPriceBean;
import com.dash.a18_shopping_cart.view.IView.IMainActivity;
import com.dash.a18_shopping_cart.view.adapter.MyAdapter;
import com.dash.a18_shopping_cart.view.custom.CartExpanableListview;
import com.dash.a18_shopping_cart.presenter.CartPresenter;
import com.dash.a18_shopping_cart.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 *1.点击全选:选中/未选中...调用适配器中的方法...myAdapter.setIsCheckAll(true);来设置所有的一级和二级是否选中,计算
 *
 * 2.一级列表的点击事件:
 *      2.1改变当前一级选中的状态...dataBean.setChoosed(! dataBean.isChoosed());
 *      2.2根据当前一级的状态,改变该组里面二级列表的状态....changeChilState(groupPosition,dataBean.isChoosed());
 *      2.3通过判断所有的一级组是否选中,来决定是否全选选中:...changeAllState(isAllGroupChecked());
 *      2.4发送价格个数量:...sendPriceAndCount();
 *      2.5刷新适配器
 * 3.二级列表点击事件:
 *      3.1点击改变当前子条目状态:...listBean.setChildChoosed(! listBean.isChildChoosed());//相反
 *      3.2发送价钱和数量给界面显示....sendPriceAndCount();
 *      3.3如果当前子条目是选中状态
 *          3.3.1选中
 *              判断一下当前组中所有的子条目是否全部选中:...isAllChildSelected(groupPosition)
 *              如果全部选中改变一下当前组的状态:...changGroupState(groupPosition,true);...确定是否改变全选changeAllState(isAllGroupChecked());
 *          3.3.2未选中
 *              changGroupState(groupPosition,false);改变当前组false...是否全选changeAllState(isAllGroupChecked());
 */
public class MainActivity extends AppCompatActivity implements IMainActivity, View.OnClickListener {

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
        setContentView(R.layout.activity_main);

        check_all =(CheckBox) findViewById(R.id.check_all);
        text_total = (TextView)findViewById(R.id.text_total);
        text_buy =(TextView) findViewById(R.id.text_buy);
        expanableListview =(CartExpanableListview) findViewById(R.id.expanable_listview);
        //去掉默认的指示器
        expanableListview.setGroupIndicator(null);

        cartPresenter = new CartPresenter(this);
        gson = new Gson();

        //全选:...点击事件
        check_all.setOnClickListener(this);
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
        myAdapter = new MyAdapter(MainActivity.this, listGroup, listChilds,handler);
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
