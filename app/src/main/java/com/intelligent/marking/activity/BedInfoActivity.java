package com.intelligent.marking.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BedInfoActivity extends BaseActivity {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_info);
        ButterKnife.bind(this);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.fanhui01);
        llLeftContainer.addView(imageView);
        ImageView rightimg = new ImageView(this);
        rightimg.setImageResource(R.mipmap.scan_white);
        llRightContainer.addView(rightimg);
        tvHeaderTitle.setText(hospitalName+subareaName);
        tabLayout.addTab(tabLayout.newTab().setText("需处理"));
        tabLayout.addTab(tabLayout.newTab().setText("进行中"));
        tabLayout.addTab(tabLayout.newTab().setText("已完成"));





        int subareaid = getIntent().getIntExtra("subareaid",-1);
        Map<String,Object> value = new HashMap<>();
        value.put("subarea_id",subareaid);
        HttpPost(AppConst.GETBEDINFO,value,1);

        //TODO
    }

    @Override
    public void getCallBack(String response, int flag) {
        switch (flag){
            case 1:







                break;


        }
    }
}
