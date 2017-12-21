package com.bwie.test.demo2;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.test.demo2.bean.DetailsBean;
import com.bwie.test.demo2.presenter.AddCartPresenter;
import com.bwie.test.demo2.presenter.DetailsPresenter;
import com.bwie.test.demo2.view.IMainListener;

public class MainActivity extends AppCompatActivity implements IMainListener, View.OnClickListener {

    private DetailsPresenter detailsPresenter;
    private ImageView mIv;
    private TextView mTvBargainPrice;
    private TextView mTvPrice;
    /**
     * 购物车
     */
    private TextView mTvCart;
    /**
     * 加入购物车
     */
    private TextView mTvAddCart;
    private AddCartPresenter addCartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        detailsPresenter = new DetailsPresenter(this);
        addCartPresenter = new AddCartPresenter(this);
        detailsPresenter.getProductDetail();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailsPresenter.dettach();
        addCartPresenter.dettach();
    }

    @Override
    public void show(DetailsBean detailsBean) {
        String images = detailsBean.getData().getImages();
        String[] split = images.split("\\|");
        Glide.with(this).load(split[0]).into(mIv);
        TextPaint paint = mTvBargainPrice.getPaint();
        paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mTvBargainPrice.setText("原价：" + detailsBean.getData().getBargainPrice());
        mTvPrice.setText("优惠价：" + detailsBean.getData().getPrice());
    }

    @Override
    public void show(String str) {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        mTvBargainPrice = (TextView) findViewById(R.id.tvBargainPrice);
        mTvPrice = (TextView) findViewById(R.id.tvPrice);
        mTvCart = (TextView) findViewById(R.id.tvCart);
        mTvAddCart = (TextView) findViewById(R.id.tvAddCart);
        mTvCart.setOnClickListener(this);
        mTvAddCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tvCart:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.tvAddCart:
                addCartPresenter.addCart();
                break;
        }
    }
}
