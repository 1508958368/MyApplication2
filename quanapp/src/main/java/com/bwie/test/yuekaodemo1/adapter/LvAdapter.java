package com.bwie.test.yuekaodemo1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.test.yuekaodemo1.R;
import com.bwie.test.yuekaodemo1.bean.BaseBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * author:Created by 董博 on 2017/12/20.
 */

public class LvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<BaseBean.MiaoshaBean.ListBeanX> list;
    private MyViewHolder myViewHolder;
    private Onclick onclick;


    public interface Onclick {
        void Onclik(String pid);
    }

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    public LvAdapter(Context context, List<BaseBean.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BaseBean.MiaoshaBean.ListBeanX listBeanX = list.get(position);
        MyViewHolder myViewHolder1 = (MyViewHolder) holder;
        String images = listBeanX.getImages();
        String[] split = images.split("\\|");
        ImageLoader.getInstance().displayImage(split[0], myViewHolder1.imv);
        myViewHolder1.tv_ti.setText(listBeanX.getTitle());
        myViewHolder1.tv_pr.setText(listBeanX.getPrice() + "");
        myViewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.Onclik(listBeanX.getPid()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imv;
        private final TextView tv_ti;
        private final TextView tv_pr;

        public MyViewHolder(View itemView) {
            super(itemView);
            imv = itemView.findViewById(R.id.imv);
            tv_ti = itemView.findViewById(R.id.tv_ti);
            tv_pr = itemView.findViewById(R.id.tv_pr);
        }
    }
}
