package com.intelligent.marking.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.adapter.DuctSelectAdapter;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.DuctListInfo;
import com.intelligent.marking.widget.PopUpwindowUtil;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectDuctActivity extends BaseActivity {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.rl_duct_select)
    RecyclerView rlDuctSelect;

    List<Object> data;

    DuctSelectAdapter adapter;
    int bedmainid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_duct);
        ButterKnife.bind(this);
        bedmainid = getIntent().getIntExtra("bedmainid",-1);
        value.clear();
        value.put("subarea_id",subareaNameid);
        showProgress();
        HttpPost(AppConst.DUCTINDEX,value,1);

    }

    @Override
    public void getCallBack(String response, int flag) {
        disMissPro();
        Type type;
        switch (flag){
            case 1:
                type = new TypeToken<BaseModel<List<DuctListInfo>>>(){}.getType();
                BaseModel<List<DuctListInfo>> baseModel = new Gson().fromJson(response,type);

                adapter = new DuctSelectAdapter(this,baseModel.getData());
                adapter.setListener(new DuctSelectAdapter.ItemClick() {
                    @Override
                    public void itemClick(int position) {
                        //TODO
                        if(baseModel.getData().get(position).getDuct_cat().isEmpty()) {
                            Intent itemIntent = new Intent(SelectDuctActivity.this, DuctinfoEditActivity.class);
                            itemIntent.putExtra("ducttypeid",0);
                            itemIntent.putExtra("bedmainid",bedmainid);
                            itemIntent.putExtra("ductid",baseModel.getData().get(position).getDuct_id());
                            itemIntent.putExtra("ductname",baseModel.getData().get(position).getDuct_name());

                            itemIntent.putExtra("outside",baseModel.getData().get(position).getOutside());
                            itemIntent.putExtra("inside",baseModel.getData().get(position).getInside());
                            itemIntent.putExtra("keep_day",baseModel.getData().get(position).getKeep_day());
                            itemIntent.putExtra("keep_hour",baseModel.getData().get(position).getKeep_hour());

                            startActivity(itemIntent);
                        }else {
                            PopUpwindowUtil.createPopUpWindowDuctInfo(SelectDuctActivity.this, baseModel.getData().get(position).getDuct_name() + "", null,baseModel.getData().get(position).getDuct_cat(),new PopUpwindowUtil.dialogClickDuctListener() {


                                @Override
                                public void itemClick(int pos, int id) {
                                    Intent itemIntent = new Intent(SelectDuctActivity.this, DuctinfoEditActivity.class);
                                    itemIntent.putExtra("ducttypeid",id);
                                    itemIntent.putExtra("bedmainid",bedmainid);
                                    itemIntent.putExtra("duct_info",baseModel.getData().get(position).getDuct_cat().get(pos));
                                    itemIntent.putExtra("ductid",baseModel.getData().get(position).getDuct_id());
                                    itemIntent.putExtra("ductname",baseModel.getData().get(position).getDuct_name()+"-"+baseModel.getData().get(position).getDuct_cat().get(pos).getDuct_cat_name());
                                    startActivity(itemIntent);
                                }

                                @Override
                                public void closeClick() {

                                }
                            });
                            WindowManager.LayoutParams lp = ((Activity) SelectDuctActivity.this).getWindow().getAttributes();
                            lp.alpha = 0.4f;
                            ((Activity) SelectDuctActivity.this).getWindow().setAttributes(lp);
                            PopUpwindowUtil.popupWindow.showAtLocation(rlDuctSelect, Gravity.CENTER, 0, 0);
                        }
                    }
                });
                rlDuctSelect.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                rlDuctSelect.setLayoutManager(new LinearLayoutManager(this));
                rlDuctSelect.setAdapter(adapter);
                break;
        }
    }
}
