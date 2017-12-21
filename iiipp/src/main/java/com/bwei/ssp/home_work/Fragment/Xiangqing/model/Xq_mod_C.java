package com.bwei.ssp.home_work.Fragment.Xiangqing.model;

import com.bwei.ssp.home_work.Fragment.Xiangqing.bean.Xiang_Bean;
import com.bwei.ssp.home_work.Okhttputils.GsonObjectCallback;
import com.bwei.ssp.home_work.Okhttputils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

/**
 * Created by lenovo on 2017/12/14.
 */

public class Xq_mod_C implements Xq_model {
    OnGetXiangListener listener;
    public interface OnGetXiangListener{
        void onGetXq(Xiang_Bean bean);
    }

    public void setListener(OnGetXiangListener listener) {
        this.listener = listener;
    }

    @Override
    public void setData(int pid) {
        if (pid>-1) {
            HashMap<String, String> map = new HashMap<>();
            map.put("pid", pid + "");
            OkHttp3Utils.doPost("http://120.27.23.105/product/getProductDetail", map, new GsonObjectCallback<Xiang_Bean>() {
                @Override
                public void onUi(Xiang_Bean bean) {
                    if (bean.getData()!=null){

                        if (listener!=null){
                            listener.onGetXq(bean);
                        }
                    }

                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });
        }
    }

}
