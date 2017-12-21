package com.bwei.ssp.home_work.Acticity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.ssp.home_work.R;
import com.bwei.ssp.home_work.bean.Zcbean;
import com.bwei.ssp.home_work.okhttp.OkHttpUtils;
import com.bwei.ssp.home_work.okhttp.OnUiCallback;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;

public class ZhuceActivity extends AppCompatActivity {

    private EditText ed_pone;
    private EditText ed_pass;
    private String path="http://120.27.23.105/user/reg?";
    private EditText ed_pass1;
    private EditText ed_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        ed_pone = (EditText) findViewById(R.id.ed_phone);
        ed_pass = (EditText) findViewById(R.id.ed_pass);
        ed_pass1 = (EditText) findViewById(R.id.ed_pass1);
        ed_email = (EditText) findViewById(R.id.ed_email);
        Button zc = (Button) findViewById(R.id.zc);
        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData() {
        String phone = ed_pone.getText().toString();
        String pass = ed_pass.getText().toString();
        String pass1 = ed_pass1.getText().toString();
        String email = ed_email.getText().toString();
        if (TextUtils.isEmpty(phone)&&TextUtils.isEmpty(pass)&&TextUtils.isEmpty(pass1)&&TextUtils.isEmpty(email)){
            Toast.makeText(ZhuceActivity.this,"账户 密码 邮箱不能为空",Toast.LENGTH_LONG).show();
        return;
        }
        if (pass!=pass1){
            Toast.makeText(ZhuceActivity.this,"两次密码输入不一样",Toast.LENGTH_LONG).show();
            return;
        }
        OkHttpUtils.getInstance().doGet(path+"mobile="+phone+"&&password="+pass, new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSuccess(String result) throws IOException {
              getJs(result);
            }
        });
    }

    private void getJs(String result) {
        Gson gson = new Gson();
        Zcbean bean = gson.fromJson(result, Zcbean.class);
        String data = bean.getData();
        if (data==null){
            Toast.makeText(ZhuceActivity.this,"注册成功",Toast.LENGTH_LONG).show();
            ZhuceActivity.this.finish();
        }else {
            Toast.makeText(ZhuceActivity.this,""+bean.getMsg(),Toast.LENGTH_LONG).show();
        }
    }
}
