package com.bwei.ssp.home_work.Fragment.Xiangqing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.ssp.home_work.Fragment.Xiangqing.bean.Add_Bean;
import com.bwei.ssp.home_work.Fragment.Xiangqing.bean.Xiang_Bean;
import com.bwei.ssp.home_work.Fragment.Xiangqing.presenter.Xq_presenter;
import com.bwei.ssp.home_work.Fragment.Xiangqing.view.Xq_view;
import com.bwei.ssp.home_work.Okhttputils.GsonObjectCallback;
import com.bwei.ssp.home_work.Okhttputils.OkHttp3Utils;
import com.bwei.ssp.home_work.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

public class XiangqingActivity extends AppCompatActivity implements Xq_view{

    private ImageView img;
    private TextView title,newprice,price,subhead;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        img = (ImageView) findViewById(R.id.img);
        title = (TextView) findViewById(R.id.title);
        subhead = (TextView) findViewById(R.id.subhead);
        price = (TextView) findViewById(R.id.price);
        newprice = (TextView) findViewById(R.id.newprice);
        add = (Button) findViewById(R.id.add);
        Xq_presenter xq_presenter = new Xq_presenter(this);
        Intent intent = getIntent();
        int pid = intent.getIntExtra("pid", 1);
        xq_presenter.getJS(pid);

    }

    @Override
    public void getData(final Xiang_Bean bean) {
        Xiang_Bean.DataBean data = bean.getData();
        ImageLoaderConfiguration cf = ImageLoaderConfiguration.createDefault(XiangqingActivity.this);
        ImageLoader.getInstance().init(cf);
        if (data.getImages().split("!")[0]!=null){
            ImageLoader.getInstance().displayImage(data.getImages().split("!")[0],img);
        }
        title.setText(data.getTitle());
        subhead.setText(data.getSubhead());
        price.setText(data.getPrice()+"");
        newprice.setText(data.getBargainPrice()+"");
        final int pid = bean.getData().getPid();
        final int sellerid = bean.getSeller().getSellerid();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                HashMap<String, String> addmap = new HashMap<>();
                addmap.put("uid","2856");
                addmap.put("pid",pid+"");
                Log.e("guc", pid+"" );
                OkHttp3Utils.doPost("http://120.27.23.105/product/addCart", addmap, new GsonObjectCallback<Add_Bean >() {
                    @Override
                    public void onUi(Add_Bean add_bean) {
                        Toast.makeText(XiangqingActivity.this,add_bean.getMsg(),Toast.LENGTH_LONG).show();

                        Log.e("guc", add_bean.getMsg() );
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });

                HashMap<String, String> suchmap = new HashMap<>();
                suchmap.put("uid","2856");
                suchmap.put("sellerid",sellerid+"");
                suchmap.put("pid",pid+"");
                suchmap.put("num","10");
                suchmap.put("selected","0");
                 OkHttp3Utils.doPost("http://120.27.23.105/product/updateCarts", suchmap, new GsonObjectCallback<Add_Bean>() {
                     @Override
                     public void onUi(Add_Bean add_bean) {
                         Toast.makeText(XiangqingActivity.this,"刷新购物车",Toast.LENGTH_LONG).show();
                         Log.e("guc", add_bean.getMsg() );
                     }

                     @Override
                     public void onFailed(Call call, IOException e) {

                     }
                 });
            }
        });
    }
}
