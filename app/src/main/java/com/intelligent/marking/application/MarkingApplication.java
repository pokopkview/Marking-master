package com.intelligent.marking.application;

import android.app.Application;
import android.os.Build;
import android.posapi.PosApi;

import com.intelligent.marking.common.okgo.App;
import com.intelligent.marking.net.HttpInterceptor;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MarkingApplication extends Application {


    private static String mCurDev1 = "";

    static MarkingApplication instance = null;
    //PosSDK mSDK = null;
    static PosApi mPosApi = null;
    public MarkingApplication(){
        super.onCreate();
        instance = this;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        HttpInterceptor interceptor = new HttpInterceptor();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(interceptor)
                .build();
        OkHttpUtils.initClient(okHttpClient);

        mPosApi = PosApi.getInstance(this);
        //初始
        init();
    }


    public static void init(){
        //根据型号进行初始化mPosApi类
        if (Build.MODEL.contains("LTE")||android.os.Build.DISPLAY.contains("3508")||
                android.os.Build.DISPLAY.contains("403")||
                android.os.Build.DISPLAY.contains("35S09")) {
            mPosApi.initPosDev("ima35s09");
            setCurDevice("ima35s09");
        } else if(Build.MODEL.contains("5501")){
            mPosApi.initPosDev("ima35s12");
            setCurDevice("ima35s12");
        }else{
            mPosApi.initPosDev(PosApi.PRODUCT_MODEL_IMA80M01);
            setCurDevice(PosApi.PRODUCT_MODEL_IMA80M01);
        }

    }


    public static MarkingApplication getInstance(){
        if(instance==null){
            instance =new MarkingApplication();
        }
        return instance;
    }

    public String getCurDevice() {
        return mCurDev1;
    }

    public static  void setCurDevice(String mCurDev) {
        mCurDev1 = mCurDev;
    }

    //其他地方引用mPosApi变量
    public PosApi getPosApi(){
        return mPosApi;
    }
}
