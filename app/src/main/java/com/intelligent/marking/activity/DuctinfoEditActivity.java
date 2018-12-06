package com.intelligent.marking.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.R;
import com.intelligent.marking.set.TimerPicer;

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
        TimerPicer.showTimePic(DuctinfoEditActivity.this, rootView);
    }

    @Override
    public void getCallBack(String response, int flag) {

    }
}
