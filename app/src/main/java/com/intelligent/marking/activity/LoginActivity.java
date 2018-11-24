package com.intelligent.marking.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.adapter.SelectMoreAdapter;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.HospitalModel;
import com.intelligent.marking.widget.PopUpwindowUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    String JsonData;
    @BindView(R.id.login_tv_register)
    TextView loginTvRegister;
    @BindView(R.id.login_tv_position)
    TextView loginTvPosition;
    @BindView(R.id.ll_login_location)
    LinearLayout llLoginLocation;
    @BindView(R.id.ll_select_hist)
    LinearLayout llSelectHist;
    @BindView(R.id.ll_select_area)
    LinearLayout llSelectArea;
    @BindView(R.id.ll_select_depart)
    LinearLayout llSelectDepart;
    @BindView(R.id.ll_select_moreares)
    LinearLayout llSelectMoreares;
    @BindView(R.id.login_tv01)
    TextView loginTv01;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;

    @OnClick(R.id.iv_left)
    public void clickLeft(View view){
        //TODO 扫二维码
    }

    /**
     * 获取医院大区数据
     * @param view
     */
    @OnClick(R.id.ll_select_hist)
    public void selectHosp(View view){
        getHospiDate(25579,25580,25582);
    }

    @OnClick(R.id.ll_select_area)
    public void selectArea(View view){
        getAreaDate(2);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        ButterKnife.bind(this);
        initView();
    }

    private void getAreaDate(int hospiid){
        Map<String,Object> value = new HashMap<>();
        value.put("hospital_id",hospiid);
        HttpPost(AppConst.GETAREA,value,2);
    }

    private void getHospiDate(int proid,int cityid,int disid) {
        Map<String,Object> value = new HashMap<>();
        value.put("province_id",proid);
        value.put("city_id",cityid);
        value.put("district_id",disid);
        HttpPost(AppConst.GETHOSPITAL,value,1);
    }

    @Override
    public void getCallBack(String response, int flag) {
        switch (flag){
            case 1:
                //show dialog
                System.out.println(response);
                Type type = new TypeToken<BaseModel<List<HospitalModel>>>(){}.getType();
                BaseModel<List<HospitalModel>> model = new Gson().fromJson(response,type);
                List<String> objects = new ArrayList<>();
                for(HospitalModel hospitalModel : model.getData()){
                    objects.add(hospitalModel.getName());
                }
                PopupWindow popupWindow = PopUpwindowUtil.createPopUpWindow(R.layout.popupwindow_layout, this, objects, new SelectMoreAdapter.itemClickListener() {
                    @Override
                    public void itemclick(int pos, View view) {

                    }

                    @Override
                    public void footclick() {

                    }
                });
                int height = this.getResources().getDimensionPixelSize(R.dimen.y27);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(llSelectHist,Gravity.NO_GRAVITY,(int)llSelectHist.getX(),(int)llSelectHist.getY()+height);
            break;
            case 2:
                break;
        }
    }

    private void initView() {

    }

}
