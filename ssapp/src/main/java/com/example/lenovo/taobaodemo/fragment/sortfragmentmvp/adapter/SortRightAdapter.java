package com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortRithtChildBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.goodsxiangqing.GoodsDetailsActivity;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/12/13.
 */

public class SortRightAdapter extends RecyclerView.Adapter<SortRightAdapter.MVH> {

    private List<SortRithtChildBean.DataBean> list;
    private Context context;

    public SortRightAdapter(List<SortRithtChildBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.sortright, null);
        MVH mvh = new MVH(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MVH holder, int position) {
        holder.textView.setText(list.get(position).getName());
        final List<SortRithtChildBean.DataBean.ListBean> listBeanList = this.list.get(position).getList();
        ChildssAdapter childssAdapter = new ChildssAdapter(context, listBeanList);
        holder.gridview.setAdapter(childssAdapter);
        holder.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SortRithtChildBean.DataBean.ListBean listBean = listBeanList.get(i);
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                intent.putExtra("listBean", listBean);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MVH extends RecyclerView.ViewHolder {
        TextView textView;
        GridView gridview;

        public MVH(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.titless);
            gridview = (GridView) itemView.findViewById(R.id.childssgridview);
        }
    }

}
