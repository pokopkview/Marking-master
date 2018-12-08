package com.intelligent.marking.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubAreaEmployeeActivity extends BaseActivity {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.rlv_employee)
    RecyclerView rlvEmployee;
    @BindView(R.id.iv_add_employee)
    ImageView ivAddEmployee;
    @BindView(R.id.rl_add_employee)
    RelativeLayout rlAddEmployee;


    @OnClick(R.id.rl_add_employee)
    public void addEmployee(View view){
        //TODO

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_area_employee);
        ButterKnife.bind(this);
        
    }


    @Override
    public void getCallBack(String response, int flag) {

    }
}
