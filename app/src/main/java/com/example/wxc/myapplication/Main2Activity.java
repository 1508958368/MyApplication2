package com.example.wxc.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wxc.myapplication.login.bean1.User;
import com.example.wxc.myapplication.login.presenter.Mpresenter;
import com.example.wxc.myapplication.login.view1.Iview;

public class Main2Activity extends AppCompatActivity implements Iview {

    private EditText editText1, editText2;
    private Button bt2;
    private Mpresenter mpersenter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText1 = (EditText) findViewById(R.id.et1);
        editText2 = (EditText) findViewById(R.id.et2);
        bt2 = (Button) findViewById(R.id.bt2);

        mpersenter1 = new Mpresenter(this);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpersenter1.login(new User(editText1.getText().toString(),editText2.getText().toString()));
                finish();
            }
        });


    }



    @Override
    public void LoginSuccess() {
        Toast.makeText(Main2Activity.this, "成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoginFideled() {
        Toast.makeText(Main2Activity.this, "失败", Toast.LENGTH_SHORT).show();
    }
}
