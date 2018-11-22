package com.intelligent.marking.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.intelligent.marking.R;
import com.intelligent.marking.common.utils.TargetActivityUtils;

/**
 * 欢迎引导页面
 */
public class WelcomeInterfaceActivity  extends Activity implements Animation.AnimationListener{
    private ImageView iv_wi_image;
    private ConstraintLayout clBg;

    private Animation atAlpha;//开端动画
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_interface);
        initView();
    }

    private void initView() {
        iv_wi_image = (ImageView)findViewById(R.id.iv_wi_image);
        clBg = (ConstraintLayout)findViewById(R.id.cl_bg);

        atAlpha = AnimationUtils.loadAnimation(this, R.anim.zoom);//初始化动画
        atAlpha.setFillEnabled(true); // 启动Fill保持
        atAlpha.setFillAfter(true); // 设置动画的最后一帧是保持在View上面
        clBg.setAnimation(atAlpha);//给img设置动画
        atAlpha.setAnimationListener(this); // 为动画设置监听
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        skipActivity();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

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
        if (atAlpha != null) {
            atAlpha.cancel();
        }
    }
}
