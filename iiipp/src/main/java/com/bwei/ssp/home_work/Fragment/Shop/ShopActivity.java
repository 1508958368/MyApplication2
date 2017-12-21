package com.bwei.ssp.home_work.Fragment.Shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bwei.ssp.home_work.Fragment.Shop.Shop_presenter.Shop_presenter;
import com.bwei.ssp.home_work.Fragment.Shop.Sop_view.Shop_view;
import com.bwei.ssp.home_work.Fragment.Shop.adapter.Shop_Adapter;
import com.bwei.ssp.home_work.Fragment.Shop.bean.Shop_Bean;
import com.bwei.ssp.home_work.Fragment.Xiangqing.XiangqingActivity;
import com.bwei.ssp.home_work.R;

public class ShopActivity extends AppCompatActivity implements Shop_view{

    private RecyclerView rlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        rlv = (RecyclerView) findViewById(R.id.shop_rlv);
        Intent intent = getIntent();
        int pscid = intent.getIntExtra("pscid", 2);
        Log.e("pscid", "pscid="+pscid );
        Shop_presenter shop_presenter = new Shop_presenter(this);
        shop_presenter.getJs(pscid);
    }

    @Override
    public void getData(final Shop_Bean bean) {
        if(bean.getData()!=null){
            rlv.setLayoutManager(new LinearLayoutManager(ShopActivity.this));
            Shop_Adapter shop_adapter = new Shop_Adapter(ShopActivity.this, bean.getData());
            rlv.setAdapter(shop_adapter);
            shop_adapter.setLisenter(new Shop_Adapter.OnItemClickLisenter() {
                @Override
                public void onitenmCklick(View view, int position) {
                    int pid = bean.getData().get(position).getPid();
                    Toast.makeText(ShopActivity.this,"pid="+pid,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ShopActivity.this, XiangqingActivity.class);
                    intent.putExtra("pid",pid);
                    startActivity(intent);
                }
            });
        }else {

            Log.e("**", "没数据了 " );
            Toast.makeText(ShopActivity.this,"接口没数据",Toast.LENGTH_LONG).show();
        }
    }
}
