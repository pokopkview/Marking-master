package com.intelligent.marking.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.common.utils.TargetActivityUtils;
import com.intelligent.marking.net.model.LocationInfoModel;

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
        initView();
        Map<String,Object> value = new HashMap<>();
        value.put("id",0);
        HttpPost(AppConst.GETPROVINCE,value,1);
    }

    @Override
    public void getCallBack(String response, int flag) {
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
            skipActivity();
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
    }
}
