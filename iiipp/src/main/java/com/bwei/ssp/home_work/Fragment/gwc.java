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
import android.widget.Button;

import com.bwei.ssp.home_work.Fragment.shopcate.Guc_adapter;
import com.bwei.ssp.home_work.Okhttputils.GsonObjectCallback;
import com.bwei.ssp.home_work.Okhttputils.OkHttp3Utils;
import com.bwei.ssp.home_work.R;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by lenovo on 2017/12/6.
 */

public class gwc extends Fragment {

    private RecyclerView guc_rlv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.gwc, null);
        guc_rlv = v.findViewById(R.id.guc_rlv);
        Button bt = v.findViewById(R.id.but);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttp3Utils.doGet("http://120.27.23.105/product/getCarts?uid=2856", new GsonObjectCallback<Gwc_Bean>() {
                    @Override
                    public void onUi(Gwc_Bean gwc_bean) {
                        Log.e("**", gwc_bean.getMsg());
                        guc_rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
                        Guc_adapter guc_adapter = new Guc_adapter(getContext(), gwc_bean.getData());
                        guc_rlv.setAdapter(guc_adapter);
                        guc_adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });
            }
        });


        return v;
    }
}
