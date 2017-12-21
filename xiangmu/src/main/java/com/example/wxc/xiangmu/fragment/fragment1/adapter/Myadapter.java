package com.example.wxc.xiangmu.fragment.fragment1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wxc.xiangmu.R;

import java.util.ArrayList;

/**
 * author:Created by WangXinChi on 2017/12/15.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>{
    private Context context;
    private ArrayList<String> list;

    public Myadapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Myadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Myadapter.ViewHolder holder, int position) {
        holder.miaoshu.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView miaoshu;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            miaoshu = itemView.findViewById(R.id.miaoshu);
            img = itemView.findViewById(R.id.gridimg);
        }
    }
}
