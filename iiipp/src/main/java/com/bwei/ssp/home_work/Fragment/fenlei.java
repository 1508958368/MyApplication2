package com.bwei.ssp.home_work.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwei.ssp.home_work.Fragment.Classification.adapter.Myadapter;
import com.bwei.ssp.home_work.Fragment.Classification.adapter.OnMyadapter;
import com.bwei.ssp.home_work.Fragment.Classification.bean.CommodityBean;
import com.bwei.ssp.home_work.Fragment.Classification.bean.Son_Bean;
import com.bwei.ssp.home_work.Fragment.Classification.fellei_View.Fenlei_View;
import com.bwei.ssp.home_work.Fragment.Classification.fenlei_presenter.Fenlei_pre;
import com.bwei.ssp.home_work.R;

/**
 * Created by lenovo on 2017/12/6.
 */

public class fenlei extends Fragment implements Fenlei_View{
    private RecyclerView left_recview,rlv;
    private Fenlei_pre fenlei_pre;
    private int cid;
    private View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = View.inflate(getActivity(), R.layout.fenlei, null);
        left_recview = v.findViewById(R.id.left_recyle);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        left_recview.setLayoutManager(manager);
        fenlei_pre = new Fenlei_pre(this);
        fenlei_pre.getJs();

        return v;
    }

    @Override
    public void getConnBean(final CommodityBean bean) {
        Myadapter myadapter = new Myadapter(getContext(), bean.getData());
        left_recview.setAdapter(myadapter);
        myadapter.setListener(new Myadapter.OnItemClickListener() {
            @Override
            public void onItemclick(View view, int position) {
                int ishome = bean.getData().get(position).getIshome();
                if(ishome==1)
                {
                    cid = bean.getData().get(position).getCid();
                    fenlei_pre.getSonJs(cid);
                    Log.e("cid", "cid= "+cid );


                }else {
                   // Toast.makeText(getContext(),"无法加载九宫格",Toast.LENGTH_LONG).show();
                }
              //  Toast.makeText(getContext(),"点击了"+ishome,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void getSonBean(final Son_Bean bean) {
        if (bean.getData()!=null) {
            rlv = v.findViewById(R.id.left_rlv);
            rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
            OnMyadapter onMyadapter = new OnMyadapter(getContext(), bean);
            rlv.setAdapter(onMyadapter);
        }else {
            Toast.makeText(getContext(),"没数据",Toast.LENGTH_LONG).show();
        }
    }
}
