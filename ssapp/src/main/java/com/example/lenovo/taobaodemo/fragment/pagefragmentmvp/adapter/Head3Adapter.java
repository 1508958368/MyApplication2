package com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.taobaodemo.R;

import java.util.ArrayList;

/**
 * author:Created by WangZhiQiang on 2017/12/13.
 */

public class Head3Adapter extends RecyclerView.Adapter<Head3Adapter.MVH> {

    private Context context;
    private ArrayList<String> list;
    private ArrayList<Integer> listimg;

    public Head3Adapter(Context context, ArrayList<String> list, ArrayList<Integer> listimg) {
        this.context = context;
        this.list = list;
        this.listimg = listimg;
    }

    @Override
    public MVH onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.head3item, null);
        MVH mvh = new MVH(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MVH holder, int position) {
        holder.img.setImageResource(listimg.get(position));
        holder.miaoshu.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MVH extends RecyclerView.ViewHolder {
        TextView miaoshu;
        ImageView img;

        public MVH(View itemView) {
            super(itemView);
            miaoshu = itemView.findViewById(R.id.miaoshu);
            img = itemView.findViewById(R.id.gridimg);

        }
    }

}
