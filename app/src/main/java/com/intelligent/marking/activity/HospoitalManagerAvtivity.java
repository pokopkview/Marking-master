package com.intelligent.marking.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intelligent.marking.BaseActivity;
import com.intelligent.marking.Const.AppConst;
import com.intelligent.marking.R;
import com.intelligent.marking.Utils.PreferencesUtils;
import com.intelligent.marking.adapter.SimpleListTextAdapter;
import com.intelligent.marking.adapter.SimpleSubAreaAdapter;
import com.intelligent.marking.adapter.areaselectmanagerAdapter;
import com.intelligent.marking.eventbus.ManagerLevelEvent;
import com.intelligent.marking.net.model.AreaModel;
import com.intelligent.marking.net.model.BaseModel;
import com.intelligent.marking.net.model.DepartModel;
import com.intelligent.marking.net.model.SubAreModel;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HospoitalManagerAvtivity extends BaseActivity {

    @BindView(R.id.ll_left_container)
    LinearLayout llLeftContainer;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ll_right_container)
    LinearLayout llRightContainer;
    @BindView(R.id.tv_edit_subarea)
    TextView tvEditSubarea;
    @BindView(R.id.tv_edit_employee)
    TextView tvEditEmployee;
    @BindView(R.id.lv_show_detail)
    ListView lvShowDetail;
    @BindView(R.id.ll_right_detail_container)
    LinearLayout llRightDetailContainer;
    @BindView(R.id.left_menu_lv)
    ListView leftMenuLv;
    @BindView(R.id.header_title_root)
    RelativeLayout headerTitleRoot;
    @BindView(R.id.tv_select_are)
    TextView tvSelectAre;
    @BindView(R.id.rl_select_area)
    RelativeLayout rlSelectArea;
    @BindView(R.id.ll_add_select_are)
    LinearLayout llAddSelectAre;

    private int tempSelect = 0;
    private int tempSelectid = -1;
    private Map<Integer, List<SubAreModel>> datas = new HashMap<>();
    SimpleListTextAdapter simpleListTextAdapter;
    SimpleSubAreaAdapter simpleSubAreaAdapter;
    PopupWindow popupWindow;
    areaselectmanagerAdapter adapter;
    int role =-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospoital_manager_avtivity);
        ButterKnife.bind(this);
        ImageView back = new ImageView(this);
        back.setImageResource(R.mipmap.fanhui01);
        llLeftContainer.addView(back);
        tvHeaderTitle.setText(hospitalName);
        setViewByRole();
//        value.clear();
//        value.put("area_id", 1);
//        HttpPost(AppConst.GETDEPARTMENT, value, 1);
    }

    private void setViewByRole() {
        simpleListTextAdapter = new SimpleListTextAdapter(HospoitalManagerAvtivity.this);
        leftMenuLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(role>2){
                    showToast("无权限!");
                    return;
                }
                simpleListTextAdapter.setSelecposition(position);
                tempSelect = position;
                tempSelectid = simpleListTextAdapter.getListDate().get(tempSelect).getDepartment_id();
                EventBus.getDefault().postSticky(new ManagerLevelEvent(tempSelectid));
                if (datas.containsKey(tempSelectid)) {
                    List<SubAreModel> temp = datas.get(simpleListTextAdapter.getListDate().get(position).getDepartment_id());
                    simpleSubAreaAdapter.setData(temp);
                } else {
                    Map<String, Object> value = new HashMap<>();
                    value.put("department_id", simpleListTextAdapter.getListDate().get(position).getDepartment_id());
                    HttpPost(AppConst.GETSUBAREA, value, 2);
                }
            }
        });


        role = PreferencesUtils.getInt(this,PreferencesUtils.ROLE,-1);
        switch (role){
            case 1:
//                value.clear();
//                value.put("area_id", 1);
//                HttpPost(AppConst.GETDEPARTMENT, value, 1);

                break;
            case 2:
                tvSelectAre.setText(areaName);
                rlSelectArea.setTag(false);
                value.clear();
                value.put("area_id", areaNameid);
                HttpPost(AppConst.GETDEPARTMENT, value, 1);
                break;
            case 3:
                tvSelectAre.setText(areaName);
                rlSelectArea.setTag(false);
                value.clear();
                value.put("area_id", areaNameid);
                HttpPost(AppConst.GETDEPARTMENT, value, 1);
                break;
            case 4:
                tvSelectAre.setText(areaName);
                value.clear();
                value.put("area_id", areaNameid);
                HttpPost(AppConst.GETDEPARTMENT, value, 1);
                break;
        }



    }


    /**
     * 片区管理
     *
     * @param view
     */
    @OnClick(R.id.tv_edit_subarea)
    public void editSubArea(View view) {
        if(leftMenuLv.getAdapter()== null){
            showToast("请选择大区");
            return;
        }
        Intent intent = new Intent(this, SubAreaManagerActivity.class);
        System.out.println(datas.get(tempSelectid));
        intent.putExtra("subarealist", (Serializable) datas.get(tempSelectid));
        startActivity(intent);
    }

    @OnClick(R.id.tv_edit_employee)
    public void editSubareaEmployee(View view) {
        if(leftMenuLv.getAdapter()== null){
            showToast("请选择大区");
            return;
        }
        Intent intent = new Intent(this, SubAreaEmployeeActivity.class);
        startActivity(intent);
    }

    @Override
    public void getCallBack(String response, int flag) {
        Type type;
        switch (flag) {
            case 1:
                type = new TypeToken<BaseModel<List<DepartModel>>>() {
                }.getType();
                BaseModel<List<DepartModel>> model = new Gson().fromJson(response, type);
                simpleListTextAdapter.setData(model.getData());
                leftMenuLv.setAdapter(simpleListTextAdapter);
                if(role > 1){
                    for(int i = 0;i<model.getData().size();i++) {
                        if(model.getData().get(i).getDepartment_name().equals(departName)) {
                            simpleListTextAdapter.setSelecposition(i);
                        }
                    }
                }
                value.clear();
                value.put("department_id", model.getData().get(0).getDepartment_id());
                EventBus.getDefault().postSticky(new ManagerLevelEvent(model.getData().get(0).getDepartment_id()));
                HttpPost(AppConst.GETSUBAREA, value, 2);
                break;
            case 2:
                type = new TypeToken<BaseModel<List<SubAreModel>>>() {
                }.getType();
                BaseModel<List<SubAreModel>> subareamodel = new Gson().fromJson(response, type);
                List<SubAreModel> subAreModelList = subareamodel.getData();
                System.out.println(!datas.containsKey(tempSelectid));
                if (!datas.containsKey(tempSelectid)) {
                    datas.put(tempSelectid, subAreModelList);
                }
                if (simpleSubAreaAdapter == null) {
                    simpleSubAreaAdapter = new SimpleSubAreaAdapter(HospoitalManagerAvtivity.this, subAreModelList);
                    lvShowDetail.setAdapter(simpleSubAreaAdapter);
                } else {
                    simpleSubAreaAdapter.setData(subAreModelList);
                }
                lvShowDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //TODO
                        Intent intent = new Intent(HospoitalManagerAvtivity.this, BedInfoActivity.class);
                        intent.putExtra("subarea_id", subAreModelList.get(position).getSubarea_id());
                        startActivity(intent);
                    }
                });
                break;
            case 3:
                type = new TypeToken<BaseModel<List<AreaModel>>>() {
                }.getType();
                BaseModel<List<AreaModel>> listBaseModel = new Gson().fromJson(response, type);
                View view = LayoutInflater.from(this).inflate(R.layout.select_are_manager_layout, null);
                TextView area = view.findViewById(R.id.tv_select_are);
                popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                ListView listView = view.findViewById(R.id.lv_area);
                adapter = new areaselectmanagerAdapter(this, listBaseModel.getData(), tvSelectAre.getText().toString());
                listView.setAdapter(adapter);
                listView.setDividerHeight(0);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        WindowManager.LayoutParams lp = ((Activity) HospoitalManagerAvtivity.this).getWindow().getAttributes();
                        lp.alpha = 1f;
                        ((Activity) HospoitalManagerAvtivity.this).getWindow().setAttributes(lp);
                        popupWindow.dismiss();

                        value.clear();
                        value.put("area_id",listBaseModel.getData().get(position).getArea_id());
//                        EventBus.getDefault().post(new ManagerLevelEvent(listBaseModel.getData().get(position).getArea_id(),-1));
                        HttpPost(AppConst.GETDEPARTMENT, value, 1);
                        tvSelectAre.setText(listBaseModel.getData().get(position).getArea_name());
                        area.setText(listBaseModel.getData().get(position).getArea_name());
                    }
                });
                popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
                popupWindow.setClippingEnabled(false);
                System.out.println("Y:" + rlSelectArea.getY() + "height:" + this.getResources().getDimensionPixelSize(R.dimen.y59));
                adapter.setSelectItem(tvSelectAre.getText().toString());
                popupWindow.showAsDropDown(headerTitleRoot, 0, 0);
                break;

        }
    }

    private void initChildView() {
        //TODO

    }

    @OnClick(R.id.rl_select_area)
    public void onViewClicked() {
        if(rlSelectArea.getTag()!=null){
            showToast("无权限!");
            return;
        }
        if (popupWindow == null) {

            HttpPost(AppConst.MANAGER_ARE, value, 3);
        } else {
            adapter.setSelectItem(tvSelectAre.getText().toString());
            popupWindow.showAsDropDown(headerTitleRoot, 0, 0);
        }
    }
}
