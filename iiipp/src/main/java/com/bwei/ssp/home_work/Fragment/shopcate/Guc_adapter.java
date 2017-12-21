package com.bwei.ssp.home_work.Fragment.shopcate;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwei.ssp.home_work.Fragment.Gwc_Bean;
import com.bwei.ssp.home_work.R;

import java.util.List;

/**
 * Created by lenovo on 2017/12/15.
 */



public class Guc_adapter extends RecyclerView.Adapter<Guc_adapter.Myhoder> {
    Context context;
    List<Gwc_Bean.DataBean> list;

    public Guc_adapter(Context context, List<Gwc_Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Guc_adapter.Myhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.gwc_rlv_item, null);
        Myhoder myhoder = new Myhoder(view);
        return myhoder;
    }

    @Override
    public void onBindViewHolder(final Guc_adapter.Myhoder holder, int position) {
        holder.tv.setText(list.get(position).getSellerName());
        holder.guc_iyem_rlv.setLayoutManager(new LinearLayoutManager(context));
        Gwc_item_adapter gwc_item_adapter = new Gwc_item_adapter(context, list.get(position).getList());
        holder.guc_iyem_rlv.setAdapter(gwc_item_adapter);
        gwc_item_adapter.setChecklistener(new Gwc_item_adapter.onGetCheckListener() {
            @Override
            public void onGeetcheck(View v, boolean che) {

                Log.e("check", che+"" );

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myhoder extends RecyclerView.ViewHolder{
        private TextView tv;
        private CheckBox che1;
        private  RecyclerView guc_iyem_rlv;
        public Myhoder(View itemView) {
            super(itemView);

           che1= itemView.findViewById(R.id.che1);
           tv= itemView.findViewById(R.id.tv);
           guc_iyem_rlv= itemView.findViewById(R.id.guc_item_rlv);
        }

    }
}
