package com.intelligent.marking;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.intelligent.marking.Utils.ToastUtil;
import com.intelligent.marking.common.utils.ClickUtils;
import com.intelligent.marking.common.utils.StringUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;


/**
 * Created by Administrator on 2017/4/26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    protected final String TAG = this.getClass().getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
    }

    protected void HttpPost(String url, Map<String,Object> value, final int flag){
        showProgress();
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
                        disMissPro();
                        getCallBack(response,flag);
                    }
                });
    }

    public abstract void getCallBack(String response,int flag);


    protected void showProgress(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage("数据加载中...");
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
