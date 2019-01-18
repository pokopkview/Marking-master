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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.adapter.BedInfoPageAdapter;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.BedInfoModel;
import com.intelligent.marking.net.model.BedStatusModel;
import com.intelligent.marking.net.model.TreatInfoModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    
    
    int count = 0;
    List<BedStatusModel> beddisposeList;
    List<BedStatusModel> bedonwayList;
    List<BedStatusModel> bedcompeleteList;


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
//        tabLayout.addTab(tabLayout.newTab().setText("需处理"));
//        tabLayout.addTab(tabLayout.newTab().setText("进行中"));
//        tabLayout.addTab(tabLayout.newTab().setText("已完成"));
        int subareaid = getIntent().getIntExtra("subarea_id",-1);
        value.clear();
        value.put("subarea_id",subareaid);
        value.put("course_id",1);
        HttpPost(AppConst.ICUDISPOSE,value,0);
    }

    @Override
    public void getCallBack(String response, int flag) {
        Type type;
        switch (flag){
            case -1:
//                type = new TypeToken<BaseModel<List<BedInfoModel>>>(){}.getType();
//                BaseModel<List<BedInfoModel>> baseModel = new Gson().fromJson(response,type);
//                bedInfoModelList = baseModel.getData();
//                for (int i = 0;i<bedInfoModelList.size();i++){
//                    value.clear();
//                    value.put("subarea_id",1);
//                    value.put("course_id",1);
//                    HttpPost(AppConst.TREATINFO,value,i);
//                }
                break;
            case 0:
                type = new TypeToken<BaseModel<List<BedStatusModel>>>(){}.getType();
                BaseModel<List<BedStatusModel>> listBaseModel = new Gson().fromJson(response,type);
                beddisposeList = listBaseModel.getData();
//                beddisposeList.add(listBaseModel.getData().get(0));
                HttpPost(AppConst.ICUONWAY,value,1);
                break;
            case 1:
                type = new TypeToken<BaseModel<List<BedStatusModel>>>(){}.getType();
                BaseModel<List<BedStatusModel>> listBaseModel1 = new Gson().fromJson(response,type);
                bedonwayList = listBaseModel1.getData();
                HttpPost(AppConst.ICUCOMPELETE,value,2);
                break;
            case 2:
                type = new TypeToken<BaseModel<List<BedStatusModel>>>(){}.getType();
                BaseModel<List<BedStatusModel>> listBaseModel2 = new Gson().fromJson(response,type);
                bedcompeleteList = listBaseModel2.getData();
                initRecy();
                break;

        }
    }

    private void initRecy() {
        System.out.println("initRecy");
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
        BedInfoPageAdapter pageAdapter = new BedInfoPageAdapter(this,beddisposeList,bedonwayList,bedcompeleteList);
        viewPager.setAdapter(pageAdapter);
    }

}
