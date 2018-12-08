package com.intelligent.marking.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.view.TimePickerView;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.R;
import com.intelligent.marking.Utils.TimePickerUtils;

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
    @BindView(R.id.edit_manager_name)
    TextView editManagerName;
    @BindView(R.id.manager_no)
    TextView managerNo;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.root_view)
    LinearLayout rootView;
    @BindView(R.id.ll_bottom_comfirm_btn)
    LinearLayout llBottomComfirmBtn;
    private TimePickerView pickerView;
    ;
//    @BindView(R.id.btnCancel)
//    Button btnCancel;
//    @BindView(R.id.tvTitle)
//    TextView tvTitle;
//    @BindView(R.id.btnSubmit)
//    Button btnSubmit;
//    @BindView(R.id.rv_topbar)
//    RelativeLayout rvTopbar;
//    @BindView(R.id.year)
//    WheelView year;
//    @BindView(R.id.month)
//    WheelView month;
//    @BindView(R.id.day)
//    WheelView day;
//    @BindView(R.id.hour)
//    WheelView hour;
//    @BindView(R.id.min)
//    WheelView min;
//    @BindView(R.id.second)
//    WheelView second;
//    @BindView(R.id.timepicker)
//    LinearLayout timepicker;

    @OnClick(R.id.ll_left_container)
    public void back(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.ll_bottom_comfirm_btn)
    public void confirm(View view){
        //TODO
        startActivity(new Intent(DuctinfoEditActivity.this,PrintPreviewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ductinfo_edit);
        ButterKnife.bind(this);
        ImageView back = new ImageView(this);
        back.setImageResource(R.mipmap.fanhui01);
        llLeftContainer.addView(back);
        tvHeaderTitle.setText("PICC");
    }

    @OnClick(R.id.ll_select_day)
    public void getDate(View view) {
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

    }
}
