package com.intelligent.marking.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.Utils.PreferencesUtils;
import com.intelligent.marking.common.utils.TargetActivityUtils;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.LocationInfoModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 欢迎引导页面
 */
public class WelcomeInterfaceActivity  extends BaseActivity{
//    private ImageView iv_wi_image;
//    private ConstraintLayout clBg;
//
//    private Animation atAlpha;//开端动画

    private int count = 0;
    private int count1 = 0;
    private int count2 = 0;
    private int i = 0;

    List<LocationInfoModel> model = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_interface);
        System.out.println("TIME:"+PreferencesUtils.getLong(this,PreferencesUtils.TIME,1l)
        +"_PWD:"+PreferencesUtils.getString(this,PreferencesUtils.PWD));
        if(PreferencesUtils.getLong(this,PreferencesUtils.TIME,1l)!=1l
                &&System.currentTimeMillis()-PreferencesUtils.getLong(this,PreferencesUtils.TIME,1l)<PreferencesUtils.MOUNTH
                &&PreferencesUtils.getString(this,PreferencesUtils.PWD)!=null){
            if(ping()){
                autoLogin();
            }else{
                showProgress("等待网络加载...");
                countHandler.sendEmptyMessageDelayed(2,1000);
            }
        }else{
            initView();
        }
//        Map<String,Object> value = new HashMap<>();
//        value.put("id",0);
//        HttpPost(AppConst.GETPROVINCE,value,1);
    }

    private void autoLogin(){
        value.clear();
        value.put("hospital_id", PreferencesUtils.getInt(this,PreferencesUtils.HOSPITALNAMEID));
        value.put("area_id", PreferencesUtils.getInt(this,PreferencesUtils.AREANAMEID));
        value.put("department_id", PreferencesUtils.getInt(this,PreferencesUtils.DEPARTNAMEID));
        value.put("subarea_id", PreferencesUtils.getInt(this,PreferencesUtils.SUBAREANAMEID));
        String str = PreferencesUtils.getString(this,PreferencesUtils.PWD);
        String pwd = new String(Base64.decode(str.getBytes(),Base64.DEFAULT));
        value.put("passwd",Integer.parseInt(pwd));
        HttpPost(AppConst.LOGIN, value, 15);
    }

    @Override
    public void getCallBack(String response, int flag) {
        disMissPro();
        Type type;
        switch (flag){
            case 15:
                type = new TypeToken<BaseModel<List<String>>>() {
                }.getType();
                BaseModel<List<String>> loginmodels = new Gson().fromJson(response, type);
                if (loginmodels.getStatus() == 0) {
                    showToast(loginmodels.getInfo());
                } else {
                    switch (PreferencesUtils.getInt(this,PreferencesUtils.ROLE)){
                        case 1:
                        case 2:
                        case 3:
                            startActivity(new Intent(WelcomeInterfaceActivity.this, HospoitalManagerAvtivity.class));
                            break;
                        case 4:
                            startActivity(new Intent(WelcomeInterfaceActivity.this, MainActivity.class));
                            break;
                    }
                    finish();
                }
                break;
        }
//        Type type = new TypeToken<BaseModel<List<LocationInfoModel>>>() {
//        }.getType();
//        switch (flag){
//            case 1:
//                System.out.println(response);
//                BaseModel<List<LocationInfoModel>> base = new Gson().fromJson(response,type);
//                model = base.getData();
//                for(LocationInfoModel areaJsonModel : model){
//                    Map<String,Object> mps = new HashMap<>();
//                    mps.put("id",areaJsonModel.getId());
//                    HttpPost(AppConst.GETPROVINCE,mps,2);
//                }
//                break;
//            case 2:
//                System.out.println(response);
//                Type type2 = new TypeToken<BaseModel<List<LocationInfoModel.CityBean>>>() {
//                }.getType();
//                BaseModel<List<LocationInfoModel.CityBean>> base1 = new Gson().fromJson(response,type2);
//                List<LocationInfoModel.CityBean> models = base1.getData();
//                System.out.println(count+"-models:"+models.toString());
//                model.get(count).setCity(models);
//                if(count==33) {
//                    for(i = 0;i<34;i++) {
//                        for (LocationInfoModel.CityBean areaJsonModel : model.get(i).getCity()) {
//                            Map<String, Object> mps = new HashMap<>();
//                            mps.put("id", areaJsonModel.getId());
//                            HttpPost(AppConst.GETPROVINCE, mps, 3);
//                        }
//                    }
//                }
//                count++;
//                break;
//            case 3:
//                System.out.println("count:"+count1);
//                Type type3 = new TypeToken<BaseModel<List<LocationInfoModel.CityBean.AreaBean>>>() {
//                }.getType();
//                BaseModel<List<LocationInfoModel.CityBean.AreaBean>> base2 = new Gson().fromJson(response,type3);
//                List<LocationInfoModel.CityBean.AreaBean> models2 = base2.getData();
//                if(count1==34){
//                    String json = new Gson().toJson(model);
//                    System.out.println(json);
//                    return;
//                }
//                if(model.get(count1).getCity()!=null) {
//                    model.get(count1).getCity().get(count2).setArea(models2);
//                }
//                count1++;
//                count2++;
//                if(base2.getCount() == count2){
//                    count2 = 0;
//                }
//                break;
//        }
    }
    Handler countHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    skipActivity();
                    break;
                case 2:
                    if(ping()) {
                        autoLogin();
                    }else{
                        countHandler.sendEmptyMessageDelayed(2,1000);
                    }
                    break;
            }

        }
    };

    private void initView() {
        countHandler.sendEmptyMessageDelayed(1,2500);
//        iv_wi_image = (ImageView)findViewById(R.id.iv_wi_image);
//        clBg = (ConstraintLayout)findViewById(R.id.cl_bg);
//
//        atAlpha = AnimationUtils.loadAnimation(this, R.anim.zoom);//初始化动画
//        atAlpha.setFillEnabled(true); // 启动Fill保持
//        atAlpha.setFillAfter(true); // 设置动画的最后一帧是保持在View上面
//        clBg.setAnimation(atAlpha);//给img设置动画
//        atAlpha.setAnimationListener(this); // 为动画设置监听


    }


    private void skipActivity() {
        TargetActivityUtils.intent(WelcomeInterfaceActivity.this,LoginActivity.class,null,true);

//        //判断是够有权限
//        rxPermissions.request(
//                Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE //读写文件权限
//        ).subscribe(granted -> {
//            if (granted) {
//
//
//
////                startActivity();
//            } else {
//                showToast(R.string.common_permission);
//                finish();
//            }
//        });

//        if(1==1){
////            jumpActivityAndFinish(ARouterConstant.S_VOICE_ACTIVITY);
//            startActivityAndFinish(TestActivity.class);
//            return;
//        }

//        if (TextUtils.isEmpty(AppContext.getInstance().getToken()) || TextUtils.isEmpty(AppContext.getInstance().getProperty(Constant.USER_ACCOUNTID))) {
//            jumpActivityAndFinish(ARouterConstant.LOGIN_ACTIVITY2);
//        } else {
//            jumpActivityAndFinish(ARouterConstant.HOME_PAGE_ACTIVITY);
//        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 在欢迎界面屏蔽BACK键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countHandler.removeMessages(1);
        countHandler.removeMessages(2);
    }

    /* @author suncat
     * @category 判断是否有外网连接（普通方法不能判断外网的网络是否连接，比如连接上局域网）
     * @return
     */
    public static final boolean ping() {

        String result = null;
        try {
            String ip = "www.baidu.com";// ping 的地址，可以换成任何一种可靠的外网
            Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);// ping网址3次
            // 读取ping的内容，可以不加
            InputStream input = p.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            StringBuffer stringBuffer = new StringBuffer();
            String content = "";
            while ((content = in.readLine()) != null) {
                stringBuffer.append(content);
            }
            Log.d("------ping-----", "result content : " + stringBuffer.toString());
            // ping的状态
            int status = p.waitFor();
            if (status == 0) {
                result = "success";
                System.out.println("------ping-----true");
                return true;
            } else {
                result = "failed";
            }
        } catch (IOException e) {
            result = "IOException";
        } catch (InterruptedException e) {
            result = "InterruptedException";
        } finally {
            Log.d("----result---", "result = " + result);
        }
        System.out.println("------ping-----false");
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void getErrorInfo(String str, int flag) {
        super.getErrorInfo(str, flag);
        switch (flag){
            case 15:
                initView();
                break;
        }
    }
}
