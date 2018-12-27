package com.intelligent.marking.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.net.model.BaseModel;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordActivity extends BaseActivity {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.et_old_pwd)
    EditText etOldPwd;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.iv_new_pwd)
    ImageView ivNewPwd;
    @BindView(R.id.et_confirm_pwd)
    EditText etConfirmPwd;
    @BindView(R.id.iv_confirm_pwd)
    ImageView ivConfirmPwd;
    @BindView(R.id.tv_confirm_change)
    TextView tvConfirmChange;

    @OnClick(R.id.ll_left_container)
    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        ImageView back = new ImageView(this);
        back.setImageResource(R.mipmap.fanhui01);
        llLeftContainer.addView(back);
        tvHeaderTitle.setText("修改密码");
        ivNewPwd.setTag(true);
        ivConfirmPwd.setTag(true);
        ivNewPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((boolean)ivNewPwd.getTag()){
                    ivNewPwd.setImageResource(R.mipmap.check_pwd_blue);
                    ivNewPwd.setTag(false);
                    etNewPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    ivNewPwd.setImageResource(R.mipmap.hiden_pwd_blue);
                    ivNewPwd.setTag(true);
                    etNewPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        ivConfirmPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((boolean)ivConfirmPwd.getTag()){
                    ivConfirmPwd.setImageResource(R.mipmap.check_pwd_blue);
                    ivConfirmPwd.setTag(false);
                    etConfirmPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    ivConfirmPwd.setImageResource(R.mipmap.hiden_pwd_blue);
                    ivConfirmPwd.setTag(true);
                    etConfirmPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @OnClick(R.id.tv_confirm_change)
    public void confirmPwd(View view){
        //TODO 修改密码
        if(etNewPwd.getText().toString().equals(etConfirmPwd.getText().toString())){
            value.clear();
            value.put("oldpasswd",etOldPwd.getText().toString());
            value.put("passwd",etNewPwd.getText().toString());
            value.put("repasswd",etConfirmPwd.getText().toString());
            HttpPost(AppConst.CHANGEPWD,value,1);
        }else{
            showToast("两次新密码填入不一致");
        }
    }

    @Override
    public void getCallBack(String response, int flag) {
        Type type;
        switch (flag){
            case 1:
                type = new TypeToken<BaseModel<String>>(){}.getType();
                BaseModel<String> changeModel = new Gson().fromJson(response,type);
                showToast(changeModel.getInfo());
                onBackPressed();
                break;
        }
    }
}
