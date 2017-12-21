package com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.taobaodemo.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * author:Created by WangZhiQiang on 2017/12/13.
 */

public class PageAdapter extends XRecyclerView.Adapter<PageAdapter.MVH> {


    private Context context;

    public PageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        MVH mvh = new MVH(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MVH extends XRecyclerView.ViewHolder {

        public MVH(View itemView) {
            super(itemView);
        }
    }

}
