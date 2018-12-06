package com.intelligent.marking.activity;

import android.app.AlertDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.IMyAidlInterface;
import com.intelligent.marking.R;
import com.intelligent.marking.Utils.BitmapTools;
import com.intelligent.marking.Utils.PosUtils;
import com.intelligent.marking.Utils.ToastUtil;
import com.intelligent.marking.adapter.SelectMoreAdapter;
import com.intelligent.marking.application.MarkingApplication;
import com.intelligent.marking.common.okgo.App;
import com.intelligent.marking.net.model.AreaModel;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.DepartModel;
import com.intelligent.marking.net.model.HospitalModel;
import com.intelligent.marking.net.model.LoginModel;
import com.intelligent.marking.net.model.SubAreModel;
import com.intelligent.marking.set.JsonDataActivity01;
import com.intelligent.marking.widget.PopUpwindowUtil;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    JsonDataActivity01 JsonData;
    @BindView(R.id.login_tv_register)
    TextView loginTvRegister;
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
    public void setLcation(View view){
        JsonData  = new JsonDataActivity01().getPosition(LoginActivity.this, loginTvPosition, new JsonDataActivity01.selectPosition() {
            @Override
            public void getLocation(String pronvince, String city, String area) {
                //TODO 完成选择后的操作，请求医院接口
            }

            @Override
            public void getLocationID(int pid, int cid, int aid) {
                ToastUtil.getInstance(LoginActivity.this).show("pid:"+JsonData.options1Items.get(pid).getId()
                +",cid:"+JsonData.options1Items.get(pid).getCity().get(cid).getId()
                +",aid:"+JsonData.options1Items.get(pid).getCity().get(cid).getArea().get(aid).getId());
                select_provinceid = JsonData.options1Items.get(pid).getId();
                select_cityid = JsonData.options1Items.get(pid).getCity().get(cid).getId();
                select_areaid = JsonData.options1Items.get(pid).getCity().get(cid).getArea().get(aid).getId();
                Map<String, Object> value = new HashMap<>();
                value.put("province_id", select_provinceid);
                value.put("city_id", select_cityid);
                value.put("district_id", select_areaid);
                HttpPost(AppConst.GETHOSPITAL,value,11);
            }
        });
    }


    // 打印文字
    private void printeText(String str) {
        MarkingApplication.printText(1, 0, str + "\n");
    }

    @OnClick(R.id.iv_left)
    public void clickLeft(View view) {
        //TODO 扫二维码
//        startActivity(new Intent(this,ChangeBedInfoActivity.class));

//        ScanDomn();
//        openDevice();
//        printQRCode();
//        printeText("test");
//        printQrCode();
//        printBarCode();
        MarkingApplication.openScan();
    }

    // 打印二维码
    private void printQrCode() {
        // 获取编辑框中的字符串
        MarkingApplication.printQRCode(0, 300,300, "test");
    }

    // 打印条码
    private void printBarCode() {
        // 获取编辑框中的字符串
//        str_massage = tv.getText().toString().trim();
//        if (str_massage == null || str_massage.length() <= 0)
//            return;
//
//        // 判断当前字符能否生成条码
//        if (str_massage.getBytes().length > str_massage.length()) {
//            Toast.makeText(MainPrinterActivity.this, "当前数据不能生成一维码",
//                    Toast.LENGTH_SHORT).show();
//            return;
//        }

        MarkingApplication.printBarCode(0, 380, 100, "test");
    }


    @OnClick(R.id.rl_login)
    public void login(View view){
//        if() {
//            loginModel.setArea_id();
//        }
//        Map<String,Object> value = new HashMap<>();
//        value.put("id",47494);
//        HttpPost(AppConst.GETPROVINCE,value,5);
        hospitalName = "湘雅附二";
        areaName = "外科楼";
        departName = "骨科";
        subareaName = "骨一科";
        startActivity(new Intent(LoginActivity.this,MainActivity.class));


    }


    @OnClick(R.id.login_tv_register)
    public void register(View view){
        startActivity(new Intent(this,RegisterActivity.class));
    }


    @OnClick(R.id.ll_select_depart)
    public void selectDepart(View view){
//        llSelectDepart.setTag(select_depart);
//        if(depart!=null){
//            setPopDate(tvDepart,departList,departListid,llSelectDepart);
//        }else{
            getDepart((int)tvArea.getTag());
//        }
    }


    @OnClick(R.id.ll_select_moreares)
    public void selectSubAre(View view){
//        llSelectMoreares.setTag(select_subarea);
//        if(subArea!=null){
//            setPopDate(tvSubarea,subList,subListid,llSelectMoreares);
//        }else{
            getSubAre((int)tvDepart.getTag());
//        }
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
        llSelectHist.setTag("hos");
        if(hospital!=null){
            setPopDate(tvHospital,hosList,hosListid,llSelectHist);
        }else {
            getHospiDate(1, 1, 1);
        }
    }


    @OnClick(R.id.ll_select_area)
    public void selectArea(View view) {
//        llSelectArea.setTag(select_hos_area);
//        if(area!=null) {
//            setPopDate(tvArea,areList,areListid,llSelectArea);
//        }else{
            getAreaDate((int)tvHospital.getTag());
//        }
    }

    class ScanBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String text1 = intent.getExtras().getString("code");
            System.out.println("scan:"+text1);
//            tv.setText(text1);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        ButterKnife.bind(this);
        loginModel = new LoginModel();
        initView();
        scanBroadcastReceiver = new ScanBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.qs.scancode");
        this.registerReceiver(scanBroadcastReceiver, intentFilter);
//
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
        System.out.println("flag:"+flag);
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
                setPopDate(tvHospital,hosList,hosListid,llSelectHist);
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
                subListid = new ArrayList<>();
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
                departListid = new ArrayList<>();
                for (DepartModel hospitalModel : depart.getData()) {
                    departList.add(hospitalModel.getDepartment_name());
                    departListid.add(hospitalModel.getDepartment_id());
                }
                if(departList.size()>1){
                    departList.add("全部");
                }
                setPopDate(tvDepart,departList,departListid,llSelectDepart);
                break;
            case 5:
                System.out.println(response);
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
                System.out.println(hospital ==null);
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
                if(rootView.getTag()!=null) {
                    textView.setText(date.get(date.size()-1));
                    textView.setTag(ids.get(ids.size()-1));
                    PopUpwindowUtil.popupWindow.dismiss();
                }else{
                    textView.setText("全部");
                    textView.setTag(0);
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
