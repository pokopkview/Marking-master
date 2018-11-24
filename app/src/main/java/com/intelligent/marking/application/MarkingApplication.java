package com.intelligent.marking.application;

import android.app.Application;

import com.intelligent.marking.net.HttpInterceptor;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MarkingApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        HttpInterceptor interceptor = new HttpInterceptor();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .build();
        OkHttpUtils.initClient(okHttpClient);

    }
}
