package com.intelligent.marking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.intelligent.marking.adapter.SelectMoreAdapter;
import com.intelligent.marking.net.model.AreaModel;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.DepartModel;
import com.intelligent.marking.net.model.HospitalModel;
import com.intelligent.marking.net.model.LoginModel;
import com.intelligent.marking.net.model.SubAreModel;
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

public class LoginActivity extends BaseActivity {


    String JsonData;
    @BindView(R.id.login_tv_register)
    TextView loginTvRegister;
    @BindView(R.id.login_tv_position)
    TextView loginTvPosition;
    @BindView(R.id.ll_login_location)
    LinearLayout llLoginLocation;
    @BindView(R.id.ll_select_hist)
    LinearLayout llSelectHist;
    @BindView(R.id.ll_select_area)
    LinearLayout llSelectArea;
    @BindView(R.id.ll_select_depart)
    LinearLayout llSelectDepart;
    @BindView(R.id.ll_select_moreares)
    LinearLayout llSelectMoreares;
    @BindView(R.id.login_tv01)
    TextView loginTv01;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.iv_login_icon)
    ImageView ivLoginIcon;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_depart)
    TextView tvDepart;
    @BindView(R.id.tv_subarea)
    TextView tvSubarea;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.iv_show_pwd)
    ImageView ivShowPwd;

    private LoginModel loginModel;

    private BaseModel<List<HospitalModel>> hospital;
    private BaseModel<List<AreaModel>> area;
    private BaseModel<List<SubAreModel>> subArea;
    private BaseModel<List<DepartModel>> depart;
    private List<String> hosList,areList,subList,departList;
    private List<Integer> hosListid,areListid,subListid,departListid;

    @OnClick(R.id.ll_login_location)
    public void setLcation(View view){
        JsonData  = new JsonDataActivity01().getPosition(LoginActivity.this, loginTvPosition, new JsonDataActivity01.selectPosition() {
            @Override
            public void getLocation(String pronvince, String city, String area) {
                //TODO 完成选择后的操作，请求医院接口
            }
        });
    }


    @OnClick(R.id.iv_left)
    public void clickLeft(View view) {
        //TODO 扫二维码

    }

    @OnClick(R.id.rl_login)
    public void login(View view){
//        if() {
//            loginModel.setArea_id();
//        }

    }


    @OnClick(R.id.login_tv_register)
    public void register(View view){
        startActivity(new Intent(this,RegisterActivity.class));
    }


    @OnClick(R.id.ll_select_depart)
    public void selectDepart(View view){
        if(depart!=null){
            setPopDate(tvDepart,departList,departListid,llSelectDepart);
        }else{
            getDepart(1);
        }
    }


    @OnClick(R.id.ll_select_moreares)
    public void selectSubAre(View view){
        if(subArea!=null){
            setPopDate(tvSubarea,subList,subListid,llSelectMoreares);
        }else{
            getSubAre(1);
        }
    }

    /**
     * 获取分区信息
     * @param departid
     */
    private void getSubAre(int departid){
        Map<String,Object> value = new HashMap();
        value.put("department_id",departid);
        HttpPost(AppConst.GETSUBAREA,value,3);
    }

    /**
     * 获取科室信息
     * @param areaid
     */
    private void getDepart(int areaid){
        Map<String,Object> value = new HashMap();
        value.put("area_id",areaid);
        HttpPost(AppConst.GETDEPARTMENT,value,4);
    }

    /**
     * 获取医院大区数据
     *
     * @param view
     */
    @OnClick(R.id.ll_select_hist)
    public void selectHosp(View view) {
        if(hospital!=null){
            setPopDate(tvHospital,hosList,hosListid,llSelectHist);
        }else {
            getHospiDate(25579, 25580, 25582);
        }
    }


    @OnClick(R.id.ll_select_area)
    public void selectArea(View view) {
        if(area!=null) {
            setPopDate(tvArea,areList,areListid,llSelectArea);
        }else{
            getAreaDate(20);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        ButterKnife.bind(this);
        loginModel = new LoginModel();
        initView();
    }

    /**
     * 获取大区信息
     * @param hospiid
     */
    private void getAreaDate(int hospiid) {
        Map<String, Object> value = new HashMap<>();
        value.put("hospital_id", hospiid);
        HttpPost(AppConst.GETAREA, value, 2);
    }

    /**
     * 获取医院信息
     * @param proid
     * @param cityid
     * @param disid
     */
    private void getHospiDate(int proid, int cityid, int disid) {
        Map<String, Object> value = new HashMap<>();
        value.put("province_id", proid);
        value.put("city_id", cityid);
        value.put("district_id", disid);
        HttpPost(AppConst.GETHOSPITAL, value, 1);
    }

    @Override
    public void getCallBack(String response, int flag) {
        Type type;
        switch (flag) {
            case 1:
                type = new TypeToken<BaseModel<List<HospitalModel>>>() {
                }.getType();
                hospital = new Gson().fromJson(response, type);
                hosList = new ArrayList<>();
                for (HospitalModel hospitalModel : hospital.getData()) {
                    hosList.add(hospitalModel.getName());
                    hosListid.add(hospitalModel.getHospital_id());
                }
                if(hosList.size()>1){
                    hosList.add("全部");
                }
                setPopDate(tvHospital,hosList,hosListid,llSelectHist);
                break;
            case 2:
                type = new TypeToken<BaseModel<List<AreaModel>>>() {
                }.getType();
                area = new Gson().fromJson(response, type);
                 areList = new ArrayList<>();
                for (AreaModel hospitalModel : area.getData()) {
                    areList.add(hospitalModel.getArea_name());
                    areListid.add(hospitalModel.getArea_id());
                }
                if(areList.size()>1){
                    areList.add("全部");
                }
                setPopDate(tvArea,areList,areListid,llSelectArea);
                break;
            case 3:
                type = new TypeToken<BaseModel<List<SubAreModel>>>() {
                }.getType();
                subArea = new Gson().fromJson(response, type);
                subList = new ArrayList<>();
                for (SubAreModel hospitalModel : subArea.getData()) {
                    subList.add(hospitalModel.getSubarea_name());
                    subListid.add(hospitalModel.getSubarea_id());
                }
                if(subList.size()>1){
                    subList.add("全部");
                }
                setPopDate(tvSubarea,subList,subListid,llSelectMoreares);
                break;
            case 4:
                type = new TypeToken<BaseModel<List<DepartModel>>>() {
                }.getType();
                depart = new Gson().fromJson(response, type);
                departList = new ArrayList<>();
                for (DepartModel hospitalModel : depart.getData()) {
                    departList.add(hospitalModel.getDepartment_name());
                    departListid.add(hospitalModel.getDepartment_id());
                }
                if(departList.size()>1){
                    departList.add("全部");
                }
                setPopDate(tvDepart,departList,departListid,llSelectDepart);
                break;
        }
    }


    private void setPopDate(TextView textView,List<String> date,List<Integer> ids,View rootView){
        PopupWindow popupWindoware = PopUpwindowUtil.createPopUpWindow(R.layout.popupwindow_layout, this, date, new SelectMoreAdapter.itemClickListener() {
            @Override
            public void itemclick(int pos, View view) {
                textView.setText(date.get(pos));
                textView.setTag(ids.get(pos));
                PopUpwindowUtil.popupWindow.dismiss();
            }

            @Override
            public void footclick() {
                //select all
                textView.setText("全部");
                PopUpwindowUtil.popupWindow.dismiss();
            }
        });
        int height = this.getResources().getDimensionPixelSize(R.dimen.y27);
        popupWindoware.setOutsideTouchable(true);
        popupWindoware.showAtLocation(rootView, Gravity.NO_GRAVITY, (int) rootView.getX(), (int) rootView.getY() + height);
    }

    private void initView() {

    }

}
