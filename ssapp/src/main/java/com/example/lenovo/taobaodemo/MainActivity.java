package com.example.lenovo.taobaodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.taobaodemo.login.loginbean.User;
import com.example.lenovo.taobaodemo.login.presenter.Passer;
import com.example.lenovo.taobaodemo.login.view.Iview;

public class MainActivity extends AppCompatActivity implements Iview {

    private TextView sign_in;
    private ImageView back;
    private EditText name;
    private EditText pwd;
    private ImageView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish();

    }

    private void initView() {

        //初始化输入框
        name = (EditText) findViewById(R.id.name);
        pwd = (EditText) findViewById(R.id.pwd);

        //登入
        login = (ImageView) findViewById(R.id.login);

        //新用户注册
        sign_in = (TextView) this.findViewById(R.id.sign_in);

        //新用户注册
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signinintent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(signinintent);
                finish();

            }
        });

        //关闭程序
        back = (ImageView) this.findViewById(R.id.back_app);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(pwd.getText().toString())) {
                    Toast.makeText(MainActivity.this, "输入项不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User(name.getText().toString(), pwd.getText().toString());
                Passer passer = new Passer(MainActivity.this);
                passer.login(user);

            }
        });


    }


    //登入失败
    @Override
    public void onFailed(String codestate) {

        Toast.makeText(MainActivity.this, codestate, Toast.LENGTH_SHORT).show();
    }

    //登入成功
    @Override
    public void onSucceed(String codestate) {
        Toast.makeText(MainActivity.this, codestate + ",欢迎使用☺", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }
}
