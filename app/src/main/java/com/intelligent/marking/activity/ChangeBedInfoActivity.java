package com.intelligent.marking.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeBedInfoActivity extends BaseActivity {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.tv_from)
    TextView tvFrom;
    @BindView(R.id.et_patient_name)
    EditText etPatientName;
    @BindView(R.id.ll_patient_name)
    LinearLayout llPatientName;
    @BindView(R.id.ll_patient_sex)
    LinearLayout llPatientSex;
    @BindView(R.id.et_patient_age)
    EditText etPatientAge;
    @BindView(R.id.ll_patient_age)
    LinearLayout llPatientAge;
    @BindView(R.id.et_patient_no)
    EditText etPatientNo;
    @BindView(R.id.ll_patient_no)
    LinearLayout llPatientNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bed_info);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("床位信息更改");
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.fanhui01);
        llLeftContainer.addView(imageView);
    }

    @Override
    public void getCallBack(String response, int flag) {

    }
}
