package com.bwei.ssp.home_work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.ssp.home_work.Acticity.ZhuceActivity;
import com.bwei.ssp.home_work.Acticity.ZhujiemianActivity;
import com.bwei.ssp.home_work.Login.presenter.Presenter;
import com.bwei.ssp.home_work.Login.view.Myview;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Myview{
    private EditText ed_pone;
    private EditText ed_pass;
    private TextView zc;
    private Button login;

    private Button qq;
    private Tencent mTencent;
    private String APP_ID = "222222";
    private IUiListener loginListener;
    private String SCOPE = "all";


    private IUiListener userInfoListener;
    private  String TAG;
    private UserInfo mUserInfo;
    private ImageView qqq;
    private Presenter p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_pone = (EditText) findViewById(R.id.ed_phone);
        ed_pass = (EditText) findViewById(R.id.ed_pass);
        zc = (TextView) findViewById(R.id.zc);
        p = new Presenter(this);
        login = (Button) findViewById(R.id.login);
        qqq = (ImageView) findViewById(R.id.qq);
        qqq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initQqLogin();
                mTencent.login(MainActivity.this, SCOPE, loginListener);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.getData(new com.bwei.ssp.home_work.Login.bean.Bean(ed_pone.getText().toString(),ed_pass.getText().toString()));
            }
        });
        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZhuceActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initQqLogin() {
        mTencent = Tencent.createInstance(APP_ID, this);
        loginListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                //登录成功后回调该方法
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "response:" + o);
                JSONObject obj = (JSONObject) o;
                try {
                    String openID = obj.getString("openid");
                    String accessToken = obj.getString("access_token");
                    String expires = obj.getString("expires_in");
                    mTencent.setOpenId(openID);
                    mTencent.setAccessToken(accessToken,expires);
                    QQToken qqToken = mTencent.getQQToken();
                    mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                    mUserInfo.getUserInfo(new IUiListener() {
                        @Override
                        public void onComplete(Object response) {
                            Log.e(TAG,"登录成功"+response.toString());
                            Intent intent = new Intent(MainActivity.this, ZhujiemianActivity.class);
                            intent.putExtra("json",response.toString());
                            startActivity(intent);
                        }

                        @Override
                        public void onError(UiError uiError) {
                            Log.e(TAG,"登录失败"+uiError.toString());
                        }

                        @Override
                        public void onCancel() {
                            Log.e(TAG,"登录取消");

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
                //登录失败后回调该方法
                Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                Log.e("LoginError:", uiError.toString());
            }

            @Override
            public void onCancel() {
                //取消登录后回调该方法
                Toast.makeText(MainActivity.this, "取消登录", Toast.LENGTH_SHORT).show();
            }
        };

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_LOGIN) {
            if (resultCode == -1) {
//                Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
//                Tencent.handleResultData(data, loginListener);

                Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
                Tencent.handleResultData(data, loginListener);
                UserInfo info = new UserInfo(this, mTencent.getQQToken());
                info.getUserInfo(userInfoListener);
            }
        }
    }

    @Override
    public void getYes() {
        Toast.makeText(MainActivity.this,"成功",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, ZhujiemianActivity.class);
        startActivity(intent);
    }

    @Override
    public void getNo() {
        Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_LONG).show();
    }
}
