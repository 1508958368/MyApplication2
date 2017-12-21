package com.bwei.ssp.home_work.Fragment.Classification.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.ssp.home_work.Fragment.Classification.bean.Son_Bean;
import com.bwei.ssp.home_work.Fragment.Shop.ShopActivity;
import com.bwei.ssp.home_work.R;

import java.util.List;

/**
 * Created by lenovo on 2017/12/8.
 */

public class OnMyadapter extends RecyclerView.Adapter<OnMyadapter.MyHoder> {
    Context context;
    Son_Bean bean;


    public OnMyadapter(Context context, Son_Bean bean) {
        this.context = context;
        this.bean = bean;

    }



    @Override
    public OnMyadapter.MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.right_itme, null);
        OnMyadapter.MyHoder myHoder = new MyHoder(view);
        return myHoder;
    }
    @Override
    public void onBindViewHolder(OnMyadapter.MyHoder holder, final int position) {

        holder.bt.setText(bean.getData().get(position).getName());
        final List<Son_Bean.DataBean.ListBean> list = bean.getData().get(position).getList();
        holder.rlv.setLayoutManager(new GridLayoutManager(context,3));
        Item_adapter item_adapter = new Item_adapter(context, list);
        holder.rlv.setAdapter(item_adapter);
        holder.rlv.setLayoutManager(new GridLayoutManager(context,3));



        item_adapter.setLisenter(new Item_adapter.OnItemClickLisenter() {
            @Override
            public void setOnclick(View view, int position) {

                int pscid = list.get(position).getPscid();
                Intent intent = new Intent(context, ShopActivity.class);
                intent.putExtra("pscid",pscid);
                Toast.makeText(context,"pscid"+pscid,Toast.LENGTH_LONG).show();
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bean.getData().size();
    }

    public class MyHoder extends RecyclerView.ViewHolder{
        private TextView bt;
        private RecyclerView rlv;

        public MyHoder(View itemView) {
            super(itemView);
            bt= itemView.findViewById(R.id.bt);
            rlv= itemView.findViewById(R.id.item_rlv);
        }
    }
}
