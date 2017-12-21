package com.bwie.wangxinrun1510b20171211;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bwie.wangxinrun1510b20171211.list.v.Lview;
import com.bwie.wangxinrun1510b20171211.list.v.adapter.MxrecyclerviewAdapter;
import com.bwie.wangxinrun1510b20171211.list.v.p.Lpresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

import bean.Goods;

public class Main3Activity extends AppCompatActivity implements Lview {


    private XRecyclerView xRecyclerView;
    private MxrecyclerviewAdapter mxrecyclerviewAdapter;
    private Lpresenter lpresenter;
    String string = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ImageButton imageButton = (ImageButton) findViewById(R.id.ib1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        xRecyclerView = (XRecyclerView) findViewById(R.id.mxRecycler);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lpresenter = new Lpresenter(this);

        Map<String, String> map = new HashMap<>();
        map.put("pscid", "39");
        map.put("page", "1");

        lpresenter.getUrl("http://120.27.23.105/product/getProducts", map);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(Main3Activity.this, "刷新", Toast.LENGTH_SHORT).show();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                Toast.makeText(Main3Activity.this, "加载更多", Toast.LENGTH_SHORT).show();
                xRecyclerView.refreshComplete();
            }
        });


    }

    @Override
    public void getData(Goods goods) {

        mxrecyclerviewAdapter = new MxrecyclerviewAdapter(goods, Main3Activity.this);
        xRecyclerView.setAdapter(mxrecyclerviewAdapter);
    }
}
