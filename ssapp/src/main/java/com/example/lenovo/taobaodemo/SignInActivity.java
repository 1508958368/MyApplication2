package com.example.lenovo.taobaodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.lenovo.taobaodemo.register.presenter.Passer;
import com.example.lenovo.taobaodemo.register.registerbean.Registrant;
import com.example.lenovo.taobaodemo.register.view.RView;

import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity implements RView {
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private ImageView back;
    private EditText name;
    private EditText pwd;
    private EditText checkingpwd;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //初始化组件
        initView();
    }

    //初始化组件
    private void initView() {
        back = (ImageView) this.findViewById(R.id.back);
        name = (EditText) findViewById(R.id.name);
        pwd = (EditText) findViewById(R.id.pwd);
        checkingpwd = (EditText) findViewById(R.id.checkingpwd);
        email = (EditText) findViewById(R.id.email);
        //控件监听
        initListening();
    }

    //控件监听
    private void initListening() {
        //返回按钮
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //注册按钮
    public void register(View v) {
        //判断输入项是否为空
        if (TextUtils.isEmpty(name.getText().toString())
                || TextUtils.isEmpty(pwd.getText().toString())
                || TextUtils.isEmpty(checkingpwd.getText().toString())
                || TextUtils.isEmpty(email.getText().toString())) {
            Toast.makeText(this, "输入项不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pwd.getText().toString().equals(checkingpwd.getText().toString())) {
            Toast.makeText(this, "密码两次输入不一致，请确认密码", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isemail = isEmail(email);
        if (!isemail) {
            Toast.makeText(this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        new Passer(this).register(new Registrant(name.getText().toString(), checkingpwd.getText().toString()));

    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(EditText email) {
        return Pattern.matches(REGEX_EMAIL, email.getText().toString());
    }


    //注册成功
    @Override
    public void onSucceed(String msg) {
        Toast.makeText(this, msg + ",欢迎使用", Toast.LENGTH_LONG).show();

        //注册成功跳转登入页面
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(intent);
        finish();


    }


    //注册失败
    @Override
    public void onFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
