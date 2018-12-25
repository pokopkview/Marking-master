package com.intelligent.marking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScanPatientInfoActivity extends BaseActivity {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.patient_title)
    TextView patientTitle;
    @BindView(R.id.tv_bed_no)
    TextView tvBedNo;
    @BindView(R.id.tv_patient_name)
    TextView tvPatientName;
    @BindView(R.id.tv_patient_sex)
    TextView tvPatientSex;
    @BindView(R.id.tv_patient_age)
    TextView tvPatientAge;
    @BindView(R.id.et_patient_no)
    EditText etPatientNo;
    @BindView(R.id.header_title_root)
    RelativeLayout headerTitleRoot;
    @BindView(R.id.login_tv01)
    TextView loginTv01;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_patient_info);
        ButterKnife.bind(this);
        String json = getIntent().getStringExtra("patientinfo");

    }

    @Override
    public void getCallBack(String response, int flag) {

    }

    @OnClick(R.id.rl_login)
    public void onViewClicked() {
        Intent intent = new Intent(ScanPatientInfoActivity.this,MainActivity.class);

        intent.putExtra("bedno",etPatientNo.getText().toString());
        setResult(1,intent);
        finish();
    }
}
