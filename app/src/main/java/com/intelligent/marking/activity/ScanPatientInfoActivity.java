package com.intelligent.marking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
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
import com.intelligent.marking.net.model.BedStatusModel;
import com.intelligent.marking.net.model.ScanPatientInfoModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hugo.weaving.DebugLog;
import okhttp3.Call;

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

    ScanPatientInfoModel bedStatusModel;
    List<BedInfoModel> bedInfoModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_patient_info);
        ButterKnife.bind(this);
        String json = getIntent().getStringExtra("patientinfo");
        bedInfoModelList = (List<BedInfoModel>) getIntent().getSerializableExtra("bedinfo");
        OkHttpUtils.get().url(json).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                showToast(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Type type = new TypeToken<BaseModel<ScanPatientInfoModel>>(){}.getType();
                BaseModel<ScanPatientInfoModel> bedStatusModelBaseModel = new Gson().fromJson(response,type);
                bedStatusModel = bedStatusModelBaseModel.getData();
                initView();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void getCallBack(String response, int flag) {
        Type type;
        switch (flag){
            case 1:
                type = new TypeToken<BaseModel<String>>(){}.getType();
                BaseModel<String> baseModel = new Gson().fromJson(response,type);
                showToast(baseModel.getInfo());
                Intent intent = new Intent(ScanPatientInfoActivity.this,MainActivity.class);
                intent.putExtra("bedno",etPatientNo.getText().toString());
                intent.putExtra("patientinfo",(Serializable) bedStatusModel);
                setResult(1,intent);
                finish();
                break;
        }
    }

    private void initView(){
        tvBedNo.setText("床号："+bedStatusModel.getBed_name());
        tvPatientName.setText("姓名："+bedStatusModel.getName());
        tvPatientSex.setText("性别："+this.getResources().getStringArray(R.array.sex)[bedStatusModel.getSex()]);
        tvPatientAge.setText("年龄："+bedStatusModel.getAge());
    }

    @DebugLog
    @OnClick(R.id.rl_login)
    public void onViewClicked() {
        String bedno = etPatientNo.getText().toString();
        String curseid = "";
        for(BedInfoModel bedInfoModel : bedInfoModelList){
            if(bedInfoModel.getBed_name().equals(bedno)){
                curseid = bedInfoModel.getBed_name();
            }
        }
        value.clear();
        value.put("subarea_id",subareaNameid);
        value.put("bed_name",curseid);
        value.put("course_id",bedStatusModel.getCourse_id());
        HttpPost(AppConst.ADDPATIENT,value,1);
    }
}
