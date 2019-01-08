package com.intelligent.marking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.BedInfoModel;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmptyBedAddPatientActivity extends BaseActivity {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.header_title_root)
    RelativeLayout headerTitleRoot;
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
    @BindView(R.id.ll_save_patient)
    LinearLayout llSavePatient;
    @BindView(R.id.ck_mela)
    CheckBox ckMela;
    @BindView(R.id.ck_femela)
    CheckBox ckFemela;

    private BedInfoModel bedInfoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_bed_add_patient);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("床位信息添加");
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.fanhui01);
        llLeftContainer.addView(imageView);
        bedInfoModel = (BedInfoModel) getIntent().getSerializableExtra("bedinfo");
        tvFrom.setText(Html.fromHtml(departName+"<font color='#36D6CC'>"+bedInfoModel.getBed_name()+"</font>号床位"));

        ckFemela.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(ckMela.isChecked()){
                        ckMela.setChecked(false);
                    }
                }
            }
        });
        ckMela.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(ckFemela.isChecked()){
                        ckFemela.setChecked(false);
                    }
                }
            }
        });
    }


    @Override
    public void getCallBack(String response, int flag) {
        Type type;
        switch (flag){
            case 1:
                type = new TypeToken<BaseModel<String>>(){}.getType();
                BaseModel<String> baseModel = new Gson().fromJson(response,type);
                showToast(baseModel.getInfo());
                Intent intent = new Intent(this,MainActivity.class);
                setResult(2,intent);
                finish();
                break;
        }
    }

    @OnClick(R.id.ll_save_patient)
    public void onViewClicked() {

        if (etPatientName.getText().toString().isEmpty()) {
            showToast("请输入病人姓名！");
            return;
        }
        if (etPatientAge.getText().toString().isEmpty()) {
            showToast("请输入病人年龄！");
            return;
        }
        if (ckMela.isChecked()) {
            value.put("sex",1);
        }
        if(ckFemela.isChecked()){
            value.put("sex",2);
        }
        if(!ckFemela.isChecked()&&!ckMela.isChecked()){
            value.put("sex",0);
        }
        value.put("subarea_id",subareaNameid);
        value.put("bed_sn",bedInfoModel.getBed_sn());
        value.put("bed_name",bedInfoModel.getBed_name());
        value.put("name",etPatientName.getText().toString());
        value.put("age",Integer.parseInt(etPatientAge.getText().toString()));
        HttpPost(AppConst.NULLBEDADDPAT,value,1);

    }
}
