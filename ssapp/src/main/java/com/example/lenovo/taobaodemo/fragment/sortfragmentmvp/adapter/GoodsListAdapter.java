package com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.GoodsList;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/12/14.
 */

public class GoodsListAdapter extends XRecyclerView.Adapter<GoodsListAdapter.MVH> {

    private List<GoodsList.DataBean> list;
    private Context context;


    public GoodsListAdapter(List<GoodsList.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.goodslistitem, null);
        MVH mvh = new MVH(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MVH holder, int position) {
        ImageLoader instance = ImageLoader.getInstance();
        instance.displayImage(list.get(position).getImages().split("//|")[0], holder.goodstupian);
        holder.titles.setText(list.get(position).getTitle());
        holder.subhead.setText(list.get(position).getSubhead());
        holder.newprice.setText(list.get(position).getBargainPrice() + "");
        holder.oldprice.setText(list.get(position).getPrice() + "");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MVH extends XRecyclerView.ViewHolder {

        private ImageView goodstupian;
        private TextView titles;
        private TextView subhead;
        private TextView newprice;
        private TextView oldprice;

        public MVH(View itemView) {
            super(itemView);
            goodstupian = (ImageView) itemView.findViewById(R.id.goodstupian);
            titles = (TextView) itemView.findViewById(R.id.settitles);
            subhead = (TextView) itemView.findViewById(R.id.subhead);
            newprice = (TextView) itemView.findViewById(R.id.newprice);
            oldprice = (TextView) itemView.findViewById(R.id.oldprice);
        }
    }


}
