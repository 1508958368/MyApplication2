package com.example.wxc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wxc.myapplication.login.bean1.User;
import com.example.wxc.myapplication.login.presenter.Mpresenter;
import com.example.wxc.myapplication.login.view1.Iview;

public class MainActivity extends AppCompatActivity implements Iview {

    private EditText editText1, editText2;
    private Button bt1, bt2;
    private Mpresenter mpersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText1 = (EditText) findViewById(R.id.et1);
        editText2 = (EditText) findViewById(R.id.et2);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);

        mpersenter = new Mpresenter(this);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpersenter.login(new User(editText1.getText().toString(),editText2.getText().toString()));
                Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                startActivity(intent);
            }
        });
         bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });



    }





    @Override
    public void LoginSuccess() {
        Toast.makeText(MainActivity.this,"成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoginFideled() {
        Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_SHORT).show();
    }
}
