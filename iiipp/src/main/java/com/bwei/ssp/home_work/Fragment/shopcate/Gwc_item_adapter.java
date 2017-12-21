package com.bwei.ssp.home_work.Fragment.shopcate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.ssp.home_work.Fragment.Gwc_Bean;
import com.bwei.ssp.home_work.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by lenovo on 2017/12/15.
 */

public class Gwc_item_adapter extends RecyclerView.Adapter<Gwc_item_adapter.MyHoder> {
   Context context;
   List<Gwc_Bean.DataBean.ListBean> list;
    onGetCheckListener checklistener;

    public void setChecklistener(onGetCheckListener checklistener) {
        this.checklistener = checklistener;
    }

    public Gwc_item_adapter(Context context, List<Gwc_Bean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.gwc_item_rlv, null);
        MyHoder myHoder = new MyHoder(view);
        return myHoder;
    }

    @Override
    public void onBindViewHolder(final MyHoder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.js.setText(list.get(position).getSubhead());
        holder.price.setText(list.get(position).getPrice()+"");
        holder.newprice.setText(list.get(position).getBargainPrice()+"");
        ImageLoaderConfiguration cf = ImageLoaderConfiguration.createDefault( context);
        ImageLoader.getInstance().init(cf);
        Log.e("image",list.get(position).getImages().split("!")[0]);
        if (list.get(position).getImages().split("!")[0]!=null){

            ImageLoader.getInstance().displayImage(list.get(position).getImages().split("!")[0],holder.gwc_img);
        }


        holder.che2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = holder.che2.isChecked();
                checklistener.onGeetcheck(holder.che2,checked);
            }
        });

      //  Log.e("check", checked+"" );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
  public  interface onGetCheckListener{
      void onGeetcheck(View v , boolean che);

  }
    public class MyHoder extends RecyclerView.ViewHolder {
        private CheckBox che2;
        private TextView title,js,price,newprice;
        private ImageView gwc_img;
        public MyHoder(View itemView) {
            super(itemView);
            gwc_img = itemView.findViewById(R.id.gwc_img);
            che2=itemView.findViewById(R.id.che2);
           title= itemView.findViewById(R.id.title);
           js= itemView.findViewById(R.id.js);
           price= itemView.findViewById(R.id.price);
          newprice=  itemView.findViewById(R.id.newprice);
        }
    }
}
