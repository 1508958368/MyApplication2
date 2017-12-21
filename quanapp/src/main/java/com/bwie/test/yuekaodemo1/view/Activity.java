package com.bwie.test.yuekaodemo1.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.bwie.test.yuekaodemo1.R;
import com.bwie.test.yuekaodemo1.adapter.LvAdapter;
import com.bwie.test.yuekaodemo1.bean.BaseBean;
import com.bwie.test.yuekaodemo1.presenter.BasePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class Activity extends AppCompatActivity implements Activity1{

    private XRecyclerView mXrecycleView;
    private BasePresenter basePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);
        initView();
        basePresenter = new BasePresenter(this);
        basePresenter.dopost();
    }

    private void initView() {
        mXrecycleView = (XRecyclerView) findViewById(R.id.xrecycleView);
        mXrecycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void show(String str) {

    }

    @Override
    public void showNews(BaseBean baseBean) {
        List<BaseBean.MiaoshaBean.ListBeanX> list = baseBean.getMiaosha().getList();
        LvAdapter lvAdapter = new LvAdapter(this, list);
        mXrecycleView.setAdapter(lvAdapter);
//        lvAdapter.setOnclick(new LvAdapter.Onclick() {
//            @Override
//            public void Onclick(int pid) {
////                Intent intent = new Intent(Activity.this, MainActivity.class);
////                intent.putExtra("pid",pid);
////                startActivity(intent);
//                Toast.makeText(Activity.this,pid,Toast.LENGTH_SHORT).show();
//            }
//        });
        lvAdapter.setOnclick(new LvAdapter.Onclick() {
            @Override
            public void Onclik(String pid) {
                Intent intent = new Intent(Activity.this, MainActivity.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
                Toast.makeText(Activity.this,pid,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
