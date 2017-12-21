package com.example.wxc.gouwuche.view;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxc.gouwuche.R;
import com.example.wxc.gouwuche.bean.GoodsNewsBean;
import com.example.wxc.gouwuche.precenter.GoodsNewsPrecenter;
import com.nostra13.universalimageloader.core.ImageLoader;

public class GoodNewsActivity extends AppCompatActivity implements IGoodsNewsActivity, View.OnClickListener {

    private ImageView mBackIv;
    private ImageView mImg;
    /**
     * 548945645486asdf
     */
    private TextView mTitle;
    /**
     * 548945645486asdf
     */
    private TextView mOldprice;
    /**
     * 548945645486asdf
     */
    private TextView mNewprice;
    /**
     * 购物车
     */
    private Button mJumpCart;
    /**
     * 加入购物车
     */
    private Button mAddCart;
    private GoodsNewsPrecenter goodsNewsPrecenter;
    private GoodsNewsBean.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_news);
        initView();
        //创建p层对象 获取数据
        goodsNewsPrecenter = new GoodsNewsPrecenter(this);
        goodsNewsPrecenter.getGoodsNews();
    }

    @Override
    public void show(String str) {
        //吐司
        Toast.makeText(GoodNewsActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNews(GoodsNewsBean goodsNewsBean) {
        data = goodsNewsBean.getData();
        String[] split = data.getImages().split("\\|");
        ImageLoader.getInstance().displayImage(split[0],mImg);
        mTitle.setText(data.getTitle());
        mOldprice.setText(data.getPrice()+"");
        mOldprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        mNewprice.setText(data.getBargainPrice()+"");
    }

    private void initView() {
        //初始化组件
        mBackIv = (ImageView) findViewById(R.id.back_iv);
        mImg = (ImageView) findViewById(R.id.img);
        mTitle = (TextView) findViewById(R.id.title);
        mOldprice = (TextView) findViewById(R.id.oldprice);
        mNewprice = (TextView) findViewById(R.id.newprice);
        mJumpCart = (Button) findViewById(R.id.jump_cart);
        mBackIv.setOnClickListener(this);
        mJumpCart.setOnClickListener(this);
        mAddCart = (Button) findViewById(R.id.add_cart);
        mAddCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;

            case R.id.jump_cart:
                //跳转到购物车
                Intent intent = new Intent(GoodNewsActivity.this,ShopCartActivity.class);
                startActivity(intent);

                break;
            case R.id.back_iv:
                //返回  关闭此activity
                this.finish();
                break;
            case R.id.add_cart:
                //点击加入购物车按钮 请求加入购物车接口 传过去pid
                goodsNewsPrecenter.addToCart(data.getPid());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解决内存泄露
        goodsNewsPrecenter.Dettouch();
    }
}
