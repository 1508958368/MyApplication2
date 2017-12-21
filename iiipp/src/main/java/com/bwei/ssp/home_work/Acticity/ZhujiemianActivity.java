package com.bwei.ssp.home_work.Acticity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bwei.ssp.home_work.Fragment.fenlei;
import com.bwei.ssp.home_work.Fragment.gwc;
import com.bwei.ssp.home_work.Fragment.shouye;
import com.bwei.ssp.home_work.Fragment.wode;
import com.bwei.ssp.home_work.R;

import java.util.ArrayList;
import java.util.List;

public class ZhujiemianActivity extends AppCompatActivity {

    private ViewPager vp;
    private RadioGroup rg;
    private List<Fragment> flist =new ArrayList<>();
    private PMadapter pMadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhujiemian);
        vp = (ViewPager) findViewById(R.id.vp);
        rg = (RadioGroup) findViewById(R.id.rg);
        flist.add(new shouye());
        flist.add(new fenlei());
        flist.add(new gwc());
        flist.add(new wode());
        pMadapter = new PMadapter(getSupportFragmentManager());
         vp.setAdapter(pMadapter);
        ImageView b1 = (ImageView) findViewById(R.id.b1);
         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(ZhujiemianActivity.this,"点击了", Toast.LENGTH_LONG);

             }
         });
//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup,int checkedId) {
//                switch (checkedId){
//                    case  R.id.b1:
//                        Toast.makeText(ZhujiemianActivity.this,"点击了",Toast.LENGTH_LONG);
//                        vp.setCurrentItem(0);
//                        break;
//                    case  R.id.b2:
//                        vp.setCurrentItem(1);
//                        break;
//                    case  R.id.b3:
//                        vp.setCurrentItem(2);
//                        break;
//                    case  R.id.b4:
//                        vp.setCurrentItem(3);
//                        break;
//                }
//
//            }
//        });
    }

    private class PMadapter   extends FragmentPagerAdapter {
        public PMadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return flist.get(position);
        }

        @Override
        public int getCount() {
            return flist.size();
        }
    }
}
