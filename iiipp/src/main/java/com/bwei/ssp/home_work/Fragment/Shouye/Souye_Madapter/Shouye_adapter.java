package com.bwei.ssp.home_work.Fragment.Shouye.Souye_Madapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.ssp.home_work.Fragment.Shouye.bean.Grild_Bean;
import com.bwei.ssp.home_work.R;

import java.util.List;

/**
 * Created by lenovo on 2017/12/12.
 */

public class Shouye_adapter extends RecyclerView.Adapter<Shouye_adapter.Myhoder> {
    Context context;
    List<Grild_Bean> list;

    public Shouye_adapter(Context context, List<Grild_Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Myhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.shouye_item, null);
        Myhoder myhoder = new Myhoder(inflate);
        return myhoder;
    }

    @Override
    public void onBindViewHolder(Myhoder holder, int position) {
        holder.tv.setText(list.get(position).getName());
        holder.img.setBackgroundResource(list.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class Myhoder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tv;
        public Myhoder(View itemView) {
            super(itemView);
            img =itemView.findViewById(R.id.img);
            tv =itemView.findViewById(R.id.tv);
        }
    }
}
