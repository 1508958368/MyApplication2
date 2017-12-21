package com.bwie.wangxinrun1510b20171211;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.wangxinrun1510b20171211.reg.bean2.User1;
import com.bwie.wangxinrun1510b20171211.reg.persenter1.Mpersenter1;
import com.bwie.wangxinrun1510b20171211.reg.view.Iview1;

public class Main2Activity extends AppCompatActivity implements Iview1 {

    private EditText editText1, editText2;
    private Button bt2;
    private Mpersenter1 mpersenter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText1 = (EditText) findViewById(R.id.et1);
        editText2 = (EditText) findViewById(R.id.et2);
        bt2 = (Button) findViewById(R.id.bt2);

        mpersenter1 = new Mpersenter1(this);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpersenter1.login1(new User1(editText1.getText().toString(),editText2.getText().toString()));
                finish();
            }
        });


    }

    @Override
    public void RegSuccess() {
        Toast.makeText(Main2Activity.this, "成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ReginFideled() {
        Toast.makeText(Main2Activity.this, "失败", Toast.LENGTH_SHORT).show();
    }
}
