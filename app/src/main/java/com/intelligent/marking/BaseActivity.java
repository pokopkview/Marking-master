package com.intelligent.marking;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.intelligent.marking.Utils.PreferencesUtils;
import com.intelligent.marking.Utils.ToastUtil;
import com.intelligent.marking.net.CacheUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;


/**
 * Created by Administrator on 2017/4/26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    protected final String TAG = this.getClass().getSimpleName();
    protected String hospitalName = "";
    protected String areaName = "";
    protected String departName = "";
    protected String subareaName = "";

    protected int hospitalNameid = -1;
    protected int areaNameid = -1;
    protected int departNameid = -1;
    protected int subareaNameid = -1;

    protected Map<String,Object> value = new HashMap<>();

    private boolean canCache = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setStatic();
    }

    private void setStatic() {
        hospitalName = PreferencesUtils.getString(this,PreferencesUtils.HOSPITALNAME);
        areaName = PreferencesUtils.getString(this,PreferencesUtils.AREANAME);
        departName = PreferencesUtils.getString(this,PreferencesUtils.DEPARTNAME);
        subareaName = PreferencesUtils.getString(this,PreferencesUtils.SUBAREANAME);
//        loginTvPosition.setText(PreferencesUtils.getString(this,PreferencesUtils.LOCATION);

        hospitalNameid = PreferencesUtils.getInt(this,PreferencesUtils.HOSPITALNAMEID,-1);
        areaNameid = PreferencesUtils.getInt(this,PreferencesUtils.AREANAMEID,-1);
        departNameid = PreferencesUtils.getInt(this,PreferencesUtils.DEPARTNAMEID,-1);
        subareaNameid = PreferencesUtils.getInt(this,PreferencesUtils.SUBAREANAMEID,-1);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void hideStatusBar() {
//        WindowManager.LayoutParams attrs = getWindow().getAttributes();
////        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN|;
//        getWindow().setAttributes(attrs);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION  //该参数指布局能延伸到navigationbar，我们场景中不应加这个参数
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT); //设置navigationbar颜色为透明
        }
    }

    protected void HttpPost(String url, Map<String,Object> value, final int flag){
//        showProgress();
        System.out.println("HTTP--request:"+new Gson().toJson(value));
        if(CacheUtils.getInstance().containKey(url)){
            getCallBack(CacheUtils.getInstance().getKey(url),flag);
            return;
        }

        if(CacheUtils.getInstance().containURL(url)){
            canCache = true;
        }

        OkHttpUtils
                .postString()
                .url(url)
                .content(new Gson().toJson(value))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        disMissPro();
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        disMissPro();
                        System.out.println("HTTP--response:"+response);
                        if(canCache){
                            CacheUtils.getInstance().put(url,response);
                            canCache = false;
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int i = (int) jsonObject.opt("status");
                            if(i == 0){
                                showToast((String) jsonObject.opt("info"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        getCallBack(response,flag);
                    }
                });
    }

    public abstract void getCallBack(String response,int flag);


    protected void showProgress(){
        showProgress("数据加载中...");
    }

    protected void showProgress(String tips){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage(tips);
        progressDialog.show();
    }
    protected void disMissPro(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    protected void showToast(String str){
        ToastUtil.getInstance(this).show(str);
    }
}
