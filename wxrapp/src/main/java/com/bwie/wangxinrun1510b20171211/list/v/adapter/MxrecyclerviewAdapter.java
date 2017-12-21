package com.bwie.wangxinrun1510b20171211.list.v.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.wangxinrun1510b20171211.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import bean.Goods;

/**
 * author: Wangxinrun
 * Date: 2017/12/12
 * Time: 9:50
 */

public class MxrecyclerviewAdapter extends RecyclerView.Adapter<MxrecyclerviewAdapter.MRecycylerHolder> {

    private Goods goods;
    private Context context;
    private ImageLoader imageLoader=ImageLoader.getInstance();

    public MxrecyclerviewAdapter(Goods goods, Context context) {
        this.goods = goods;
        this.context = context;
    }

    @Override
    public MRecycylerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        MRecycylerHolder mRecycylerHolder=new MRecycylerHolder(view);
        return mRecycylerHolder;
    }

    @Override
    public void onBindViewHolder(MRecycylerHolder holder, int position) {
        String[] split = goods.getData().get(position).getImages().split("\\|");
        holder.textView1.setText(goods.getData().get(position).getTitle());
        holder.textView2.setText(goods.getData().get(position).getPrice()+"");
        imageLoader.displayImage(split[0],holder.imageView);
    }

    @Override
    public int getItemCount() {
        return goods.getData().size();
    }

    class MRecycylerHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView1, textView2;

        public MRecycylerHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
            textView1 = itemView.findViewById(R.id.tv1);
            textView2 = itemView.findViewById(R.id.tv2);


        }
    }
}
