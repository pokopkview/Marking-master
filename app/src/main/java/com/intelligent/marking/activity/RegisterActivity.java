package com.intelligent.marking.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.R;
import com.intelligent.marking.set.JsonDataActivity01;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    String JsonData;
    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.login_tv_position)
    TextView loginTvPosition;
    @BindView(R.id.ll_select_location)
    LinearLayout llSelectLocation;
    @BindView(R.id.v_line1)
    View vLine1;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.ll_select_hospital)
    LinearLayout llSelectHospital;
    @BindView(R.id.v_line2)
    View vLine2;
    @BindView(R.id.et_area)
    EditText etArea;
    @BindView(R.id.ll_select_area)
    LinearLayout llSelectArea;
    @BindView(R.id.v_line3)
    View vLine3;
    @BindView(R.id.et_department)
    EditText etDepartment;
    @BindView(R.id.ll_select_depart)
    LinearLayout llSelectDepart;
    @BindView(R.id.v_line4)
    View vLine4;
    @BindView(R.id.et_subarea)
    EditText etSubarea;
    @BindView(R.id.ll_select_subarea)
    LinearLayout llSelectSubarea;
    @BindView(R.id.v_line5)
    View vLine5;
    @BindView(R.id.et_bed)
    EditText etBed;
    @BindView(R.id.ll_select_bed)
    LinearLayout llSelectBed;
    @BindView(R.id.v_line6)
    View vLine6;
    @BindView(R.id.first_pwd)
    EditText firstPwd;
    @BindView(R.id.ll_pwd)
    LinearLayout llPwd;
    @BindView(R.id.v_line7)
    View vLine7;
    @BindView(R.id.confirm_pwd)
    EditText confirmPwd;
    @BindView(R.id.ll_pwd_retry)
    LinearLayout llPwdRetry;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.ll_confirm)
    LinearLayout llConfirm;

    @OnClick(R.id.ll_left_container)
    public void back(View view){
        finish();
    }

    @OnClick(R.id.ll_select_location)
    public void setLcation(View view){
        JsonData  = new JsonDataActivity01().getPosition(RegisterActivity.this, loginTvPosition, new JsonDataActivity01.selectPosition() {
            @Override
            public void getLocation(String pronvince, String city, String area) {
                //TODO 完成选择后的返回
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        ImageView leftIcon = new ImageView(this);
        leftIcon.setBackgroundResource(R.mipmap.fanhui01);
        llLeftContainer.addView(leftIcon);
        tvHeaderTitle.setText("注册");
    }

    @Override
    public void getCallBack(String response, int flag) {

    }
}
