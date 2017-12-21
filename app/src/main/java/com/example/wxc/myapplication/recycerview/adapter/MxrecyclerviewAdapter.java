package com.example.wxc.myapplication.recycerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wxc.myapplication.R;
import com.example.wxc.myapplication.bean.Goods;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * author:Created by WangXinChi on 2017/12/12.
 */

public class MxrecyclerviewAdapter extends RecyclerView.Adapter<MxrecyclerviewAdapter.myViewHolder>{
   private Goods goods;
    private Context context;
    private ImageLoader imageLoader =ImageLoader.getInstance();

    public MxrecyclerviewAdapter(Goods goods, Context context) {
        this.goods = goods;
        this.context = context;
    }

    @Override
    public MxrecyclerviewAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        String[] split = goods.getData().get(position).getImages().split("\\|");
        holder.textView1.setText(goods.getData().get(position).getTitle());
        holder.textView2.setText(goods.getData().get(position).getTitle());
        imageLoader.displayImage(split[0],holder.imageView);
    }



    @Override
    public int getItemCount() {
        return goods.getData().size();
    }

    public class myViewHolder extends XRecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1,textView2;
        public myViewHolder(View itemView) {
            super(itemView);

             imageView = itemView.findViewById(R.id.iv);
             textView1 =  itemView.findViewById(R.id.tv1);
             textView2 =   itemView.findViewById(R.id.tv2);
        }
    }
}
