package com.bwei.ssp.home_work.Fragment.Classification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bwei.ssp.home_work.Fragment.Classification.bean.Son_Bean;
import com.bwei.ssp.home_work.R;

import java.util.List;

/**
 * Created by lenovo on 2017/12/14.
 */

public class Item_adapter extends RecyclerView.Adapter<Item_adapter.Myhoder> {
    Context context;
    List<Son_Bean.DataBean.ListBean> list;

    OnItemClickLisenter lisenter;

    public void setLisenter(OnItemClickLisenter lisenter) {
        this.lisenter = lisenter;
    }

    public interface OnItemClickLisenter{
        void setOnclick(View view,int position);
    }

    public Item_adapter(Context context, List<Son_Bean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Item_adapter.Myhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.son_item, null);
        Myhoder myhoder = new Myhoder(view);
        return myhoder;
    }

    @Override
    public void onBindViewHolder(Item_adapter.Myhoder holder, final int position) {
       holder.tv.setText(list.get(position).getName());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lisenter.setOnclick(v,position);

            }
        });

    }

    @Override
    public int getItemCount() {


        return  list.size();
    }

    public class Myhoder extends RecyclerView.ViewHolder{
        private Button tv ;
        public Myhoder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv);
        }
    }
}
