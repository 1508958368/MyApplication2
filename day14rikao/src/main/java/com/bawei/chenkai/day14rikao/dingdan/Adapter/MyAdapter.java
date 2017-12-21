package com.bawei.chenkai.day14rikao.dingdan.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.chenkai.day14rikao.R;
import com.bawei.chenkai.day14rikao.dingdan.bean.OrderBean;
import com.bawei.chenkai.day14rikao.dingdan.presenter.CancleOrderPresenter;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/12/21.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<OrderBean.DataBean> list;
    private final CancleOrderPresenter cancleOrderPresenter;

    public MyAdapter(Context context, List<OrderBean.DataBean> list) {
        this.context = context;
        this.list = list;
        cancleOrderPresenter = new CancleOrderPresenter();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderBean.DataBean dataBean = list.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tv_title.setText(dataBean.getTitle());
        int status = dataBean.getStatus();
        if (status == 0) {
            myViewHolder.tv_status.setText("待支付");
            myViewHolder.tv.setText("取消订单");
            myViewHolder.tv_status.setTextColor(Color.RED);
        } else if (status == 1) {
            myViewHolder.tv_status.setText("已支付");
            myViewHolder.tv.setText("查看订单");
            myViewHolder.tv_status.setTextColor(Color.BLACK);
        } else {
            myViewHolder.tv_status.setText("已取消");
            myViewHolder.tv.setText("查看订单");
            myViewHolder.tv_status.setTextColor(Color.BLACK);
        }

        myViewHolder.tv_price.setText("价格：" + dataBean.getPrice());
        myViewHolder.tv_time.setText("创建时间：" + dataBean.getCreatetime());

        myViewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("提示")
                        .setMessage("确定取消订单吗？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cancleOrderPresenter.cancleOrder("1", dataBean.getOrderid() + "");
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_title;
        private final TextView tv_status;
        private final TextView tv_price;
        private final TextView tv_time;
        private final TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
