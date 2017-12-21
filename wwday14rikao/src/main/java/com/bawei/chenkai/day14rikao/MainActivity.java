package com.bawei.chenkai.day14rikao;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.chenkai.day14rikao.xiangqing.bean.SecondBean;
import com.bawei.chenkai.day14rikao.xiangqing.bean.presenter.SecondPresenter;
import com.bawei.chenkai.day14rikao.xiangqing.bean.view.SecondViewListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements SecondViewListener.PresenterSecondInterface {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.yuanJia)
    TextView yuanJia;
    @Bind(R.id.youHui)
    TextView youHui;
    @Bind(R.id.backImage)
    ImageView backImage;
    @Bind(R.id.ProductImage)
    ImageView ProductImage;
    private SecondPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //关联p层,获取数据
        presenter = new SecondPresenter(this);
        presenter.getData();
    }

    @OnClick({R.id.backImage, R.id.goToCart, R.id.addCart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backImage:        //点击左上角返回按钮跳转至属性动画页面
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intent);
                break;

            case R.id.addCart:      //点击加入购物车的方法
                //路径
                String path = "https://www.zhaoapi.cn/product/addCart?uid=71&pid=1";
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(path)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String body = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //吐司加入购物车成功
                                Toast.makeText(MainActivity.this, "购物车加入商品成功"+body, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                break;

            case R.id.goToCart:     //点击  购物车  按钮跳转至购物车列表页面
                Intent intent1 = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void success(SecondBean bean) {
        //设置图片 下标为0的图片数据

        String images1 = bean.getData().getImages();
        String[] split = images1.split("\\|");
        //ImageLoader加载图片数组中的图片
        ImageLoader.getInstance().displayImage(split[0],ProductImage);

        //设置商品信息显示
        title.setText(bean.getData().getTitle());
        yuanJia.setText("原价:￥" + bean.getData().getPrice());
        //设置原价中间横线（删除线）
        yuanJia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        youHui.setText("优惠价:" + bean.getData().getBargainPrice());
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(MainActivity.this,"数据出错",Toast.LENGTH_SHORT).show();
    }

    //为防止内存泄漏，在view层销毁p层
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
