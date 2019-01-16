package com.intelligent.marking.net;

import android.content.Context;
import android.util.Log;

import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.Utils.PreferencesUtils;
import com.intelligent.marking.application.MarkingApplication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.InternalCache;
import okio.Sink;

public class HttpInterceptor implements Interceptor {

    private Context mContext;

    public void setmContext(Context context){
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request uest = chain.request();
        String uiid = PreferencesUtils.getString(MarkingApplication.getInstance().getApplicationContext(),PreferencesUtils.UUID);
        System.out.println(uiid);
        Response response;
        if(null != uiid){
            Request request = uest.newBuilder()
                    .addHeader("uuid",uiid)
                    .addHeader("Content-Type","application/json")
                    .build();
            response = chain.proceed(request);
        }else{
            response = chain.proceed(uest);
        }
        return response;
    }
}
