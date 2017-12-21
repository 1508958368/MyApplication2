package com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortBean;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/12/13.
 */

public class SortLeftAdapter extends RecyclerView.Adapter<SortLeftAdapter.MVH> {

    private List<SortBean.DataBean> list;
    private Context context;
    private setonclick setonclick;

    public interface setonclick {
        void onclick(View view, int positon, String str);
    }

    public SortLeftAdapter(List<SortBean.DataBean> list, Context context, setonclick setonclick) {
        this.list = list;
        this.context = context;
        this.setonclick = setonclick;
    }

    @Override
    public MVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View sortleft = View.inflate(context, R.layout.sortleft, null);
        MVH mvh = new MVH(sortleft);
        return mvh;
    }

    @Override
    public void onBindViewHolder(final MVH holder, final int position) {
        holder.sortname.setText(list.get(position).getName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setonclick != null) {
                    setonclick.onclick(holder.linearLayout, position, list.get(position).getCid() + "");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MVH extends RecyclerView.ViewHolder {
        private TextView sortname;
        private LinearLayout linearLayout;

        public MVH(View itemView) {
            super(itemView);

            sortname = (TextView) itemView.findViewById(R.id.sortname);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.sortleft);
        }
    }

}
