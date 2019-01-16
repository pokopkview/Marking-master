package com.intelligent.marking.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.Utils.PreferencesUtils;
import com.intelligent.marking.Utils.UtilsChange;
import com.intelligent.marking.adapter.SubAreaEmployeeAdapter;
import com.intelligent.marking.eventbus.ManagerLevelEvent;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.NurseInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubAreaEmployeeActivity extends BaseActivity implements SubAreaEmployeeAdapter.DeleteItemClick {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.rlv_employee)
    RecyclerView rlvEmployee;
    @BindView(R.id.iv_add_employee)
    ImageView ivAddEmployee;
    @BindView(R.id.rl_add_employee)
    RelativeLayout rlAddEmployee;


    SubAreaEmployeeAdapter adapter;
    List<NurseInfo> data;
    @BindView(R.id.header_title_root)
    RelativeLayout headerTitleRoot;
    @BindView(R.id.et_add_name)
    EditText etAddName;
    @BindView(R.id.et_add_no)
    EditText etAddNo;
    @BindView(R.id.ll_add_nurse)
    RelativeLayout llAddNurse;

    private int deletePos = -1;

    private int departid = -1;

    @OnClick(R.id.ll_left_container)
    public void back(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @OnClick(R.id.ll_right_container)
    public void save(View view){
        showProgress();
        System.out.println(etAddName.getText().toString()+"__"+etAddNo.getText().toString());
        if(etAddName.getText().toString().isEmpty() || etAddNo.getText().toString().isEmpty()){
            showToast("请填入正确数据");
            return;
        }
        value.clear();
        value.put("nurse_name",etAddName.getText().toString());
        value.put("nurse_no",etAddNo.getText().toString());
        value.put("subarea_id",subareaNameid);
        HttpPost(AppConst.SAVENURSE,value,2);
    }


    @OnClick(R.id.rl_add_employee)
    public void addEmployee(View view) {
        if(llAddNurse.getVisibility() == View.GONE) {
            llAddNurse.setVisibility(View.VISIBLE);
        }else{
            llAddNurse.setVisibility(View.GONE);
            etAddName.setText("");
            etAddNo.setText("");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_area_employee);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        ImageView back = new ImageView(this);
        back.setImageResource(R.mipmap.fanhui01);
        llLeftContainer.addView(back);
        TextView tv = new TextView(this);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(UtilsChange.px2sp(this,getResources().getDimensionPixelSize(R.dimen.x16)));
        tv.setText("保存");
        llRightContainer.addView(tv);
        setDate();
        initRvView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getDepart(ManagerLevelEvent messageEvent) {
        System.out.println("department_id:"+messageEvent.getDepartid());
        departid = messageEvent.getDepartid();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    private void initRvView() {
        rlvEmployee.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_item_bg));
        rlvEmployee.addItemDecoration(dividerItemDecoration);
        adapter = new SubAreaEmployeeAdapter(this, this);
    }

    private void setDate() {
        if (PreferencesUtils.getInt(this, PreferencesUtils.ROLE) == 4) {
            value.clear();
            value.put("", "");
            HttpPost(AppConst.GETNURSEINFO, value, 1);
        } else {
            value.clear();
            if(departNameid<1){
                value.put("department_id", departid);
            }else{
                value.put("department_id", departNameid);
            }
            HttpPost(AppConst.GETNURSEINFO, value, 1);
        }
        showProgress();
    }


    @Override
    public void getCallBack(String response, int flag) {
        Type type;
        switch (flag) {
            case 1:
                disMissPro();
                type = new TypeToken<BaseModel<List<NurseInfo>>>() {}.getType();
                BaseModel<List<NurseInfo>> baseModel = new Gson().fromJson(response, type);
                rlvEmployee.setAdapter(adapter);
                data = baseModel.getData();
                adapter.setDate(data);
                break;
            case 2:
                disMissPro();
                type = new TypeToken<BaseModel<NurseInfo>>() {}.getType();
                BaseModel<NurseInfo> baseModel1 = new Gson().fromJson(response, type);
                adapter.addItemData(baseModel1.getData());
                llAddNurse.setVisibility(View.GONE);
                etAddNo.setText("");
                etAddName.setText("");
                break;
            case 3:
                disMissPro();
                adapter.removeItem(deletePos);
                break;
        }

    }

    @Override
    public void clickitem(int position) {
        value.clear();
        deletePos = position;
        value.put("department_id",departNameid);
        value.put("nurse_id",adapter.getListDate().get(position).getNurse_id());
        HttpPost(AppConst.REMOVENURSE,value,3);
    }
}
