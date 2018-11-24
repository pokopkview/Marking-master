package com.intelligent.marking.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.R;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    @Override
    public void getCallBack(String response, int flag) {

    }

    private void initView() {
    }
}
