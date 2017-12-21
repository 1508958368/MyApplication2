package com.bwei.ssp.home_work.Fragment.Classification.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.ssp.home_work.Fragment.Classification.bean.CommodityBean;
import com.bwei.ssp.home_work.R;

import java.util.List;

/**
 * Created by lenovo on 2017/12/8.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyHoder> {
    Context context;
    List<CommodityBean.DataBean> bean;
    public Myadapter(Context context, List<CommodityBean.DataBean> bean) {
        this.context = context;
        this.bean = bean;
    }
    OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public Myadapter.MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.left_fenlei_reclyleview_item, null);
        MyHoder myHoder = new MyHoder(view);
        return myHoder;
    }



    @Override
    public void onBindViewHolder(Myadapter.MyHoder holder, final int position) {
        holder.tv.setText(bean.get(position).getName());
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 listener.onItemclick(v,position);
             }
         });
    }

    @Override
    public int getItemCount() {
        return bean.size();
    }

    public class MyHoder extends RecyclerView.ViewHolder{
        private final TextView tv;

        public MyHoder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    public interface OnItemClickListener{
        void onItemclick(View view, int position);
    }
}
