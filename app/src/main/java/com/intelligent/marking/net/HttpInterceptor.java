package com.intelligent.marking.net;

import android.content.Context;
import android.util.Log;

import com.intelligent.marking.R;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpInterceptor implements Interceptor {

    private Context mContext;

    public void setmContext(Context context){
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request uest = chain.request();
        Request request = uest.newBuilder()
                .addHeader("uuid","yb_iCep6EBTWxKCW9sutpl2kWm6LNBThGEPMr2BxEUoS6qAaWX0rN3YPVv9zJhmo0mQ")
                .addHeader("Content-Type","application/json")
                .build();

        Response response = chain.proceed(request);
        System.out.println("response:"+response.toString());
        return response;
    }
}
