package com.bwei.ssp.home_work.Fragment.Shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.ssp.home_work.Fragment.Shop.bean.Shop_Bean;
import com.bwei.ssp.home_work.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by lenovo on 2017/12/13.
 */

public class Shop_Adapter extends RecyclerView.Adapter<Shop_Adapter.Mhoder> {

    Context context;
    List<Shop_Bean.DataBean>list;

    public Shop_Adapter(Context context, List<Shop_Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    OnItemClickLisenter lisenter;

    public void setLisenter(OnItemClickLisenter lisenter) {
        this.lisenter = lisenter;
    }

    public interface OnItemClickLisenter{
        void onitenmCklick(View view,int position);
    }

    @Override
    public Mhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shop_item, null);
        final Mhoder mhoder = new Mhoder(view);


        return mhoder;
    }

    @Override
    public void onBindViewHolder(Mhoder holder, final int position) {
         holder.title.setText(list.get(position).getTitle());
         holder.js.setText(list.get(position).getSubhead());
        holder.price.setText(list.get(position).getPrice()+"");
        holder.newprice.setText(list.get(position).getBargainPrice()+"");
        ImageLoader.getInstance().displayImage(list.get(position).getImages().split("!")[0],holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lisenter.onitenmCklick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Mhoder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title,js,price,newprice;
        public Mhoder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
            js=itemView.findViewById(R.id.js);
            price=itemView.findViewById(R.id.price);
            newprice=itemView.findViewById(R.id.newprice);
        }
    }


}
