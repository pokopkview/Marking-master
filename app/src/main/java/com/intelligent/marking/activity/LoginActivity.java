package com.intelligent.marking.activity;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Build;
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
import com.intelligent.marking.Utils.PreferencesUtils;
import com.intelligent.marking.Utils.ToastUtil;
import com.intelligent.marking.adapter.SelectMoreAdapter;
import com.intelligent.marking.application.MarkingApplication;
import com.intelligent.marking.net.model.AreaModel;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.DepartModel;
import com.intelligent.marking.net.model.HospitalModel;
import com.intelligent.marking.net.model.LoginModel;
import com.intelligent.marking.net.model.SubAreModel;
import com.intelligent.marking.set.JsonDataActivity01;
import com.intelligent.marking.widget.PopUpwindowUtil;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import android.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    JsonDataActivity01 JsonData;
    //    @BindView(R.id.login_tv_register)
//    TextView loginTvRegister;
    @BindView(R.id.login_tv_position)
    TextView loginTvPosition;
    @BindView(R.id.ll_login_location)
    LinearLayout llLoginLocation;
    @BindView(R.id.ll_select_hist)
    RelativeLayout llSelectHist;
    @BindView(R.id.ll_select_area)
    RelativeLayout llSelectArea;
    @BindView(R.id.ll_select_depart)
    RelativeLayout llSelectDepart;
    @BindView(R.id.ll_select_moreares)
    RelativeLayout llSelectMoreares;
    @BindView(R.id.login_tv01)
    TextView loginTv01;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    //    @BindView(R.id.iv_left)
//    ImageView ivLeft;
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
    @BindView(R.id.tv_hospital_left)
    TextView tvHospitalLeft;
    @BindView(R.id.tv_de_area)
    TextView tvDeArea;
    @BindView(R.id.tv_de_depart)
    TextView tvDeDepart;
    @BindView(R.id.tv_de_subarea)
    TextView tvDeSubarea;
    @BindView(R.id.tv_de_pwd)
    TextView tvDePwd;

    private LoginModel loginModel;

    private BaseModel<List<HospitalModel>> hospital;
    private BaseModel<List<AreaModel>> area;
    private BaseModel<List<SubAreModel>> subArea;
    private BaseModel<List<DepartModel>> depart;
    private List<String> hosList, areList, subList, departList;
    private List<Integer> hosListid, areListid, subListid, departListid;


    private int select_provinceid;
    private int select_cityid;
    private int select_areaid;
    private int select_hos_area;
    private int select_depart;
    private int select_subarea;
    private int select_hosid;

    private ScanBroadcastReceiver scanBroadcastReceiver;


//    static {
//        System.loadLibrary("");
//    }


    @OnClick(R.id.ll_login_location)
    public void setLcation(View view) {
        JsonData = new JsonDataActivity01().getPosition(LoginActivity.this, loginTvPosition, new JsonDataActivity01.selectPosition() {
            @Override
            public void getLocation(String pronvince, String city, String area) {
                //TODO 完成选择后的操作，请求医院接口
            }

            @Override
            public void getLocationID(int pid, int cid, int aid) {
//                ToastUtil.getInstance(LoginActivity.this).show("pid:" + JsonData.options1Items.get(pid).getId()
//                        + ",cid:" + JsonData.options1Items.get(pid).getCity().get(cid).getId()
//                        + ",aid:" + JsonData.options1Items.get(pid).getCity().get(cid).getArea().get(aid).getId());
                select_provinceid = JsonData.options1Items.get(pid).getId();
                select_cityid = JsonData.options1Items.get(pid).getCity().get(cid).getId();
                select_areaid = JsonData.options1Items.get(pid).getCity().get(cid).getArea().get(aid).getId();


                PreferencesUtils.putInt(LoginActivity.this, PreferencesUtils.PROVINCE, pid);
                PreferencesUtils.putInt(LoginActivity.this, PreferencesUtils.CITY, cid);
                PreferencesUtils.putInt(LoginActivity.this, PreferencesUtils.ARE, aid);

                PreferencesUtils.putInt(LoginActivity.this, PreferencesUtils.PROVINCEID, select_provinceid);
                PreferencesUtils.putInt(LoginActivity.this, PreferencesUtils.CITYID, select_cityid);
                PreferencesUtils.putInt(LoginActivity.this, PreferencesUtils.AREID, select_areaid);


                Map<String, Object> value = new HashMap<>();
                value.put("province_id", select_provinceid);
                value.put("city_id", select_cityid);
                value.put("district_id", select_areaid);
                HttpPost(AppConst.GETHOSPITAL, value, 11);
            }
        });
    }

    @OnClick(R.id.iv_left)
    public void clickLeft(View view) {
        //TODO 扫二维码
        MarkingApplication.openScan();

    }


    @OnClick(R.id.rl_login)
    public void login(View view) {
//        if() {
//            loginModel.setArea_id();
//        }
        hospitalName = tvHospital.getText().toString();
        areaName = tvArea.getText().toString();
        departName = tvDepart.getText().toString();
        subareaName = tvSubarea.getText().toString();
        if(tvHospital.getTag()!=null) {
            hospitalNameid = (int) tvHospital.getTag();
        }else{
            showToast("请选择医院");
            return;
        }

        if(tvArea.getTag()!=null) {
            areaNameid = (int) tvArea.getTag();
        }else{
            showToast("请选择大区");
            return;
        }
        if(tvDepart.getTag()!=null) {
            departNameid = (int) tvDepart.getTag();
        }else{
            showToast("请选择科室");
            return;
        }
        if(tvSubarea.getTag()!=null) {
            subareaNameid = (int) tvSubarea.getTag();
        }else{
            showToast("请选择片区");
            return;
        }

        Map<String, Object> value = new HashMap<>();
        value.put("hospital_id", hospitalNameid);
        value.put("area_id", areaNameid);
        value.put("department_id", departNameid);
        value.put("subarea_id", subareaNameid);
        if(!etPwd.getText().toString().isEmpty()) {
            value.put("passwd", Integer.parseInt(etPwd.getText().toString()));
        }else{
            showToast("请输入密码");
            return;
        }
        rlLogin.setClickable(false);
        showProgress();
        HttpPost(AppConst.LOGIN, value, 5);
    }


    @OnClick(R.id.login_tv_register)
    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
//        MarkingApplication.openScan();
    }


    @OnClick(R.id.ll_select_depart)
    public void selectDepart(View view) {
//        llSelectDepart.setTag(select_depart);
//        if(depart!=null){
//            setPopDate(tvDepart,departList,departListid,llSelectDepart);
//        }else{
        if (null == tvArea.getTag()) {
            showToast("请先选择大区");
        } else {
            if (departList == null) {
                getDepart((int) tvArea.getTag());
            } else {
                setPopDate(tvDepart, departList, departListid, llSelectDepart);
            }
        }
//        }
    }


    @OnClick(R.id.ll_select_moreares)
    public void selectSubAre(View view) {
//        llSelectMoreares.setTag(select_subarea);
//        if(subArea!=null){
//            setPopDate(tvSubarea,subList,subListid,llSelectMoreares);
//        }else{
        if (null == tvDepart.getTag()) {
            showToast("请先选择科室");
        } else {
            if (subList == null) {
                getSubAre((int) tvDepart.getTag());
            } else {
                setPopDate(tvSubarea, subList, subListid, llSelectMoreares);
            }
        }
//        }
    }

    /**
     * 获取分区信息
     *
     * @param departid
     */
    private void getSubAre(int departid) {
        Map<String, Object> value = new HashMap();
        value.put("department_id", departid);
        HttpPost(AppConst.GETSUBAREA, value, 3);
    }

    /**
     * 获取科室信息
     *
     * @param areaid
     */
    private void getDepart(int areaid) {
        Map<String, Object> value = new HashMap();
        value.put("area_id", areaid);
        HttpPost(AppConst.GETDEPARTMENT, value, 4);
    }

    /**
     * 获取医院大区数据
     *
     * @param view
     */
    @OnClick(R.id.ll_select_hist)
    public void selectHosp(View view) {
        llSelectHist.setTag("hos");
        if (hospital != null) {
            setPopDate(tvHospital, hosList, hosListid, llSelectHist);
        } else {
            showToast("请先选择地区");
        }
    }


    @OnClick(R.id.ll_select_area)
    public void selectArea(View view) {
        if (null == tvHospital.getTag()) {
            showToast("请先选择医院");
        } else {
            if (areList == null) {
                getAreaDate((int) tvHospital.getTag());
            } else {
                setPopDate(tvArea, areList, areListid, llSelectArea);
            }
        }
//        }
    }

    @OnClick(R.id.iv_show_pwd)
    public void onViewClicked() {
        if((boolean)ivShowPwd.getTag()){
            ivShowPwd.setImageResource(R.mipmap.mima02);
            ivShowPwd.setTag(false);
            etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else{
            ivShowPwd.setImageResource(R.mipmap.mima01);
            ivShowPwd.setTag(true);
            etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }


    }

    class ScanBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String text1 = intent.getExtras().getString("code");
            String resec = new String(Base64.decode(text1.getBytes(),Base64.DEFAULT));
            Type  type = new TypeToken<Map<String,Object>>(){}.getType();
            HttpPost(AppConst.LOGIN,new Gson().fromJson(resec,type),15);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        ButterKnife.bind(this);
        ivShowPwd.setTag(true);
//        etPwd.setText("123456");
        loginModel = new LoginModel();
        initView();
        scanBroadcastReceiver = new ScanBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.qs.scancode");
        this.registerReceiver(scanBroadcastReceiver, intentFilter);
//
        if (PreferencesUtils.getInt(this, PreferencesUtils.SUBAREANAMEID, -1) != -1) {
            tvHospital.setText(PreferencesUtils.getString(this, PreferencesUtils.HOSPITALNAME));
            tvArea.setText(PreferencesUtils.getString(this, PreferencesUtils.AREANAME));
            tvDepart.setText(PreferencesUtils.getString(this, PreferencesUtils.DEPARTNAME));
            tvSubarea.setText(PreferencesUtils.getString(this, PreferencesUtils.SUBAREANAME));
            loginTvPosition.setText(PreferencesUtils.getString(this, PreferencesUtils.LOCATION));

            tvHospital.setTag(PreferencesUtils.getInt(this, PreferencesUtils.HOSPITALNAMEID, -1));
            tvArea.setTag(PreferencesUtils.getInt(this, PreferencesUtils.AREANAMEID, -1));
            tvDepart.setTag(PreferencesUtils.getInt(this, PreferencesUtils.DEPARTNAMEID, -1));
            tvSubarea.setTag(PreferencesUtils.getInt(this, PreferencesUtils.SUBAREANAMEID, -1));
            value.clear();
            value.put("province_id", PreferencesUtils.getInt(this, PreferencesUtils.PROVINCEID, -1));
            value.put("city_id", PreferencesUtils.getInt(this, PreferencesUtils.CITYID, -1));
            value.put("district_id", PreferencesUtils.getInt(this, PreferencesUtils.AREID, -1));
            HttpPost(AppConst.GETHOSPITAL, value, 11);
        }
//        Intent intent = new Intent("COM.QS.DEMO.QSSERVICE");
//        Intent eintent = new Intent(getExplicitIntent(this, intent));
//        this.startService(eintent);
//        bindService(eintent, conn, Service.BIND_AUTO_CREATE);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(scanBroadcastReceiver);
    }

    /**
     * 获取大区信息
     *
     * @param hospiid
     */
    private void getAreaDate(int hospiid) {
        Map<String, Object> value = new HashMap<>();
        value.put("hospital_id", hospiid);
        HttpPost(AppConst.GETAREA, value, 2);
    }

    /**
     * 获取医院信息
     *
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
    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void getCallBack(String response, int flag) {
        Type type;
        System.out.println("flag:" + flag);
        switch (flag) {
            case 1:
                type = new TypeToken<BaseModel<List<HospitalModel>>>() {
                }.getType();
                hospital = new Gson().fromJson(response, type);
                hosList = new ArrayList<>();
                hosListid = new ArrayList<>();
                for (HospitalModel hospitalModel : hospital.getData()) {
                    hosList.add(hospitalModel.getName());
                    hosListid.add(hospitalModel.getHospital_id());
                }
//                if(hosList.size()>1){
//                    hosList.add("全部");
//                }
                setPopDate(tvHospital, hosList, hosListid, llSelectHist);
                break;
            case 2:
                type = new TypeToken<BaseModel<List<AreaModel>>>() {
                }.getType();
                area = new Gson().fromJson(response, type);
                areList = new ArrayList<>();
                areListid = new ArrayList<>();
                for (AreaModel hospitalModel : area.getData()) {
                    areList.add(hospitalModel.getArea_name());
                    areListid.add(hospitalModel.getArea_id());
                }
                if (areList.size() > 1) {
                    areList.add("全部");
                }
                setPopDate(tvArea, areList, areListid, llSelectArea);
                break;
            case 3:
                type = new TypeToken<BaseModel<List<SubAreModel>>>() {
                }.getType();
                subArea = new Gson().fromJson(response, type);
                subList = new ArrayList<>();
                subListid = new ArrayList<>();
                for (SubAreModel hospitalModel : subArea.getData()) {
                    subList.add(hospitalModel.getSubarea_name());
                    subListid.add(hospitalModel.getSubarea_id());
                }
                if (subList.size() > 1) {
                    subList.add("全部");
                }
                setPopDate(tvSubarea, subList, subListid, llSelectMoreares);
                break;
            case 4:
                type = new TypeToken<BaseModel<List<DepartModel>>>() {
                }.getType();
                depart = new Gson().fromJson(response, type);
                departList = new ArrayList<>();
                departListid = new ArrayList<>();
                for (DepartModel hospitalModel : depart.getData()) {
                    departList.add(hospitalModel.getDepartment_name());
                    departListid.add(hospitalModel.getDepartment_id());
                }
                if (departList.size() > 1) {
                    departList.add("全部");
                }
                setPopDate(tvDepart, departList, departListid, llSelectDepart);
                break;
            case 5:
                disMissPro();
                rlLogin.setClickable(true);
                type = new TypeToken<BaseModel<List<String>>>() {
                }.getType();
                BaseModel<List<String>> loginmodel = new Gson().fromJson(response, type);
                if (loginmodel.getStatus() == 0) {
                    showToast(loginmodel.getInfo());
                } else {
                    PreferencesUtils.putString(this, PreferencesUtils.LOCATION, loginTvPosition.getText().toString());
                    PreferencesUtils.putString(this, PreferencesUtils.UUID, loginmodel.getUuid());
                    PreferencesUtils.putString(this, PreferencesUtils.HOSPITALNAME, hospitalName);
                    PreferencesUtils.putString(this, PreferencesUtils.AREANAME, areaName);
                    PreferencesUtils.putString(this, PreferencesUtils.DEPARTNAME, departName);
                    PreferencesUtils.putString(this, PreferencesUtils.SUBAREANAME, subareaName);
                    PreferencesUtils.putInt(this, PreferencesUtils.HOSPITALNAMEID, hospitalNameid);
                    PreferencesUtils.putInt(this, PreferencesUtils.AREANAMEID, areaNameid);
                    PreferencesUtils.putInt(this, PreferencesUtils.DEPARTNAMEID, departNameid);
                    PreferencesUtils.putInt(this, PreferencesUtils.SUBAREANAMEID, subareaNameid);
                    String pwd = etPwd.getText().toString();
//                    String strpwd = new String(Base64.encode(pwd.getBytes(),1));
                    String strpwd = Base64.encodeToString(pwd.getBytes(),Base64.DEFAULT);
                    PreferencesUtils.putString(this,PreferencesUtils.PWD,strpwd);

                    Map<String,Object> save = new HashMap<>();
                    save.put("hospital_id", hospitalNameid);
                    save.put("area_id", areaNameid);
                    save.put("department_id", departNameid);
                    save.put("subarea_id", subareaNameid);
                    save.put("passwd",pwd);
                    String json = new Gson().toJson(save);
                    String sec = Base64.encodeToString(json.getBytes(),Base64.DEFAULT);//base64加密
                    Bitmap bitmap = MarkingApplication.createQRImage(sec,300,300);
                    MarkingApplication.printBitmap(0,bitmap);
                    if (areaNameid == 0 && departNameid == 0 && departNameid == 0 && subareaNameid == 0) {
                        PreferencesUtils.putInt(this, PreferencesUtils.ROLE, 1);//医院总账户
                        startActivity(new Intent(LoginActivity.this, HospoitalManagerAvtivity.class));
                    } else if (areaNameid != 0 && departNameid == 0 && subareaNameid == 0) {
                        PreferencesUtils.putInt(this, PreferencesUtils.ROLE, 2);//大区总账户
                        startActivity(new Intent(LoginActivity.this, HospoitalManagerAvtivity.class));
                    } else if (areaNameid != 0 && departNameid != 0 && subareaNameid == 0) {
                        PreferencesUtils.putInt(this, PreferencesUtils.ROLE, 3);//科室总账户
                        startActivity(new Intent(LoginActivity.this, HospoitalManagerAvtivity.class));
                    } else if (areaNameid != 0 && departNameid != 0 && subareaNameid != 0) {
                        PreferencesUtils.putInt(this, PreferencesUtils.ROLE, 4);//区域总账户
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    PreferencesUtils.putLong(this,PreferencesUtils.TIME,System.currentTimeMillis());
                    finish();
                }
                break;
            case 11:
                type = new TypeToken<BaseModel<List<HospitalModel>>>() {
                }.getType();
                hospital = new Gson().fromJson(response, type);
                hosList = new ArrayList<>();
                hosListid = new ArrayList<>();
                for (HospitalModel hospitalModel : hospital.getData()) {
                    hosList.add(hospitalModel.getName());
                    hosListid.add(hospitalModel.getHospital_id());
                }
//                if(hosList.size()>1){
//                    hosList.add("全部");
//                }
                break;
            case 12:
                type = new TypeToken<BaseModel<List<AreaModel>>>() {
                }.getType();
                area = new Gson().fromJson(response, type);
                areList = new ArrayList<>();
                areListid = new ArrayList<>();
                for (AreaModel hospitalModel : area.getData()) {
                    areList.add(hospitalModel.getArea_name());
                    areListid.add(hospitalModel.getArea_id());
                }
                if (areList.size() > 1) {
                    areList.add("全部");
                }
//                setPopDate(tvArea, areList, areListid, llSelectArea);
                break;
            case 13:
                type = new TypeToken<BaseModel<List<SubAreModel>>>() {
                }.getType();
                subArea = new Gson().fromJson(response, type);
                subList = new ArrayList<>();
                subListid = new ArrayList<>();
                for (SubAreModel hospitalModel : subArea.getData()) {
                    subList.add(hospitalModel.getSubarea_name());
                    subListid.add(hospitalModel.getSubarea_id());
                }
                if (subList.size() > 1) {
                    subList.add("全部");
                }
//                setPopDate(tvSubarea, subList, subListid, llSelectMoreares);
                break;
            case 14:
                type = new TypeToken<BaseModel<List<DepartModel>>>() {
                }.getType();
                depart = new Gson().fromJson(response, type);
                departList = new ArrayList<>();
                departListid = new ArrayList<>();
                for (DepartModel hospitalModel : depart.getData()) {
                    departList.add(hospitalModel.getDepartment_name());
                    departListid.add(hospitalModel.getDepartment_id());
                }
                if (departList.size() > 1) {
                    departList.add("全部");
                }
//                setPopDate(tvDepart, departList, departListid, llSelectDepart);
                break;
            case 15:
                type = new TypeToken<BaseModel<List<String>>>() {
                }.getType();
                PreferencesUtils.putLong(this,PreferencesUtils.TIME,System.currentTimeMillis());
                BaseModel<List<String>> loginmodels = new Gson().fromJson(response, type);
                if (loginmodels.getStatus() == 0) {
                    showToast(loginmodels.getInfo());
                } else {
                    switch (PreferencesUtils.getInt(this,PreferencesUtils.ROLE)){
                        case 1:
                        case 2:
                        case 3:
                            startActivity(new Intent(LoginActivity.this, HospoitalManagerAvtivity.class));
                            break;
                        case 4:
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            break;
                    }
                    finish();
                }

                break;
        }
    }


    private void setPopDate(TextView textView, List<String> date, List<Integer> ids, View rootView) {
        PopupWindow popupWindoware = PopUpwindowUtil.createPopUpWindow(R.layout.popupwindow_layout, this, date, new SelectMoreAdapter.itemClickListener() {
            @Override
            public void itemclick(int pos, View view) {
                textView.setText(date.get(pos));
                textView.setTag(ids.get(pos));
                if (rootView.equals(llSelectArea)) {
                    llSelectDepart.setClickable(true);
                    llSelectMoreares.setClickable(true);
                    Map<String, Object> value = new HashMap();
                    value.put("area_id", (int) tvArea.getTag());
                    HttpPost(AppConst.GETDEPARTMENT, value, 14);
                } else if (rootView.equals(llSelectDepart)) {
                    llSelectMoreares.setClickable(true);
                    Map<String, Object> value = new HashMap();
                    value.put("department_id", (int) tvDepart.getTag());
                    HttpPost(AppConst.GETSUBAREA, value, 13);
                } else if (rootView.equals(llSelectHist)) {
                    Map<String, Object> value = new HashMap<>();
                    value.put("hospital_id", (int) tvHospital.getTag());
                    HttpPost(AppConst.GETAREA, value, 12);
                }
                PopUpwindowUtil.popupWindow.dismiss();
            }

            @Override
            public void footclick() {
                //select all
                if (rootView.getTag() != null) {
                    textView.setText(date.get(date.size() - 1));
                    textView.setTag(ids.get(ids.size() - 1));
                    PopUpwindowUtil.popupWindow.dismiss();
                } else {
                    textView.setText("全部");
                    textView.setTag(0);
                    if (rootView.equals(llSelectArea)) {
                        llSelectDepart.setClickable(false);
                        tvDepart.setText("全部");
                        tvDepart.setTag(0);
                        tvSubarea.setText("全部");
                        tvSubarea.setTag(0);
                        llSelectMoreares.setClickable(false);
                    } else if (rootView.equals(llSelectDepart)) {
                        llSelectMoreares.setClickable(false);
                        tvSubarea.setText("全部");
                        tvSubarea.setTag(0);
                    }
                    PopUpwindowUtil.popupWindow.dismiss();
                }
            }
        });
        int height = this.getResources().getDimensionPixelSize(R.dimen.y27);
        popupWindoware.setOutsideTouchable(true);
        popupWindoware.showAtLocation(rootView, Gravity.NO_GRAVITY, (int) rootView.getX(), (int) rootView.getY() + height);
    }

    private void initView() {

    }


}
