package com.intelligent.marking.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrintPreviewActivity extends BaseActivity {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.iv_top)
    ImageView ivTop;
    @BindView(R.id.def_text_bed_no)
    TextView defTextBedNo;
    @BindView(R.id.tv_text_bed_no)
    TextView tvTextBedNo;
    @BindView(R.id.def_text_bed_name)
    TextView defTextBedName;
    @BindView(R.id.tv_text_bed_name)
    TextView tvTextBedName;
    @BindView(R.id.def_text_bed_sex)
    TextView defTextBedSex;
    @BindView(R.id.tv_text_bed_sex)
    TextView tvTextBedSex;
    @BindView(R.id.def_text_bed_age)
    TextView defTextBedAge;
    @BindView(R.id.tv_text_bed_age)
    TextView tvTextBedAge;
    @BindView(R.id.tv_duct_type_name)
    TextView tvDuctTypeName;
    @BindView(R.id.iv_printq)
    ImageView ivPrintq;
    @BindView(R.id.rl_bitmap_container)
    RelativeLayout rlBitmapContainer;
    @BindView(R.id.tv_time_create)
    TextView tvTimeCreate;
    @BindView(R.id.def_manager)
    TextView defManager;
    @BindView(R.id.tv_manager_name)
    TextView tvManagerName;
    @BindView(R.id.rl_print_btn)
    RelativeLayout rlPrintBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_preview);
        ButterKnife.bind(this);
        ImageView back = new ImageView(this);
        back.setImageResource(R.mipmap.fanhui01);
        llLeftContainer.addView(back);
        tvHeaderTitle.setText("打印预览");
    }

    @OnClick(R.id.rl_print_btn)
    public void print(View view){
        //TODO
    }


    @Override
    public void getCallBack(String response, int flag) {

    }
}
