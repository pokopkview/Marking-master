package com.intelligent.marking.activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.Utils.TimePickerUtils;
import com.intelligent.marking.Utils.UtilsChange;
import com.intelligent.marking.common.okgo.App;
import com.intelligent.marking.eventbus.MainActivityEvent;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.InsertDuctBackModel;
import com.intelligent.marking.net.model.NurseInfo;
import com.intelligent.marking.net.model.TreatInfoModel;
import com.intelligent.marking.widget.PopUpwindowUtil;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DuctinfoEditActivity extends BaseActivity {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.rl_item_first)
    TextView rlItemFirst;
    @BindView(R.id.et_day)
    EditText etDay;
    @BindView(R.id.rl_item_three)
    TextView rlItemThree;
    @BindView(R.id.et_hour)
    EditText etHour;
    @BindView(R.id.tv_show_day)
    TextView tvShowDay;
    @BindView(R.id.ll_select_day)
    LinearLayout llSelectDay;
    @BindView(R.id.tv_show_time)
    TextView tvShowTime;
    @BindView(R.id.ll_select_time)
    LinearLayout llSelectTime;
    @BindView(R.id.rl_item_out)
    TextView rlItemOut;
    @BindView(R.id.et_outside)
    EditText etOutside;
    @BindView(R.id.unit_cm)
    TextView unitCm;
    @BindView(R.id.rl_item_in)
    TextView rlItemIn;
    @BindView(R.id.et_inside)
    EditText etInside;
    //    @BindView(R.id.edit_manager_name)
//    TextView editManagerName;
//    @BindView(R.id.manager_no)
//    TextView managerNo;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.root_view)
    LinearLayout rootView;
    @BindView(R.id.ll_bottom_comfirm_btn)
    LinearLayout llBottomComfirmBtn;

    int ducttypeid;
    int ductid;
    String ductName;
    int bedmainid;
    @BindView(R.id.header_title_root)
    RelativeLayout headerTitleRoot;
    @BindView(R.id.et_search_nurse)
    EditText etSearchNurse;
    private TimePickerView pickerView;


    PopupWindow popupWindow;
    ListView view;
    SearchListAdapter searchListAdapter;
    boolean isSelect = false;

    @OnClick(R.id.ll_left_container)
    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.ll_bottom_comfirm_btn)
    public void confirm(View view) {
        //TODO
        value.clear();
        value.put("subarea_id", subareaNameid);
        value.put("course_id", bedmainid);
        value.put("duct_id", ductid);
        value.put("duct_attr_id", ducttypeid);

        if(!TextUtils.isEmpty(etDay.getText().toString())) {
            value.put("keep_day", Integer.parseInt(etDay.getText().toString()));
        }else{
            showToast("请填入保留天数");
            return;
        }
        if(!TextUtils.isEmpty(etHour.getText().toString())) {
            value.put("keep_hour", Integer.parseInt(etHour.getText().toString()));
        }else{
            value.put("keep_hour", 0);
        }
        if(!TextUtils.isEmpty(tvShowDay.getText().toString())) {
            value.put("insert_date", tvShowDay.getText());
        }else{
            showToast("请选择日期");
            return;
        }
        if(!TextUtils.isEmpty(tvShowTime.getText().toString())) {
            value.put("insert_time", tvShowTime.getText());
        }else{
            showToast("请选择时间");
            return;
        }
        value.put("outside", Integer.parseInt(TextUtils.isEmpty(etOutside.getText().toString())?"0":etOutside.getText().toString()));
        value.put("inside", Integer.parseInt(TextUtils.isEmpty(etInside.getText().toString())?"0":etInside.getText().toString()));
        if(etSearchNurse.getTag()!=null) {
            value.put("nurse_id", (int) etSearchNurse.getTag());
        }else{
            showToast("请填入操作人");
            return;
        }

        value.put("operate_place", hospitalName + areaName + departName);
        System.out.println(new Gson().toJson(value));
        HttpPost(AppConst.ADDTREAT, value, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ductinfo_edit);
        ButterKnife.bind(this);
        ImageView back = new ImageView(this);
        back.setImageResource(R.mipmap.fanhui01);
        tvLocation.setText(hospitalName + areaName + departName);
        ducttypeid = getIntent().getIntExtra("ducttypeid", -1);
        ductid = getIntent().getIntExtra("ductid", -1);
        ductName = getIntent().getStringExtra("ductname");
        bedmainid = getIntent().getIntExtra("bedmainid", -1);
        llLeftContainer.addView(back);
        tvHeaderTitle.setText(ductName);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = sdf.format(new Date(System.currentTimeMillis()));
        tvShowDay.setText(date.split(" ")[0]);
        tvShowTime.setText(date.split(" ")[1]);

        etSearchNurse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                System.out.println("beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("onTextChanged"+isSelect);
                if(isSelect){
                    isSelect = false;
                    return;
                }
                if(s.toString().isEmpty()){
                    return;
                }
                value.clear();
                value.put("department_id",departNameid);
                value.put("nurse_name",s.toString());
                System.out.println(new Gson().toJson(value));
                HttpPost(AppConst.SERCHMANAGER,value,2);
            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("afterTextChanged");
//                hidenkeyboard();

            }
        });
    }

    private void hidenkeyboard(){
        InputMethodManager manager = ((InputMethodManager)DuctinfoEditActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE));
        if (manager != null) {
            manager.hideSoftInputFromWindow(etSearchNurse.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @OnClick(R.id.ll_select_day)
    public void getDate(View view) {
        hidenkeyboard();
        if(PopUpwindowUtil.popupWindow!=null){
            PopUpwindowUtil.popupWindow.dismiss();
        }
        TimePickerUtils.showTimePicker(this, new TimePickerUtils.timeBack() {
            @Override
            public void getTime(String str) {
                System.out.println(str);
                tvShowDay.setText(str);
            }
        }, 2);

    }


    @OnClick(R.id.ll_select_time)
    public void getTimeDaate(View view) {
        hidenkeyboard();
        if(PopUpwindowUtil.popupWindow!=null){
            PopUpwindowUtil.popupWindow.dismiss();
        }
        TimePickerUtils.showTimePicker(this, new TimePickerUtils.timeBack() {
            @Override
            public void getTime(String str) {
                System.out.println(str);
                tvShowTime.setText(str);
            }
        }, 1);
    }

    @Override
    public void getCallBack(String response, int flag) {
        Type type;
        switch (flag){
            case 1:
                type = new TypeToken<BaseModel<TreatInfoModel>>(){}.getType();
                BaseModel<TreatInfoModel> msg = new Gson().fromJson(response,type);
                showToast(msg.getInfo());
                EventBus.getDefault().post(new MainActivityEvent(msg.getData()));
                Intent printIntent = new Intent(DuctinfoEditActivity.this, PrintPreviewActivity.class);
                printIntent.putExtra("ductname",ductName);
                printIntent.putExtra("treatid",msg.getData());
                printIntent.putExtra("nursename",etSearchNurse.getText().toString());
                startActivity(printIntent);
                break;
            case 2:
                type = new TypeToken<BaseModel<List<NurseInfo>>>(){}.getType();
                BaseModel<List<NurseInfo>> baseModel = new Gson().fromJson(response,type);
                if(popupWindow==null) {
                    popupWindow = new PopupWindow(getResources().getDimensionPixelSize(R.dimen.x241), ViewGroup.LayoutParams.WRAP_CONTENT);
                    view = new ListView(this);
                    searchListAdapter = new SearchListAdapter(this, baseModel.getData());
                    view.setAdapter(searchListAdapter);
                    view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        baseModel.getData().get(position);
                            isSelect = true;
                            etSearchNurse.setText(baseModel.getData().get(position).getNurse_name());
                            etSearchNurse.setTag(baseModel.getData().get(position).getNurse_id());
                            popupWindow.dismiss();
                        }
                    });
//                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//                        @Override
//                        public void onDismiss() {
//                            isSelect = true;
//                            popupWindow.dismiss();
//                        }
//                    });
                    System.out.println(baseModel.getData().isEmpty());
                    if(!baseModel.getData().isEmpty()) {
                        hidenkeyboard();
                    }
                        popupWindow.setContentView(view);
                        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
                        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//                    }
                }else{
                    if(!baseModel.getData().isEmpty()) {
                        hidenkeyboard();
                    }
                    searchListAdapter.setData(baseModel.getData());
                }
//                popupWindow.showAtLocation(etSearchNurse,Gravity.NO_GRAVITY,getResources().getDimensionPixelSize(R.dimen.x120),(int)etSearchNurse.getX());
                popupWindow.showAsDropDown(etSearchNurse);
                break;

        }
    }

    class SearchListAdapter extends BaseAdapter{

        List<NurseInfo> data;
        Context mContext;

        SearchListAdapter(Context context, List<NurseInfo> value){
            mContext = context;
            data = value;
        }

        public void setData(List<NurseInfo> value){
            data.clear();
            data.addAll(value);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return data.get(position).getNurse_id();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tvName = new TextView(mContext);
            tvName.setText(data.get(position).getNurse_name());
            tvName.setTextSize(UtilsChange.px2sp(mContext,mContext.getResources().getDimensionPixelSize(R.dimen.x15)));
            tvName.setPadding(0,18,0,18);
            tvName.setBackgroundColor(getResources().getColor(R.color.gray01));
            tvName.setTextColor(Color.BLACK);
//            parent.addView(tvName);
            return tvName;
        }
    }
}
