package com.intelligent.marking.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.posapi.PosApi;
import android.posapi.PrintQueue;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.Utils.BitmapTools;
import com.intelligent.marking.Utils.PosUtils;
import com.intelligent.marking.Utils.ToastUtil;
import com.intelligent.marking.adapter.SelectMoreAdapter;
import com.intelligent.marking.net.model.AreaModel;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.DepartModel;
import com.intelligent.marking.net.model.HospitalModel;
import com.intelligent.marking.net.model.LoginModel;
import com.intelligent.marking.net.model.SubAreModel;
import com.intelligent.marking.service.ScanService;
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


    private int select_provinceid;
    private int select_cityid;
    private int select_areaid;
    private int select_hos_area;
    private int select_depart;
    private int select_subarea;
    private int select_hosid;


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


    @OnClick(R.id.iv_left)
    public void clickLeft(View view) {
        //TODO 扫二维码
//        startActivity(new Intent(this,ChangeBedInfoActivity.class));

//        ScanDomn();
        openDevice();
    }

    @OnClick(R.id.rl_login)
    public void login(View view){
//        if() {
//            loginModel.setArea_id();
//        }
//        Map<String,Object> value = new HashMap<>();
//        value.put("id",47494);
//        HttpPost(AppConst.GETPROVINCE,value,5);
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        ButterKnife.bind(this);
        loginModel = new LoginModel();
        initView();
//        mPosSDK = PosApi.getInstance(this);
//
//        // 根据型号进行初始化mPosApi类
//        if (Build.MODEL.contains("LTE")
//                || android.os.Build.DISPLAY.contains("3508")
//                || android.os.Build.DISPLAY.contains("403")
//                || android.os.Build.DISPLAY.contains("35S09")) {
//            mPosSDK.initPosDev("ima35s09");
//        } else if (Build.MODEL.contains("5501")) {
//            mPosSDK.initPosDev("ima35s12");
//        } else {
//            mPosSDK.initPosDev(PosApi.PRODUCT_MODEL_IMA80M01);
//        }
//
//        IntentFilter mFilter = new IntentFilter();
//        mFilter.addAction(PosApi.ACTION_POS_COMM_STATUS);
//        registerReceiver(receiver,mFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
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


    /*



    POS机相关代码

     */

    PrintQueue mPrintQueue;
    MediaPlayer player;
    PosApi mPosSDK;
    private byte mGpioPower = 0x1E;// PB14
    private byte mGpioTrig = 0x29;// PC9

    private int mCurSerialNo = 3; // usart3
    private int mBaudrate = 4; // 9600

    /**
     * 扫描信息获取
     */
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if (action.equalsIgnoreCase(PosApi.ACTION_POS_COMM_STATUS)) {

                // 串口标志判断
                int cmdFlag = intent.getIntExtra(PosApi.KEY_CMD_FLAG, -1);

                // 获取串口返回的字节数组
                byte[] buffer = intent
                        .getByteArrayExtra(PosApi.KEY_CMD_DATA_BUFFER);

                switch (cmdFlag) {
                    // 如果为扫描数据返回串口
                    case PosApi.POS_EXPAND_SERIAL3:
                        if (buffer == null)
                            return;
                        try {
                            // 将字节数组转成字符串
                            String str = new String(buffer, "GBK");

                            // 开启提示音，提示客户条码或者二维码已经被扫到
                            player.start();

                            // 显示到编辑框中
//                            mTv.setText(str);
                            ToastUtil.getInstance(context).show(str);

                        } catch (UnsupportedEncodingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;

                }
                // 扫描完本次后清空，以便下次扫描
                buffer = null;

            }
        }

    };


    /**
     * 执行扫描，扫描后的结果会通过action为PosApi.ACTION_POS_COMM_STATUS的广播发回
     */
    boolean isScan = false;
    public void ScanDomn(){
        if (!isScan) {
            mPosSDK.gpioControl(mGpioTrig, 0, 0);
            isScan = true;
            handler.removeCallbacks(run);
            // 3秒后还没有扫描到信息则强制关闭扫描头
            handler.postDelayed(run, 3000);
        } else {
            mPosSDK.gpioControl(mGpioTrig, 0, 1);
            mPosSDK.gpioControl(mGpioTrig, 0, 0);
            isScan = true;
            handler.removeCallbacks(run);
            // 3秒后还没有扫描到信息则强制关闭扫描头
            handler.postDelayed(run, 3000);
        }
    }

    /**
     * 打印二维码
     */
    public void printQRCode() {

        // 获取编辑框中的字符串
//        str2 = mTv.getText().toString().trim();

        String str2 = "ceshiday";
        if (str2 == null || str2.length() <= 0)
            return;

        try {

            // 二维码生成
            Bitmap btMap = PosUtils.encode2dAsBitmap(str2, 300, 300, 2);

            // 二维码图片转成打印字节数组
            byte[] printData = BitmapTools.bitmap2PrinterBytes(btMap);

            // 将打印数组添加到打印队列
            mPrintQueue.addBmp(50, 50, btMap.getWidth(), btMap.getHeight(),
                    printData);

            // 打印6个空行，使二维码显示到打印头外面
            mPrintQueue.addText(50, "\n\n\n\n\n\n".getBytes("GBK"));

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 打印队列开始
        mPrintQueue.printStart();

    }



    /**
     * 生成二维码 要转换的地址或字符串,可以是中文
     *
     * @param url
     * @param width
     * @param height
     * @return
     */
    public Bitmap createQRImage(String url, int width, int height) {
        try {
            // 判断URL合法性
            if (url == null || "".equals(url) || url.length() < 1) {
                return null;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "GBK");
            // 图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(url,
                    BarcodeFormat.QR_CODE, width, height, hints);
            // bitMatrix = deleteWhite(bitMatrix);// 删除白边
            bitMatrix = PosUtils.deleteWhite(bitMatrix);// 删除白边
            width = bitMatrix.getWidth();
            height = bitMatrix.getHeight();
            int[] pixels = new int[width * height];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = 0xff000000;
                    } else {
                        pixels[y * width + x] = 0xffffffff;
                    }
                }
            }
            // 生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap
                    .createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    Handler handler = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            // 强制关闭扫描头
            mPosSDK.gpioControl(mGpioTrig, 0, 1);
            isScan = false;
        }
    };


    private void openDevice(){
        //open power

        ScanService.mApi.gpioControl(mGpioPower,0,1);

        ScanService.mApi.extendSerialInit(mCurSerialNo, mBaudrate, 1, 1, 1, 1);

    }

    private void closeDevice(){
        //close power
        ScanService.mApi.gpioControl(mGpioPower,0,0);
        ScanService.mApi.extendSerialClose(mCurSerialNo);
    }

}
