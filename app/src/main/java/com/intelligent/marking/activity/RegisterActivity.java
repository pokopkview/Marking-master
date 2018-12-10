package com.intelligent.marking.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.adapter.RegisterPagePopAdapter;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.HospitalModel;
import com.intelligent.marking.set.JsonDataActivity01;
import com.intelligent.marking.widget.PopUpwindowUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    JsonDataActivity01 JsonData;
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
    @BindView(R.id.header_title_root)
    RelativeLayout headerTitleRoot;
    @BindView(R.id.iv_check_pwd)
    ImageView ivCheckPwd;
    @BindView(R.id.iv_check_pwd_re)
    ImageView ivCheckPwdRe;

    List<HospitalModel> hospitalModels;
    List<String> hosList;
    List<Integer> hosListid;


    @OnClick(R.id.ll_left_container)
    public void back(View view) {
        onBackPressed();
    }


    @OnClick(R.id.ll_select_location)
    public void setLcation(View view) {
        JsonData = new JsonDataActivity01().getPosition(RegisterActivity.this, loginTvPosition, new JsonDataActivity01.selectPosition() {
            @Override
            public void getLocation(String pronvince, String city, String area) {
                //TODO 完成选择后的返回
            }

            @Override
            public void getLocationID(int pid, int cid, int aid) {
                int select_provinceid = JsonData.options1Items.get(pid).getId();
                int select_cityid = JsonData.options1Items.get(pid).getCity().get(cid).getId();
                int select_areaid = JsonData.options1Items.get(pid).getCity().get(cid).getArea().get(aid).getId();
                Map<String, Object> value = new HashMap<>();
                value.put("province_id", select_provinceid);
                value.put("city_id", select_cityid);
                value.put("district_id", select_areaid);
                HttpPost(AppConst.GETHOSPITAL, value, 1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
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
        ivCheckPwd.setTag(true);
        ivCheckPwdRe.setTag(true);
    }

    @OnClick(R.id.ll_confirm)
    public void regist(View view){
        Map<String,Object> value = new HashMap<>();
        value.put("hospital_id",hospitalModels.get(0).getHospital_id());
        value.put("area_name",etArea.getText().toString());
        value.put("department_name",etDepartment.getText().toString());
        value.put("subarea_name",etSubarea.getText().toString());
        value.put("bed_number",Integer.parseInt(etBed.getText().toString()));
        value.put("passwd",Integer.parseInt(confirmPwd.getText().toString()));
        HttpPost(AppConst.REGISTER,value,2);
    }


    @Override
    public void getCallBack(String response, int flag) {
        Type type;
        switch (flag) {
            case 1:
                type = new TypeToken<BaseModel<List<HospitalModel>>>() {
                }.getType();
                BaseModel<List<HospitalModel>> hospital = new Gson().fromJson(response, type);
                hospitalModels = hospital.getData();
                hosList = new ArrayList<>();
                hosListid = new ArrayList<>();
                for (HospitalModel hospitalModel : hospital.getData()) {
                    hosList.add(hospitalModel.getName());
                    hosListid.add(hospitalModel.getHospital_id());
                }
                break;
            case 2:
                type = new TypeToken<BaseModel<List<String>>>() {
                }.getType();
                BaseModel<List<String>> regmodle = new Gson().fromJson(response,type);
                if(regmodle.getStatus()==1){
                    showToast(regmodle.getInfo());
                    onBackPressed();
                }else{
                    showToast(regmodle.getInfo());
                }

                break;
        }
    }


    private void setPopDate(TextView textView, List<String> date, List<Integer> ids, View rootView) {
        PopupWindow popupWindoware = PopUpwindowUtil.createPopUpWindowReg(R.layout.popupwindow_layout, this, date, new RegisterPagePopAdapter.ItemClickListener() {
            @Override
            public void itemclick(int position) {
                textView.setText(date.get(position));
                textView.setTag(ids.get(position));
                PopUpwindowUtil.popupWindow.dismiss();
            }
        });
        int height = this.getResources().getDimensionPixelSize(R.dimen.y27);
        popupWindoware.setOutsideTouchable(true);
        System.out.println("X:" + rootView.getX() + ",Y:" + rootView.getY() + ",height:" + rootView.getHeight());
        popupWindoware.showAtLocation(rootView, Gravity.NO_GRAVITY, (int) rootView.getX(), (int) rootView.getY() + rootView.getHeight() + headerTitleRoot.getHeight());
    }

    @OnClick(R.id.ll_select_hospital)
    public void onViewClicked() {
        if (hosList != null) {
            setPopDate(tvHospital, hosList, hosListid, llSelectHospital);
        } else {
            showToast("请先选择地区");
        }
    }

    @OnClick({R.id.iv_check_pwd, R.id.iv_check_pwd_re})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_check_pwd:
                if((boolean)ivCheckPwd.getTag()){
                    ivCheckPwd.setImageResource(R.mipmap.mima02);
                    ivCheckPwd.setTag(false);
                    firstPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    ivCheckPwd.setImageResource(R.mipmap.mima01);
                    ivCheckPwd.setTag(true);
                    firstPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                break;
            case R.id.iv_check_pwd_re:
                if((boolean)ivCheckPwdRe.getTag()){
                    ivCheckPwdRe.setImageResource(R.mipmap.mima02);
                    ivCheckPwdRe.setTag(false);
                    confirmPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    ivCheckPwdRe.setImageResource(R.mipmap.mima01);
                    ivCheckPwdRe.setTag(true);
                    confirmPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                break;
        }
    }
}
