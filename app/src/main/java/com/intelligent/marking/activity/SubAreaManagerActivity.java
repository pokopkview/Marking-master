package com.intelligent.marking.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.R;
import com.intelligent.marking.Utils.UtilsChange;
import com.intelligent.marking.adapter.SubaAreaAdapter;
import com.intelligent.marking.net.model.SubAreModel;
import com.intelligent.marking.widget.PopUpwindowUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubAreaManagerActivity extends BaseActivity {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;

    List<SubAreModel> modelList = new ArrayList<>();
    @BindView(R.id.lv_subarea)
    RecyclerView lvSubarea;

    @OnClick(R.id.ll_left_container)
    public void back(View view){
        onBackPressed();
    }

    @OnClick(R.id.ll_right_container)
    public void saveParams(View view){
        //TODO 保存参数
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_area_manager);
        ButterKnife.bind(this);
        ImageView backimg = new ImageView(this);
        backimg.setImageResource(R.mipmap.fanhui01);
        llLeftContainer.addView(backimg);
        TextView textView = new TextView(this);
        textView.setText("保存");
        textView.setTextSize(UtilsChange.px2sp(this,(int)getResources().getDimensionPixelSize(R.dimen.x16)));
        textView.setTextColor(getResources().getColor(R.color.white));
        llRightContainer.addView(textView);
        modelList = (List<SubAreModel>) getIntent().getSerializableExtra("subarealist");
        System.out.println(modelList.toString());
        lvSubarea.setLayoutManager(new LinearLayoutManager(this));
        SubaAreaAdapter adapter = new SubaAreaAdapter(this,modelList);
        adapter.setListener(new SubaAreaAdapter.deleteItemClick() {
            @Override
            public void deleteClick(int position) {

                PopupWindow popupWindow = PopUpwindowUtil.createPopUpWindowDialogStyle(SubAreaManagerActivity.this
                        , "确定移除", "hospital" + modelList.get(position).getSubarea_name(),
                        "移除", "取消", new PopUpwindowUtil.dialogClickListener() {
                            @Override
                            public void confirm() {
                                modelList.remove(position);
                                adapter.notifyDataSetChanged();
                                PopUpwindowUtil.popupWindow.dismiss();
                            }

                            @Override
                            public void cancle() {

                            }
                        });
                WindowManager.LayoutParams lp=((Activity)SubAreaManagerActivity.this).getWindow().getAttributes();
                lp.alpha=0.4f;
                ((Activity)SubAreaManagerActivity.this).getWindow().setAttributes(lp);
                popupWindow.showAtLocation(lvSubarea,Gravity.CENTER,0,0);

            }
        });
        lvSubarea.setAdapter(adapter);

    }

    @Override
    public void getCallBack(String response, int flag) {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
