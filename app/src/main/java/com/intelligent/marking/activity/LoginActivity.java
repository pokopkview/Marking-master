package com.intelligent.marking.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.intelligent.marking.R;
import com.intelligent.marking.common.app.CommonIp;
import com.intelligent.marking.bean.ProvinceBean;
import com.intelligent.marking.common.okgo.bean.ResponseBean;
import com.intelligent.marking.common.okgo.callbck.JsonCallback;
import com.intelligent.marking.common.okgo.net.OkUtil;
import com.intelligent.marking.common.utils.TargetActivityUtils;
import com.intelligent.marking.set.JsonDataActivity01;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private TextView login_tv_position;//设置省市区
    private TextView login_tv_register;//注册
    private TextView login_tv01;// 登录
    private static final String TAG = LoginActivity.class.getSimpleName();

    String JsonData ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_login_new);

        getRegion();
        initView();
        getDisteny();
    }
    private void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
    }


    private void getDisteny() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidthPx = dm.widthPixels;
        int screenHeighPx = dm.heightPixels;
        int virtualKeyHeight = 0;
        Resources res = getResources();
        int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0)
            virtualKeyHeight = res.getDimensionPixelSize(resourceId);
        float density = getResources().getDisplayMetrics().density;
        int screenWidthDp = (int) (screenWidthPx / density + 0.5f);
        int screenHeighDp = (int) ((screenHeighPx + virtualKeyHeight) / density + 0.5f);
        Log.i(TAG, "屏幕宽:" + screenWidthPx + "px,屏幕高:" + screenHeighPx + "px,虚拟键高:" + virtualKeyHeight + "px");
        Log.i(TAG, "屏幕宽:" + screenWidthDp + "dp,屏幕高:" + screenHeighDp + "dp,density:" + density);
    }

    private void initView() {
        login_tv_position = (TextView)findViewById(R.id.login_tv_position);
        login_tv_register = (TextView)findViewById(R.id.login_tv_register);
        login_tv01 = (TextView)findViewById(R.id.login_tv01);



        login_tv_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonData  = new JsonDataActivity01().getPosition(LoginActivity.this,login_tv_position);

//                login_tv_position.setText(JsonData);
            }
        });

        login_tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TargetActivityUtils.intent(LoginActivity.this, RegisterActivity.class,null,false);
            }
        });

        login_tv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TargetActivityUtils.intent(LoginActivity.this, MainActivity.class,null,false);
            }
        });
    }



    //                POST /account/updateUserHealthInfo
    private void getRegion() {
        Map map = new HashMap();
        map.put("id","0");
        // mTv.setText(response.body().Result.getName() + "-" + response.body().Result.getHabby());

        OkUtil.getRequets(CommonIp.getProvince, this, map, new JsonCallback<ResponseBean<ProvinceBean>>() {
            /**
             * 对返回数据进行操作的回调， UI线程
             * @param response
             */
            @Override
            public void onSuccess(Response<ResponseBean<ProvinceBean>> response) {
//                Log.e("-------",response.body().Result.getId() + "-" + response.body().Result.getName());
            }
        });
    }
}
