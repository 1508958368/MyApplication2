package com.example.wxc.xiangmu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wxc.xiangmu.reg.bean2.User1;
import com.example.wxc.xiangmu.reg.presenter2.Mpresenter2;
import com.example.wxc.xiangmu.reg.view2.Iview1;

import static com.example.wxc.xiangmu.R.id.pass;

public class RegActivity extends AppCompatActivity implements View.OnClickListener,Iview1{

    /**
     * 请输入手机号
     */
    private EditText mNum;
    /**
     * 请输入密码
     */
    private EditText mPass;
    /**
     * 请确认输入密码
     */
    private EditText mPass2;
    /**
     * 请输入邮箱
     */
    private EditText mEmail;
    /**
     * 注册
     */
    private Button mZhuce;
    private Mpresenter2 mpresenter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
        mpresenter2 = new Mpresenter2(this);
        mZhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass3 = mPass.getText().toString().trim();
                String pass4 = mPass2.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                    if (pass3.equals(pass4)&&pass4.length()>=6) {
                        mpresenter2.Reg(new User1(mNum.getText().toString(),mPass.getText().toString()));
                        Intent intent = new Intent(RegActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(RegActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();

                    }
                if (email.equals("@")) {
                    Toast.makeText(RegActivity.this,"邮箱格式不正确",Toast.LENGTH_SHORT).show();
                }else {
                    mpresenter2.Reg(new User1(mNum.getText().toString(),mPass.getText().toString()));
                    finish();;
                }
            }
        });
    }

    private void initView() {
        mNum = (EditText) findViewById(R.id.num);
        mPass = (EditText) findViewById(pass);
        mPass2 = (EditText) findViewById(R.id.pass2);
        mEmail = (EditText) findViewById(R.id.email);
        mZhuce = (Button) findViewById(R.id.zhuce);
        mZhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.zhuce:

                break;
        }
    }

    @Override
    public void RegSuccess() {
        Toast.makeText(RegActivity.this,"成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ReginFideled() {
        Toast.makeText(RegActivity.this,"失败",Toast.LENGTH_SHORT).show();
    }
}
